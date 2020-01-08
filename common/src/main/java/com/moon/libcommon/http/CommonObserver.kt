package com.moon.libcommon.http

import android.annotation.SuppressLint
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.core.text.isDigitsOnly
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.libbase.base.ActivityManager
import com.moon.libbase.base.net.BaseHttpObserver
import com.moon.libbase.utils.ui.ToastUtils
import com.moon.libcommon.R
import com.moon.libcommon.sp.GSPSharedPreferences
import com.moon.libcommon.utils.ARouteAddress
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

/**
 * 网络请求返回处理
 */
abstract class CommonObserver<T> : BaseHttpObserver<T>() {
    @SuppressLint("CheckResult")
    override fun onError(e: Throwable) {
        Timber.e(e, "ResultObserver onError")
        //处理自定义抛出的异常
        if (isNumeric(e.message)) {
            onInnerCodeError(e.message!!.toInt(), e.cause?.message ?: "")
            onRequestEnd()
            return
        }
        //拦截HTTP 401 token error
        val httpInt = e.message?.replace("HTTP", "")?.trim()
        if (httpInt == ApiConfig.TOKEN_ERROR.toString()) {
            Completable
                .complete()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onTokenError()
                    onRequestEnd()
                }
            return
        }
        super.onError(e)
    }

    override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
        if (isNetWorkError) {
            ToastUtils.toast(R.string.netError)
        } else {
            ToastUtils.toast(R.string.unKnowError)
        }
    }

    @SuppressLint("CheckResult")
    override fun onInnerCodeError(code: Int, message: String) {
        Completable
            .complete()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("code:$code,msg:$message")
                when (code) {
                    ApiConfig.TOKEN_ERROR -> {
                        onTokenError()
                    }
                    ApiConfig.SERVER_ERROR_CODE -> ToastUtils.toast(R.string.server_error)
                    ApiConfig.LACK_PARAMETER_CODE -> ToastUtils.toast(R.string.miss_parameter)
                    else -> ToastUtils.toast(R.string.server_error)
                }
            }
    }

    @MainThread
    fun onTokenError() {
        ToastUtils.toast(R.string.token_error_tips)
        GSPSharedPreferences.getInstance().clearUserInfo()
        ActivityManager.getInstance().exit()
        ARouter.getInstance()
            .build(ARouteAddress.ACCOUNT_ACT_LOGIN)
            .navigation()
    }

    private fun isNumeric(str: String?): Boolean {
        if (str.isNullOrBlank()) {
            return false
        }
        return str.isDigitsOnly()
    }
}
