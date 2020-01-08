package com.moon.libaccount.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.libaccount.R
import com.moon.libaccount.repo.AccountRepo
import com.moon.libbase.utils.extension.isEmail
import com.moon.libbase.utils.extension.isPhone
import com.moon.libbase.utils.ui.ToastUtils
import com.moon.libcommon.http.ApiConfig
import com.moon.libcommon.http.ResultProgressObserver
import javax.inject.Inject

/**
 * @author ry
 * @date 2019-07-02
 */
class CodeViewModel @Inject constructor(private val repo: AccountRepo):ViewModel() {

    //加载框状态
    val progressStatus = MutableLiveData<Boolean>()
    //倒计时
    val countDownTime = MutableLiveData<Int>()



    fun getPhoneCode(phoneNum:String?){
        if (!phoneNum.isPhone()) {
            ToastUtils.toast(R.string.phone_error)
            return
        }
        requestVerifyCode(ApiConfig.CODE_FUNC_PHONE,phoneNum!!)
    }

    fun getEmailCode(email:String?){
        if (email.isNullOrBlank()){
            ToastUtils.toast(R.string.empty_email)
            return
        }
        if (!email.isEmail()){
            ToastUtils.toast(R.string.error_email)
            return
        }
        requestVerifyCode(ApiConfig.CODE_FUNC_EMAIL,email)
    }



    /**
     * 获取验证码
     * @param type 类型 1.手机 2.邮箱
     * @param target 手机号或邮箱
     */
    private fun requestVerifyCode(type:Int,target: String) {
        repo.getVerificationCode(
            type,
            target,
            object : ResultProgressObserver<String>(progressStatus) {
                override fun onSuccess(result: String?) {
                    //发送成功,倒计时开始
                    codeCountdown()
                }

                override fun onInnerCodeError(code: Int, message: String) {
                    when (code) {
                        ApiConfig.BUSY_CODE -> ToastUtils.toast(R.string.code_frequent)
                        else -> super.onInnerCodeError(code, message)
                    }
                }
            })
    }

    fun codeCountdown() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownTime.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                countDownTime.value = -1
            }
        }.start()
    }
}