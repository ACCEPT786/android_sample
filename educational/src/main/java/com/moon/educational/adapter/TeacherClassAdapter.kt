package com.moon.educational.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.educational.R
import com.moon.libcommon.entity.TeacherClass
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-12-25
 */
class TeacherClassAdapter :
    ListAdapter<TeacherClass, TeacherClassAdapter.TeacherClassAdapterVH>(diff) {

    var clickListener: ((TeacherClass) -> Unit)? = null

    override fun onBindViewHolder(holder: TeacherClassAdapterVH, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener?.invoke(item)
        }
        holder.tvClassName.text = item.name
        holder.tvCourseName.text= item.coursename
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherClassAdapterVH {
        return TeacherClassAdapterVH.create(parent)
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<TeacherClass>() {
            override fun areContentsTheSame(oldItem: TeacherClass, newItem: TeacherClass): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: TeacherClass, newItem: TeacherClass): Boolean {
                return oldItem.classId == newItem.classId
            }
        }
    }

    class TeacherClassAdapterVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvCourseName = view.find<TextView>(R.id.tvCourseName)
        val tvClassName = view.find<TextView>(R.id.tvClassName)

        companion object {
            fun create(parent: ViewGroup): TeacherClassAdapterVH {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_teacher_class, parent, false)
                return TeacherClassAdapterVH(view)
            }
        }
    }
}