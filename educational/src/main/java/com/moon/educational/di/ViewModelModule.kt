package com.moon.educational.di

import androidx.lifecycle.ViewModel
import com.moon.educational.ui.course.vm.ManagerCourseViewModel
import com.moon.educational.ui.teacher.vm.AddTeacherViewModel
import com.moon.educational.ui.teacher.vm.ManagerTeacherVM
import com.moon.educational.ui.teacher.vm.TeacherDetailViewModel
import com.moon.educational.ui.teacher.vm.TeacherInfoVM
import com.moon.libbase.dl.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author ry
 * @date 2019-12-20
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ManagerTeacherVM::class)
    abstract fun bindManagerTeacherVM(managerTeacherVM: ManagerTeacherVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddTeacherViewModel::class)
    abstract fun bindAddTeacherViewModel(addTeacherViewModel: AddTeacherViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeacherInfoVM::class)
    abstract fun bindTeacherInfoVM(teacherInfoVM: TeacherInfoVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeacherDetailViewModel::class)
    abstract fun bindTeacherDetailViewModel(teacherDetailViewModel: TeacherDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ManagerCourseViewModel::class)
    abstract fun bindManagerCourseViewModel(managerCourseViewModel: ManagerCourseViewModel): ViewModel


}