package com.moon.educational.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.moon.educational.R
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource

/**
 * @author ry
 * @date 2019-12-20
 */
class HomeGridAdapter : ListAdapter<HomeItem, HomeGridAdapter.HomeGridAdapterVH>(diff) {

    var clickListener: ((HomeItem) -> Unit)? = null

    override fun onBindViewHolder(holder: HomeGridAdapterVH, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener?.invoke(getItem(position))
        }
        holder.imageView.imageResource = getItem(position).iconId
        holder.titleView.setText(getItem(position).titleRes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeGridAdapterVH {
        return HomeGridAdapterVH.create(parent)
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<HomeItem>() {
            override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class HomeGridAdapterVH(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.find<ImageView>(R.id.imageView)
        val titleView = view.find<TextView>(R.id.titleView)

        companion object {
            fun create(parent: ViewGroup): HomeGridAdapterVH {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_grid, parent, false)
                return HomeGridAdapterVH(view)
            }
        }
    }
}

data class HomeItem(val iconId: Int, val titleRes: Int)