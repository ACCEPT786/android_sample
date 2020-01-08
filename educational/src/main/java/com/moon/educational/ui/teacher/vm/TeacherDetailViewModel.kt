package com.moon.educational.ui.teacher.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.educational.http.EducationalRepo
import com.moon.libcommon.entity.Teacher
import com.moon.libcommon.entity.TeacherClass
import com.moon.libcommon.entity.TeacherStudent
import com.moon.libcommon.http.CommonObserver
import javax.inject.Inject

class TeacherDetailViewModel @Inject constructor(private val repo: EducationalRepo) : ViewModel() {


    val teacherInfo = MutableLiveData<Teacher?>()
    val teacherClass = MutableLiveData<List<TeacherClass>?>()
    val teacherStudent = MutableLiveData<List<TeacherStudent>?>()

    /**
     * 获取老师详情个人信息
     */
    fun getTeacherDetailInfo(teacherId: String?) {
        if (teacherId.isNullOrEmpty()) return
        repo.getTeacherDetailInfo(teacherId, object : CommonObserver<Teacher>() {
            override fun onSuccess(result: Teacher?) {
                teacherInfo.value = result
            }
        })
    }

    /**
     * 获取老师详情授课班级
     */
    fun getTeacherDetailClass(teacherId: String?) {
        if (teacherId.isNullOrEmpty()) return
        repo.getTeacherDetailClass(teacherId, object : CommonObserver<List<TeacherClass>>() {
            override fun onSuccess(result: List<TeacherClass>?) {
                teacherClass.value = result
            }

        })
    }

    /**
     * 获取老师详情教授学生
     */
    fun getTeacherDetailStudent(teacherId: String?) {
        if (teacherId.isNullOrEmpty()) return
        repo.getTeacherDetailStudent(teacherId, object : CommonObserver<List<TeacherStudent>>() {
            override fun onSuccess(result: List<TeacherStudent>?) {
                teacherStudent.value = result
            }

        })
    }

}