package com.moon.libaccount.http

import com.moon.libbase.base.BaseApplication
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.utils.extension.toJsonRequestBody
import com.moon.libbase.utils.secret.Md5Util
import com.moon.libbase.utils.system.SystemUtils
import com.moon.libcommon.entity.UserInfo
import com.moon.libcommon.http.ApiConfig
import com.moon.libcommon.sp.GSPSharedPreferences
import com.moon.libcommon.sp.KTPreference
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-05-16
 */
@Singleton
class AccountNet @Inject constructor(retrofit: Retrofit, val context: BaseApplication){


    private val api = retrofit.create(AccountApi::class.java)
    private val imsi by lazy { SystemUtils.getImsi(context) }
    private val imei by lazy { SystemUtils.getImei(context) }
    private val uid by KTPreference(GSPSharedPreferences.KEY_UID,"")

    /**
     * 手机号密码登录
     */
    fun phonePwdLogin(uType: Int, phone: String, pwd: String): Observable<HttpResult<UserInfo>> {
        return api.login(uType, ApiConfig.LOGIN_TYPE_PWD, phone, imsi, imei, Md5Util.generateStr(pwd).toJsonRequestBody())
    }

    /**
     * 验证码登录
     */
    fun codeLogin(uType: Int, phone: String, code: String): Observable<HttpResult<UserInfo>> {
        return api.login(uType, ApiConfig.LOGIN_TYPE_CODE, phone, imsi, imei, code.toJsonRequestBody())
    }

    /**
     * token登录
     */
    fun tokenLogin(uType: Int, token: String): Observable<HttpResult<UserInfo>> {
        return api.login(uType, ApiConfig.LOGIN_TYPE_TOKEN, uid, imsi, imei, token.toJsonRequestBody())
    }


    /**
     * 检查手机号是否已注册
     */
    fun checkPhoneIsRegistered(phone: String, uType: Int) = api.checkPhoneIsRegistered(phone, uType)

    /**
     * 获取验证码（手机或邮箱）
     */
    fun getVerificationCode(func: Int, send: String) = api.getVerificationCode(func, send)

    /**
     * 获取用户信息
     */
    fun getUserInfo() = api.getUserInfo()

}