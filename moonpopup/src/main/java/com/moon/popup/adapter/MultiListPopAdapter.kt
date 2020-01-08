package com.moon.popup.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.moon.popup.R
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-07-16
 * 多选的item
 */
class MultiListPopAdapter : ListAdapter<ListItem, MultiListPopAdapter.ListItemVH>(diff) {

    override fun onBindViewHolder(holder: ListItemVH, position: Int) {
        holder.checkBtn.setOnCheckedChangeListener(null)
        holder.checkBtn.text = getItem(position).name
        holder.checkBtn.isChecked = getItem(position).checked
        holder.checkBtn.setOnCheckedChangeListener { _, isChecked ->
            getItem(position).checked = isChecked
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemVH {
        return ListItemVH.create(parent)
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
        val checkBtn = view.find<CheckBox>(R.id.check_btn)

        companion object {
            fun create(parent: ViewGroup): ListItemVH {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_multi_list_item, parent, false)
                return ListItemVH(view)
            }
        }
    }


}

