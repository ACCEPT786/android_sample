package com.moon.educational.di

import com.moon.educational.ui.course.fragment.CostTabFragment
import com.moon.educational.ui.course.fragment.CourseTabFragment
import com.moon.educational.ui.course.fragment.GoodsTabFragment
import com.moon.educational.ui.teacher.fragment.TeacherClassFragment
import com.moon.educational.ui.teacher.fragment.TeacherInfoFragment
import com.moon.educational.ui.teacher.fragment.TeacherStudentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-12-20
 */
@Module
abstract class TeacherDetailFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeTeacherInfoFragment(): TeacherInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeTeacherClassFragment(): TeacherClassFragment


    @ContributesAndroidInjector
    abstract fun contributeTeacherStudentFragment(): TeacherStudentFragment

}
@Module
abstract class CourseFragmentModule{
    @ContributesAndroidInjector
    abstract fun contributeCostTabFragment(): CostTabFragment

    @ContributesAndroidInjector
    abstract fun contributeCourseTabFragment(): CourseTabFragment

    @ContributesAndroidInjector
    abstract fun contributeGoodsTabFragment(): GoodsTabFragment
}