package com.moon.educational.ui.teacher.fragment


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moon.educational.R
import com.moon.educational.databinding.FragmentTeacherInfoBinding
import com.moon.educational.ui.teacher.TeacherDetailActivity.Companion.KEY_TEACHERID
import com.moon.educational.ui.teacher.vm.TeacherDetailViewModel
import com.moon.libcommon.base.BaseVMFragment
import com.moon.libcommon.entity.Teacher

/**
 * 教师详情个人信息界面
 */
class TeacherInfoFragment : BaseVMFragment<FragmentTeacherInfoBinding, TeacherDetailViewModel>() {

    companion object {
        fun newInstance(teacherId: String?): TeacherInfoFragment {
            val fragment = TeacherInfoFragment()
            val bundle = Bundle()
            bundle.putString(KEY_TEACHERID, teacherId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_teacher_info


    var teacherId: String? = null
    override fun onInject() {
        super.onInject()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TeacherDetailViewModel::class.java)
        teacherId = arguments?.getString(KEY_TEACHERID)
    }

    override fun initData() {
        super.initData()
        viewModel.getTeacherDetailInfo(teacherId)
    }

    override fun observerData() {
        super.observerData()
        viewModel.teacherInfo.observe(this, Observer {
            setTeacherInfo(it)
        })
    }

    private fun setTeacherInfo(teacher: Teacher?) {
        if (teacher == null) return
        //todo 头像
        dataBinding.tvName.text = teacher.name
        dataBinding.tvSubject.text = teacher.subjectName
        dataBinding.tvPhone.text = teacher.phone
        dataBinding.tvGender.text = teacher.getGenderString()

        dataBinding.statusButton.isChecked = teacher.status == 1
    }
}
