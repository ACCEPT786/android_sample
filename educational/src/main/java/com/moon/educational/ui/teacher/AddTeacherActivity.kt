package com.moon.educational.ui.teacher

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.moon.educational.R
import com.moon.educational.ui.teacher.vm.AddTeacherViewModel
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.utils.ARouteAddress
import kotlinx.android.synthetic.main.activity_add_teacher.*

@Route(path = ARouteAddress.EDU_ADD_TEACHER)
class AddTeacherActivity : BaseVMActivity<ViewDataBinding, AddTeacherViewModel>() {
    override fun hasBinding(): Boolean {
        return false
    }

    override val layoutId: Int
        get() = R.layout.activity_add_teacher

    override fun onInject() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddTeacherViewModel::class.java)
    }

    override fun initListener() {
        super.initListener()
        portraitBlock.setOnClickListener {  }
        genderBlock.setOnClickListener {  }
        subjectBlock.setOnClickListener {  }
    }


}
