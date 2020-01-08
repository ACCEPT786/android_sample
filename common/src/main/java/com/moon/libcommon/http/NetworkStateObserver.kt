package com.moon.libcommon.http

import androidx.lifecycle.MutableLiveData
import com.moon.libbase.base.net.NetworkState

/**
 * @author ry
 * @date 2019-12-24
 */
abstract class NetworkStateObserver<T>(private val netStatus: MutableLiveData<NetworkState>) : CommonObserver<T>() {

    override fun onRequestStart() {
        super.onRequestStart()
        netStatus.value = NetworkState.LOADING
    }

    override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
        super.onFailure(e, isNetWorkError)
        netStatus.value = NetworkState.FAILED
    }

    override fun onInnerCodeError(code: Int, message: String) {
        super.onInnerCodeError(code, message)
        netStatus.value = NetworkState.FAILED
    }



}