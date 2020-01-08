package com.moon.educational.ui

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.educational.R
import com.moon.educational.adapter.HomeGridAdapter
import com.moon.educational.adapter.HomeItem
import com.moon.libcommon.base.BaseVMFragment
import com.moon.libcommon.utils.ARouteAddress
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseVMFragment<ViewDataBinding, ViewModel>() {

    override fun hasBinding(): Boolean {
        return false
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    val adapter = HomeGridAdapter()

    override fun initView() {
        super.initView()
        adapter.submitList(
            listOf(
                HomeItem(R.drawable.home_ic_teacher, R.string.home_tab_teacher),
                HomeItem(R.drawable.home_ic_student, R.string.home_tab_student),
                HomeItem(R.drawable.home_ic_classes, R.string.home_tab_classes),
                HomeItem(R.drawable.home_ic_course, R.string.home_tab_course),
                HomeItem(R.drawable.home_ic_course_schedule, R.string.home_tab_course_schedule),
                HomeItem(R.drawable.home_ic_class_record, R.string.home_tab_class_record),
                HomeItem(R.drawable.home_ic_notice, R.string.home_tab_notice),
                HomeItem(R.drawable.home_ic_student_archives, R.string.home_tab_student_archives)
            )
        )
        adapter.clickListener = {
            when (it.iconId) {
                R.drawable.home_ic_teacher -> {
                    ARouter.getInstance().build(ARouteAddress.EDU_MANAGER_TEACHER).navigation()
                }
                R.drawable.home_ic_student -> {
                }
                R.drawable.home_ic_classes -> {
                }
                R.drawable.home_ic_course -> {
                    ARouter.getInstance().build(ARouteAddress.EDU_MANAGER_COURSE).navigation()
                }
                R.drawable.home_ic_course_schedule -> {
                }
                R.drawable.home_ic_class_record -> {
                }
                R.drawable.home_ic_notice -> {
                }
                R.drawable.home_ic_student_archives -> {
                }
            }
        }
        rvHomeGrid.adapter = adapter
    }


}
