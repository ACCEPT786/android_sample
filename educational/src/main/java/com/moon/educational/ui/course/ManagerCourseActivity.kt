package com.moon.educational.ui.course

import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.moon.educational.R
import com.moon.educational.databinding.ActivityManagerCourseBinding
import com.moon.educational.ui.course.vm.ManagerCourseViewModel
import com.moon.libbase.utils.extension.setupWithViewPager2
import com.moon.libbase.widget.adapter.ViewPager2Adapter
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.utils.ARouteAddress


@Route(path = ARouteAddress.EDU_MANAGER_COURSE)
class ManagerCourseActivity : BaseVMActivity<ActivityManagerCourseBinding, ManagerCourseViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_manager_course

    override fun onInject() {
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ManagerCourseViewModel::class.java)

    }

    override fun initView() {
        super.initView()
        dataBinding.viewPager.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle).apply {

        }
        dataBinding.viewPager.offscreenPageLimit = 2
        dataBinding.tabLayout.setupWithViewPager2(
            dataBinding.viewPager, arrayListOf(
                getString(R.string.course_tab_course),
                getString(R.string.course_tab_goods),
                getString(R.string.course_tab_cost)
            )
        )
    }


}
