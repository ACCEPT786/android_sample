package com.moon.libbase.utils.extension

import com.moon.libbase.utils.FilterUtils

/**
 * @author ry
 * @date 2019-06-29
 */
/**
 * 验证一个字符串是否是手机形式
 */
fun String?.isPhone(): Boolean {
    if (this.isNullOrBlank()){
        return false
    }
    return FilterUtils.isPhone(this)
}

fun String?.isEmail():Boolean{
    if (this.isNullOrBlank()){
        return false
    }
    return this.matches(Regex("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
}