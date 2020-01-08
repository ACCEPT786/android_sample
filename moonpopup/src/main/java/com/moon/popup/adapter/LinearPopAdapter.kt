package com.moon.popup.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.popup.adapter.LinearPopAdapter.LinearItemVH
import com.moon.popup.R
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * @author ry
 * @date 2019-07-16
 * 线性展现适配器
 */
class LinearPopAdapter : ListAdapter<ListItem, LinearItemVH>(diff) {

    var clickListener: ((ListItem, Int, Int) -> Unit)? = null

    var checkPosition = -1

    override fun onBindViewHolder(holder: LinearItemVH, position: Int) {
        holder.itemView.setOnClickListener {
            val prePosition = checkPosition
            if (prePosition != position) {
                if (prePosition != -1) {
                    notifyItemChanged(prePosition)
                }
                checkPosition = position
                holder.tvItem.textColor =
                    ContextCompat.getColor(holder.itemView.context, R.color.popup_select_color)
            }
            //传递当前序号和之前的序号
            clickListener?.invoke(getItem(position), prePosition, position)

        }
        holder.tvItem.text = getItem(position).name
        holder.tvItem.textColor =
            ContextCompat.getColor(
                holder.itemView.context,
                if (checkPosition == position) R.color.popup_select_color else R.color.popup_unSelect_color
            )
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearItemVH {
        return LinearItemVH.create(parent)
    }

    fun getCurrentItem(): ListItem? {
        if (checkPosition == -1) return null
        return getItem(checkPosition)
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

    class LinearItemVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.find<TextView>(R.id.tvItem)

        companion object {
            fun create(parent: ViewGroup): LinearItemVH {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_linear_list, parent, false)
                return LinearItemVH(view)
            }
        }
    }

}


