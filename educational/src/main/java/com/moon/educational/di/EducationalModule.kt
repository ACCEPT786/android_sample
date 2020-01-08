package com.moon.educational.di

import com.moon.educational.ui.course.ManagerCourseActivity
import com.moon.educational.ui.teacher.AddTeacherActivity
import com.moon.educational.ui.teacher.ManagerTeacherActivity
import com.moon.educational.ui.teacher.TeacherDetailActivity
import com.moon.libbase.dl.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-12-20
 */
@Module(includes = [ViewModelModule::class])
abstract class EducationalModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeManagerTeacherActivity(): ManagerTeacherActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeAddTeacherActivity(): AddTeacherActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TeacherDetailFragmentModule::class])
    abstract fun contributeTeacherDetailActivity(): TeacherDetailActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [CourseFragmentModule::class])
    abstract fun contributeManagerCourseActivity(): ManagerCourseActivity
}