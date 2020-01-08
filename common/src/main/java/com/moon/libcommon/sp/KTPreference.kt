package com.moon.libcommon.sp

import com.moon.libbase.utils.extension.BaseKTPreference

/**
 * @author ry
 * @date 2019-12-16
 *
 * GSPSharedPreferences属性代理
 */
class KTPreference<T>(private val keyName: String, private val default: T) :
    BaseKTPreference<T>(GSPSharedPreferences.SP_NAME, keyName, default)