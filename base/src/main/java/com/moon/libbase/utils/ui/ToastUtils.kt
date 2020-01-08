package com.moon.libbase.utils.ui

import android.app.Application
import androidx.annotation.MainThread
import org.jetbrains.anko.toast

/**
 * @author ry
 * @date 2019-05-15
 */
object ToastUtils {

    var application: Application? = null

    fun init(application: Application) {
        ToastUtils.application = application
    }

    @MainThread
    fun toast(id: Int) {
        application?.toast(id)
    }

    @MainThread
    fun toast(text: String) {
        application?.toast(text)
    }
}