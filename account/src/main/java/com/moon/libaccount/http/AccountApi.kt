package com.moon.libaccount.http

import com.moon.libbase.base.net.HttpResult
import com.moon.libcommon.entity.UserInfo
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author ry
 * @date 2019-05-13
 */
interface AccountApi {
    //学生/家长/教师  学生学号登录|手机密码登录|验证码登录|token登录
    //token 登录username传uid
    @POST("ximu/study/api/login")
    fun login(
        @Query("utype") utype: Int, @Query("loginType") loginType: Int, @Query("username") username: String?,
        @Query("imsi") imsi: String, @Query("imei") imei: String, @Body body: RequestBody, @Query("schoolId") schoolId: Long = 0
    ): Observable<HttpResult<UserInfo>>

    //账号是否注册
    //utype: 1学生 2老师 3家长
    @GET("ximu/study/api/isReg")
    fun checkPhoneIsRegistered(@Query("phone") phone: String, @Query("utype") utype: Int): Observable<HttpResult<Boolean>>

    //获取验证码(支持邮箱和手机)
    //func: 1 //1.手机 2.邮箱
    @GET("ximu/study/api/code")
    fun getVerificationCode(@Query("func") func: Int, @Query("send") send: String): Observable<HttpResult<String>>

    //更新用户信息
    @PUT("ximu/study/api/updateUserInfo")
    fun updateUserInfo(@Body requestBody: RequestBody):Observable<HttpResult<String>>

    //获取个人信息
    @GET("ximu/study/api/user/info")
    fun getUserInfo():Observable<HttpResult<UserInfo>>

    //push注册
    @POST("ximu/study/api/push/reg")
    fun bindPush(@Body body: RequestBody):Observable<HttpResult<String>>

    //push注销
    @POST("ximu/study/api/push/unReg")
    fun unBindPush(@Body body: RequestBody):Observable<HttpResult<String>>

}