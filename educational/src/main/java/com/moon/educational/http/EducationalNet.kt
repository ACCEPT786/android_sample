package com.moon.educational.http

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-12-23
 */
@Singleton
class EducationalNet @Inject constructor(retrofit: Retrofit) {

    private val api = retrofit.create(EducationalApi::class.java)

    //获取老师列表
    fun getTeacherList(
        lastId: Long,
        limit: Int,
        key: String?
    ) = api.getTeacherList(lastId, limit, key)


    //获取老师详情个人信息
    fun getTeacherDetailInfo(teacherId: String) = api.getTeacherDetailInfo(teacherId)

    //获取老师详情授课班级
    fun getTeacherDetailClass(teacherId: String) = api.getTeacherDetailClass(teacherId)

    //获取老师详情教授学生
    fun getTeacherDetailStudent(teacherId: String) = api.getTeacherDetailStudent(teacherId)
}