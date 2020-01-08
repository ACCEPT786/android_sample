package com.moon.educational.ui.teacher.fragment


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moon.educational.R
import com.moon.educational.adapter.TeacherClassAdapter
import com.moon.educational.databinding.FragmentTeacherClassBinding
import com.moon.educational.ui.teacher.TeacherDetailActivity.Companion.KEY_TEACHERID
import com.moon.educational.ui.teacher.vm.TeacherDetailViewModel
import com.moon.libcommon.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_teacher_class.*

class TeacherClassFragment : BaseVMFragment<FragmentTeacherClassBinding, TeacherDetailViewModel>() {

    companion object {
        fun newInstance(teacherId: String?): TeacherClassFragment {
            val fragment = TeacherClassFragment()
            val bundle = Bundle()
            bundle.putString(KEY_TEACHERID, teacherId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_teacher_class

    val adapter = TeacherClassAdapter()
    var teacherId: String? = null

    override fun onInject() {
        super.onInject()
        viewModel =
            ViewModelProvider(activity!!, viewModelFactory).get(TeacherDetailViewModel::class.java)
        teacherId = arguments?.getString(KEY_TEACHERID)
    }

    override fun initView() {
        super.initView()
        rvTeacherClass.adapter = adapter
    }

    override fun observerData() {
        super.observerData()
        viewModel.getTeacherDetailClass(teacherId)
        viewModel.teacherClass.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}
