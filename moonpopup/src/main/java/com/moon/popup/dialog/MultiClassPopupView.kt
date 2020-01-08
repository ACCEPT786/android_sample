package com.moon.popup.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libbase.utils.ui.dp
import com.moon.popup.R
import kotlinx.android.synthetic.main.multi_popup_list.view.ok_btn
import kotlinx.android.synthetic.main.popup_multi_check_list.view.*
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-07-18
 * 居中弹框 科目多选弹框
 */
class MultiClassPopupView<T>(context: Context) : CenterPopupView(context), View.OnClickListener {


    var adapter: MultiClassPopAdapter<T>? = null

    var clickOkListener: ((List<T>) -> Unit)? = null

    val selectList = arrayListOf<T>()
    val tempSelectList = arrayListOf<T>()

    override fun getImplLayoutId(): Int {
        return R.layout.popup_multi_check_list
    }

    override fun onCreate() {
        adapter?.selectList = this.selectList
        adapter?.checkChanged = {
            checkAllStatus()
        }
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            ).apply {
                val drawable = ShapeDrawable(RectShape()).apply {
                    intrinsicHeight = 1.dp
                    paint.color = Color.parseColor("#ECEEEF")
                }
                setDrawable(drawable)
            })
        checkALlTV.setOnClickListener(this)
        checkAll.setOnCheckedChangeListener { _, _ ->
            clickAll()
        }
        ok_btn.setOnClickListener(this)
        checkAllStatus()
    }

    override fun onShow() {
        super.onShow()
        tempSelectList.clear()
        tempSelectList.addAll(selectList)
    }

    override fun onDismiss() {
        super.onDismiss()
        selectList.clear()
        selectList.addAll(tempSelectList)
        adapter?.notifyDataSetChanged()
        checkAllStatus()
    }

    /**
     * 检测全选状态
     */
    private fun checkAllStatus() {
        if (selectList.size == adapter?.itemCount) {
            changeCheckAll(true)
        } else {
            changeCheckAll(false)
        }
    }

    private fun changeCheckAll(checked: Boolean) {
        if (checkAll.isChecked == checked) return
        //这里只需要改变CheckBox状态，不需要触发事件，所以先将事件置空
        checkAll.setOnCheckedChangeListener(null)
        checkAll.toggle()
        checkAll.setOnCheckedChangeListener { _, _ ->
            clickAll()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.checkALlTV -> {
                checkAll.toggle()
            }
            R.id.ok_btn -> {
                tempSelectList.clear()
                tempSelectList.addAll(selectList)
                dismiss()
                clickOkListener?.invoke(selectList)
            }
        }
    }

    private fun clickAll() {
        if (checkAll.isChecked) {
            selectList.clear()
            selectList.addAll(adapter?.currentList ?: emptyList())
        } else {
            selectList.clear()
        }
        adapter?.notifyDataSetChanged()
    }

    fun submitList(list: List<T>) {
        adapter?.submitList(list)
        selectList.clear()
        selectList.addAll(list)
    }

    companion object {
        fun <T> createPupUpView(
            context: Context,
            list: List<T>,
            adapter: MultiClassPopAdapter<T>
        ): MultiClassPopupView<T> {
            val popup = MultiClassPopupView<T>(context)
            popup.adapter = adapter
            popup.submitList(list)
            return popup
        }
    }
}

abstract class MultiClassPopAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, MultiClassPopAdapter.ListItemVH>(diffCallback) {

    var selectList: ArrayList<T>? = null
    var checkChanged: (() -> Unit)? = null

    override fun onBindViewHolder(holder: ListItemVH, position: Int) {
        val item = getItem(position)
        bindText(item, holder, position)
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = selectList?.contains(item) ?: false
        holder.text.isSelected = selectList?.contains(item) ?: false
        holder.checkBox.setOnCheckedChangeListener { _, _ ->
            if (holder.checkBox.isChecked) {
                selectList?.add(item)
            } else {
                selectList?.remove(item)
            }
            holder.text.isSelected = holder.checkBox.isChecked
            checkChanged?.invoke()
        }
    }

    abstract fun bindText(item: T, holder: ListItemVH, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemVH {
        return ListItemVH.create(parent)
    }

    class ListItemVH(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox = view.find<CheckBox>(R.id.checkAll)
        val text = view.find<TextView>(R.id.checkALlTV)

        companion object {
            fun create(parent: ViewGroup): ListItemVH {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_multi_check_list, parent, false)
                return ListItemVH(view)
            }
        }
    }


}