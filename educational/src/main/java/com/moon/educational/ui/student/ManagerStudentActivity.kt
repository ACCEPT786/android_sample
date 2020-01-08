package com.moon.educational.ui.student

import androidx.lifecycle.ViewModelProvider
import com.moon.educational.R
import com.moon.educational.databinding.ActivityManagerStudentBinding
import com.moon.educational.ui.student.vm.ManagerStudentViewModel
import com.moon.libcommon.base.BaseVMActivity


class ManagerStudentActivity :
    BaseVMActivity<ActivityManagerStudentBinding, ManagerStudentViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_manager_student

    override fun onInject() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ManagerStudentViewModel::class.java)
    }


}
