package com.moon.educational.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.educational.R
import com.moon.libcommon.entity.TeacherStudent
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-12-25
 */
class TeacherStudentAdapter :
    ListAdapter<TeacherStudent, TeacherStudentAdapter.TeacherStudentAdapterVH>(diff) {

    var clickListener: ((TeacherStudent) -> Unit)? = null

    override fun onBindViewHolder(holder: TeacherStudentAdapterVH, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener?.invoke(item)
        }
        holder.nameView.text = item.studentName
        holder.phoneView.text = item.phone
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherStudentAdapterVH {
        return TeacherStudentAdapterVH.create(parent)
    }


    companion object {
        val diff = object : DiffUtil.ItemCallback<TeacherStudent>() {
            override fun areContentsTheSame(
                oldItem: TeacherStudent,
                newItem: TeacherStudent
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: TeacherStudent,
                newItem: TeacherStudent
            ): Boolean {
                return oldItem.studentId == newItem.studentId
            }
        }
    }

    class TeacherStudentAdapterVH(view: View) : RecyclerView.ViewHolder(view) {
        val phoneView = view.find<TextView>(R.id.phoneView)
        val nameView = view.find<TextView>(R.id.nameView)
        companion object {
            fun create(parent: ViewGroup): TeacherStudentAdapterVH {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_teacher_student, parent, false)
                return TeacherStudentAdapterVH(view)
            }
        }
    }
}