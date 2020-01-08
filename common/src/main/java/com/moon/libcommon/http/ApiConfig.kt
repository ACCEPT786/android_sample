package com.moon.libcommon.http

import com.moon.libbase.BuildConfig

/**
 * @author ry
 * @date 2019-05-10
 */
class ApiConfig {
    companion object {
        const val TOKEN_ERROR = 401 //token错误
        const val SERVER_ERROR_CODE = 500 //服务端异常
        const val LACK_PARAMETER_CODE = 400//缺少参数

        const val BUSY_CODE = 4002//系统繁忙，由于验证码请求过多
        const val ERROR_CODE = 4005//验证码错误

        const val PWD_ERROR_CODE = 4008//账户或密码错误

        const val TEACHER_NOT_EXIT = 4004//教师用户不存在

        //获取验证码
        const val CODE_FUNC_PHONE = 1
        const val CODE_FUNC_EMAIL = 2

        //todo 登录类型
        const val LOGIN_TYPE_PWD = 2  //密码登录
        const val LOGIN_TYPE_CODE = 3  //验证码登录
        const val LOGIN_TYPE_TOKEN = 4  //token登录

        const val RES_URL_PRE = BuildConfig.BASE_RES_URL + "ximu/study/static/resource/"
        const val RES_UPLOAD_URL = BuildConfig.BASE_UPLOAD_URL + "ximu/study/api/resource/upload"

        fun getResource(resName: String?): String {
            if (resName != null && resName.startsWith("http")) {
                return resName
            }
            return RES_URL_PRE + resName
        }

    }
}

