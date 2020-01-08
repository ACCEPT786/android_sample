package com.moon.educational.ui.course.fragment


import androidx.lifecycle.ViewModel
import com.moon.educational.R
import com.moon.educational.databinding.FragmentCourseTabBinding
import com.moon.libcommon.base.BaseVMFragment
import kotlinx.android.synthetic.main.layout_search_view.view.*


class CourseTabFragment : BaseVMFragment<FragmentCourseTabBinding,ViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_course_tab



    override fun initView() {
        super.initView()
        dataBinding.courseSearch.refreshRecycler
        dataBinding.courseSearch.searchView.setHint(R.string.search_course_hint)
    }


}
