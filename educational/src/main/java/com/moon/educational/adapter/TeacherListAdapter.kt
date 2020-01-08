package com.moon.educational.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.educational.R
import com.moon.educational.databinding.ItemTeacherListBinding
import com.moon.libcommon.entity.Teacher

/**
 * @author ry
 * @date 2019-12-21
 */
class TeacherListAdapter : ListAdapter<Teacher, TeacherListAdapter.TeacherListAdapterVH>(diff) {

    var clickListener: ((Teacher) -> Unit)? = null

    override fun onBindViewHolder(holder: TeacherListAdapterVH, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener?.invoke(getItem(position))
        }
        holder.binding.tvTeacherName.text = item.name
        holder.binding.tvTeacherSubject.text = item.subjectName
        holder.binding.tvClassHour.text = holder.binding.root.context.getString(
            R.string.total_month_class_hour,
            item.monthClassHour.toString()
        )
        //todo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListAdapterVH {
        return TeacherListAdapterVH.create(parent)
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<Teacher>() {
            override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
                return oldItem.teacherId == newItem.teacherId
            }
        }
    }

    class TeacherListAdapterVH(val binding: ItemTeacherListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): TeacherListAdapterVH {
                val binding = DataBindingUtil.inflate<ItemTeacherListBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_teacher_list,
                    parent,
                    false
                )
                return TeacherListAdapterVH(binding)
            }
        }
    }
}