package com.moon.teachassistant.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.libcommon.sp.GSPSharedPreferences
import com.moon.libcommon.sp.KTPreference
import javax.inject.Inject

/**
 * @author ry
 * @date 2019-05-27
 */
class SplashViewModel @Inject constructor(/*private val repo: IAccountRepo,private val  homeWorkrepo: IHomeWorkRepo*/) : ViewModel() {

    val uid by KTPreference(GSPSharedPreferences.KEY_UID, "")
    val token by KTPreference(GSPSharedPreferences.KEY_TOKEN, "")
    val startWelcome = MutableLiveData<Boolean>()
    val startMain = MutableLiveData<Boolean>()
    val uType by KTPreference(GSPSharedPreferences.KEY_IDEN, 0)

    fun checkLogin() {
        if (uid.isBlank() || uType == 0) {
            startWelcome.value = true
            return
        }
        loginWithCache(uType, token)
    }

    private fun loginWithCache(uType:Int, token: String) {
//        repo.tokenLogin(uType,token,object:CommonObserver<UserInfo>(){
//            override fun onSuccess(result: UserInfo?) {
//                if (result!=null){
//                    result.userType = uType
//                    GSPSharedPreferences.getInstance().userInfo = result
//                    //初始化suid
//                    GSPSharedPreferences.getInstance().setStudentId(KEY_SUID_DEFAULT)
//                }
//                startMain.value = true
//            }
//
//            override fun onRequestEnd() {
//                if (startMain.value!=true){
//                    startWelcome.value = true
//                }
//            }
//
//            override fun onInnerCodeError(code: Int, message: String) {
//                if (code== ApiConfig.IMSI_EMPTY){
//                    ToastUtils.toast(R.string.input_sdcard)
//                }else {
//                    super.onInnerCodeError(code, message)
//                }
//            }
//        })
    }
}