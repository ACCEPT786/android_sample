package com.moon.educational.http

import com.moon.libbase.base.net.HttpResult
import com.moon.libcommon.entity.Teacher
import com.moon.libcommon.entity.TeacherClass
import com.moon.libcommon.entity.TeacherStudent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author ry
 * @date 2019-12-23
 */
interface EducationalApi {
    //获取老师列表
    @GET("ximu/assistant/api/teacher/list")
    fun getTeacherList(@Query("lastId") lastId: Long, @Query("limit") limit: Int, @Query("key") key: String?): Observable<HttpResult<List<Teacher>>>

    //获取老师详情个人信息
    @GET("ximu/assistant/api/teacher/detail")
    fun getTeacherDetailInfo(@Query("teacherId") teacherId: String): Observable<HttpResult<Teacher>>

    //获取老师详情授课班级
    @GET("ximu/assistant/api/teacher/detailclass")
    fun getTeacherDetailClass(@Query("teacherId") teacherId: String): Observable<HttpResult<List<TeacherClass>>>

    //获取老师详情教授学生
    @GET("ximu/assistant/api/teacher/detailstudent")
    fun getTeacherDetailStudent(@Query("teacherId") teacherId: String): Observable<HttpResult<List<TeacherStudent>>>
}