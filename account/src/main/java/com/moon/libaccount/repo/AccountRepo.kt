package com.moon.libaccount.repo

import com.moon.libaccount.http.AccountNet
import com.moon.libbase.base.BaseRepo
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.utils.extension.ioScheduler
import com.moon.libcommon.entity.UserInfo
import com.moon.libcommon.http.CommonObserver
import com.moon.libcommon.sp.GSPSharedPreferences
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author ry
 * @date 2019-05-15
 */
@Singleton
class AccountRepo @Inject constructor() : BaseRepo() {

    @Inject
    lateinit var accountNet: AccountNet


    private val getUserInfo = Function<HttpResult<UserInfo>, Observable<HttpResult<UserInfo>>> {
        val info = it.data
        if (it.code == 200 && info != null) {
            GSPSharedPreferences.getInstance().uid = info.uid.toString()
            GSPSharedPreferences.getInstance().token = info.token.toString()
            return@Function accountNet.getUserInfo()
        }
        return@Function Observable.just(it)
    }
    /**
     * 手机号密码登录
     * @param uType 1学生 2老师 3家长
     * @param phone 手机号
     * @param pwd 密码
     * @param resultObserver 回调方法
     * @return code:200 success
     *         code:4001 账号或是密码错误
     */
    fun phonePwdLogin(
        uType: Int,
        phone: String,
        pwd: String,
        resultObserver: CommonObserver<UserInfo>
    ) {
        accountNet.phonePwdLogin(uType, phone, pwd).flatMap(getUserInfo).ioScheduler()
            .subscribe(resultObserver)
    }




    /**
     * 验证码登录
     * @param uType 1学生 2老师 3家长
     * @param phone 手机号
     * @param code 验证码
     * @param resultObserver 回调方法
     * @return code:200 success
     *         code:4003 验证码错误
     */
    fun codeLogin(
        uType: Int,
        phone: String,
        code: String,
        resultObserver: CommonObserver<UserInfo>
    ) {
        accountNet.codeLogin(uType, phone, code).flatMap(getUserInfo).ioScheduler().subscribe(resultObserver)
    }

    /**
     * token登录
     * @param uType 1学生 2老师 3家长
     * @param token token
     * @param resultObserver 回调方法
     * @return code:200 success
     */
    fun tokenLogin(uType: Int, token: String, resultObserver: CommonObserver<UserInfo>) {
        accountNet.tokenLogin(uType, token).flatMap(getUserInfo).ioScheduler().subscribe(resultObserver)
    }

    /**
     * 检查手机号是否已注册并获取验证码
     * @param phone 手机号
     * @param uType 1学生 2老师 3家长
     * @param resultObserver 回调方法
     * @return code:200 success
     * //todo
     */
    fun checkPhoneIsRegistered(phone: String, uType: Int, resultObserver: CommonObserver<String>) {
//        accountNet.checkPhoneIsRegistered(phone, uType).flatMap map@{
//            if (it.code == SUCCESS_CODE) {
//                if (it.data == true) {
//                    return@map accountNet.getVerificationCode(CODE_FUNC_PHONE, phone)
//                } else {
//                    return@map Observable.error<HttpResult<String>>(
//                        Throwable(
//                            ACCOUNT_NOT_EXIST_CODE.toString(),
//                            Throwable(it.msg)
//                        )
//                    )
//                }
//            }
//            Observable.error<HttpResult<String>>(Throwable(it.code.toString(), Throwable(it.msg)))
//        }.ioScheduler().subscribe(resultObserver)
    }

    /**
     * 获取验证码(支持邮箱和手机)
     * @param func 1.手机 2.邮箱
     * @param send 手机号/邮箱
     * @return code:200 success
     *         code:4002 系统繁忙
     */
    fun getVerificationCode(func: Int, send: String, resultObserver: CommonObserver<String>) {
        accountNet.getVerificationCode(func, send).ioScheduler().subscribe(resultObserver)
    }






}