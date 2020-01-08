package com.moon.libaccount.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.libaccount.R
import com.moon.libaccount.repo.AccountRepo
import com.moon.libbase.utils.FilterUtils
import com.moon.libbase.utils.ui.ToastUtils
import com.moon.libcommon.entity.UserInfo
import com.moon.libcommon.http.ApiConfig
import com.moon.libcommon.http.ResultProgressObserver
import com.moon.libcommon.sp.GSPSharedPreferences
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repo: AccountRepo
) : ViewModel() {
    /**
     * 登录成功回调
     */
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    //加载框
    val progressStatus = MutableLiveData<Boolean>()

    /**
     * 手机密码登陆
     */
    fun phonePwdLogin(phone:String?,pwd:String?) {
        //验证手机号
        if (!FilterUtils.isPhone(phone)) {
            ToastUtils.toast(R.string.phone_error)
            return
        }
        //验证密码
        if (!FilterUtils.isPassword(pwd)) {
            ToastUtils.toast(R.string.illegal_password)
            return
        }

        repo.phonePwdLogin(
            0,
            phone!!,
            pwd!!,
            object : ResultProgressObserver<UserInfo>(progressStatus) {
                override fun onSuccess(result: UserInfo?) {
                    result?.let {
                        GSPSharedPreferences.getInstance().userInfo = it
                        loginResult.value = true
                    }
                }

                override fun onInnerCodeError(code: Int, message: String) {
                    when (code) {
                        ApiConfig.PWD_ERROR_CODE -> ToastUtils.toast(R.string.code_login_error)
                        ApiConfig.TEACHER_NOT_EXIT -> {
                            ToastUtils.toast(R.string.code_account_not_exit)
                        }
                        else -> super.onInnerCodeError(code, message)
                    }
                }

            })
    }

    /**
     * 手机号验证码登录
     */
    fun phoneCodeLogin(phone:String?,code:String?){
        //验证手机号
        if (!FilterUtils.isPhone(phone)) {
            ToastUtils.toast(R.string.phone_error)
            return
        }
        //验证验证码
        if (code.isNullOrEmpty()){
            ToastUtils.toast(R.string.phone_code_empty)
            return
        }
        repo.codeLogin(
            0,
            phone!!,
            code,
            object : ResultProgressObserver<UserInfo>(progressStatus) {
                override fun onSuccess(result: UserInfo?) {
                    result?.let {
                        GSPSharedPreferences.getInstance().userInfo = it
                        loginResult.value = true
                    }
                }

                override fun onInnerCodeError(code: Int, message: String) {
                    when (code) {
                        ApiConfig.ERROR_CODE -> {
                            ToastUtils.toast(R.string.code_error_verify)
                        }
                        ApiConfig.TEACHER_NOT_EXIT -> {
                            ToastUtils.toast(R.string.code_account_not_exit)
                        }
                        else -> super.onInnerCodeError(code, message)
                    }
                }

            })
    }
}
