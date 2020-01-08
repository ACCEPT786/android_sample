package com.moon.libbase.base.net

/**
 * @author ry
 * @date 2019-05-16
 */
data class HttpResult<T>(var code: Int, var msg: String? = "", var data: T?)


const val SUCCESS_CODE = 200



