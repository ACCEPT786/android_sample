package com.moon.educational.ui.teacher.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moon.educational.R
import com.moon.educational.adapter.TeacherStudentAdapter
import com.moon.educational.databinding.FragmentTeacherStudentBinding
import com.moon.educational.ui.teacher.TeacherDetailActivity
import com.moon.educational.ui.teacher.vm.TeacherDetailViewModel
import com.moon.libcommon.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_teacher_student.*


class TeacherStudentFragment :
    BaseVMFragment<FragmentTeacherStudentBinding, TeacherDetailViewModel>() {
    companion object {
        fun newInstance(teacherId: String?): TeacherStudentFragment {
            val fragment = TeacherStudentFragment()
            val bundle = Bundle()
            bundle.putString(TeacherDetailActivity.KEY_TEACHERID, teacherId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_teacher_student
    var teacherId: String? = null
    val adapter = TeacherStudentAdapter()


    override fun onInject() {
        super.onInject()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TeacherDetailViewModel::class.java)
        teacherId = arguments?.getString(TeacherDetailActivity.KEY_TEACHERID)
    }

    override fun initView() {
        super.initView()
        rvTeacherStudent.adapter = adapter
    }

    override fun initData() {
        super.initData()
        viewModel.getTeacherDetailStudent(teacherId)
    }

    override fun observerData() {
        super.observerData()
        viewModel.teacherStudent.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}
