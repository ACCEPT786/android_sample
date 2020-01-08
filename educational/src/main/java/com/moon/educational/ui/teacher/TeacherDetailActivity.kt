package com.moon.educational.ui.teacher

import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.moon.educational.R
import com.moon.educational.ui.teacher.fragment.TeacherClassFragment
import com.moon.educational.ui.teacher.fragment.TeacherInfoFragment
import com.moon.educational.ui.teacher.fragment.TeacherStudentFragment
import com.moon.educational.ui.teacher.vm.TeacherDetailViewModel
import com.moon.libbase.dl.ARouterInjectable
import com.moon.libbase.utils.extension.setupWithViewPager2
import com.moon.libbase.widget.adapter.ViewPager2Adapter
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.entity.Teacher
import com.moon.libcommon.utils.ARouteAddress
import kotlinx.android.synthetic.main.activity_teacher_detail.*

@Route(path = ARouteAddress.EDU_TEACHER_DETAIL)
class TeacherDetailActivity :
    BaseVMActivity<ViewDataBinding, TeacherDetailViewModel>(), ARouterInjectable {

    companion object {
        const val KEY_TEACHERID = "teacherId"
    }

    override fun hasBinding(): Boolean {
        return false
    }

    @Autowired(name = ARouteAddress.EXTRA_TEACHER)
    @JvmField
    var teacher: Teacher? = null

    override val layoutId: Int
        get() = R.layout.activity_teacher_detail

    override fun onInject() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TeacherDetailViewModel::class.java)
    }

    override fun setupToolbar(toolbar: Toolbar) {
        super.setupToolbar(toolbar)
        title = teacher?.name
    }

    override fun initView() {
        super.initView()
        viewPager.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle).apply {
            addFragment(TeacherInfoFragment.newInstance(teacher?.teacherId))
            addFragment(TeacherClassFragment.newInstance(teacher?.teacherId))
            addFragment(TeacherStudentFragment.newInstance(teacher?.teacherId))
        }
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager2(
            viewPager, arrayListOf(
                getString(R.string.teacher_detail_tab_info),
                getString(R.string.teacher_detail_tab_class),
                getString(R.string.teacher_detail_tab_student)
            )
        )
    }

}
