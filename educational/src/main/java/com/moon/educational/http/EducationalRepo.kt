package com.moon.educational.http

import com.moon.libbase.base.net.BaseHttpObserver
import com.moon.libbase.utils.extension.ioScheduler
import com.moon.libcommon.entity.Teacher
import com.moon.libcommon.entity.TeacherClass
import com.moon.libcommon.entity.TeacherStudent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-12-23
 */
@Singleton
class EducationalRepo @Inject constructor(private val net: EducationalNet) {
    //获取老师列表
    fun getTeacherList(key: String?, observer: BaseHttpObserver<List<Teacher>>) {
        net.getTeacherList(0, Int.MAX_VALUE, key).ioScheduler().subscribe(observer)
    }


    //获取老师详情个人信息
    fun getTeacherDetailInfo(teacherId: String,observer: BaseHttpObserver<Teacher>) {
        net.getTeacherDetailInfo(teacherId).ioScheduler().subscribe(observer)
    }

    //获取老师详情授课班级
    fun getTeacherDetailClass(teacherId: String,observer: BaseHttpObserver<List<TeacherClass>>) {
        net.getTeacherDetailClass(teacherId).ioScheduler().subscribe(observer)
    }

    //获取老师详情教授学生
    fun getTeacherDetailStudent(teacherId: String,observer: BaseHttpObserver<List<TeacherStudent>>) {
        net.getTeacherDetailStudent(teacherId).ioScheduler().subscribe(observer)
    }
}