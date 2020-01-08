package com.moon.popup.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.moon.popup.R
import com.moon.popup.adapter.ListItem.Companion.TYPE_TITLE
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-07-16
 * 年级适配器（带标题）
 */
class ListPopAdapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(diff) {

    var clickListener: ((ListItem) -> Unit)? = null

    var checkPosition = -1

    fun getCurrentItem(): ListItem? {
        if (checkPosition == -1) return null
        return getItem(checkPosition)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ListTitleVH) {
            holder.title.text = getItem(position).name
        } else if (holder is ListItemVH) {
            holder.checkBtn.setOnCheckedChangeListener(null)
            holder.checkBtn.text = getItem(position).name
            holder.checkBtn.isChecked = getItem(position).checked
            holder.checkBtn.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val preCheckedPosition = checkPosition
                    if (preCheckedPosition != -1) {
                        getItem(preCheckedPosition).checked = false
                        notifyItemChanged(preCheckedPosition)
                    }
                    getItem(position).checked = true
                    checkPosition = position
                    clickListener?.invoke(getItem(position))
                }
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    return if (type == TYPE_TITLE) {
                        layoutManager.spanCount
                    } else {
                        1
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_TITLE) {
            ListTitleVH.create(parent)
        } else {
            ListItemVH.create(parent)
        }
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ListItemVH(view: View) : RecyclerView.ViewHolder(view) {
        val checkBtn = view.find<RadioButton>(R.id.check_btn)

        companion object {
            fun create(parent: ViewGroup): ListItemVH {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_item, parent, false)
                return ListItemVH(view)
            }
        }
    }

    class ListTitleVH(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.find<TextView>(R.id.title)

        companion object {
            fun create(parent: ViewGroup): ListTitleVH {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_title, parent, false)
                return ListTitleVH(view)
            }
        }
    }
}

data class ListItem(var name: String, var type: Int, var requestString: String? = null, var checked: Boolean = false) {
    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_ITEM = 1
    }
}

