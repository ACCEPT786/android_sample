package com.moon.popup.dialog

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.animator.PopupAnimator
import com.lxj.xpopup.impl.PartShadowPopupView
import com.moon.popup.adapter.ListItem
import com.moon.popup.adapter.MultiListPopAdapter
import kotlinx.android.synthetic.main.multi_popup_list.view.*

/**
 * @author ry
 * @date 2019-07-18
 * 局部阴影弹框 科目多选弹框
 */
class MultiSubjectPopupView(context: Context) : PartShadowPopupView(context) {

    var adapter = MultiListPopAdapter()

    var clickOkListener: ((List<ListItem>) -> Unit)? = null

    override fun getImplLayoutId(): Int {
        return com.moon.popup.R.layout.multi_popup_list
    }

    override fun onCreate() {
        adapter_rv.layoutManager = GridLayoutManager(context, 4)
        adapter_rv.adapter = adapter
        if (adapter.itemCount == 0) {
            empty_text.visibility = View.VISIBLE
        }
        //全选
        check_btn.setOnClickListener { _ ->
            val currentList = arrayListOf<ListItem>()
            currentList.addAll(adapter.currentList)
            currentList.forEach {
                it.checked = true
            }
            adapter.submitList(currentList)
            adapter.notifyDataSetChanged()
        }
        ok_btn.setOnClickListener {
            dismiss()
            clickOkListener?.invoke(adapter.currentList)
        }
        cancel_btn.setOnClickListener {
            dismiss()
        }
    }


    override fun getPopupAnimator(): PopupAnimator {
        return SlideDownAnimator(popupImplView)
    }

    companion object {
        fun createPupUpView(context: Context, list: List<ListItem>): MultiSubjectPopupView {
            val popup = MultiSubjectPopupView(context)
            popup.adapter.submitList(list)
            return popup
        }
    }
}