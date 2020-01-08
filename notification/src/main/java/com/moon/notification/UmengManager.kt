package com.moon.notification

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.moon.notification.message.PushMessageHandler
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.MsgConstant
import com.umeng.message.PushAgent
import org.android.agoo.huawei.HuaWeiRegister
import org.android.agoo.xiaomi.MiPushRegistar
import timber.log.Timber

/**
 * @author ry
 * @date 2019-12-13
 */
class UmengManager {
    companion object {
        @JvmStatic
        fun initUMengPush(context: Application, pushToken: MutableLiveData<String>) {
            UMConfigure.setLogEnabled(BuildConfig.DEBUG)
            UMConfigure.init(
                context,
                BuildConfig.umengKey,
                BuildConfig.FLAVOR,
                UMConfigure.DEVICE_TYPE_PHONE,
                BuildConfig.umengMsgSecret
            )

            val mPushAgent = PushAgent.getInstance(context)
            //resourcePackageName 资源包名，应与manifests文件中的package一致
            mPushAgent.resourcePackageName = BuildConfig.resourcePackageName
            //通知提示音和震动
            mPushAgent.notificationPlaySound = MsgConstant.NOTIFICATION_PLAY_SERVER
            mPushAgent.notificationPlayVibrate = MsgConstant.NOTIFICATION_PLAY_SERVER

            //厂商通道
            HuaWeiRegister.register(context)
            MiPushRegistar.register(context, BuildConfig.xiaomiId, BuildConfig.xiaomiKey)
            //注册推送服务，每次调用register方法都会回调该接口
            mPushAgent.register(object : IUmengRegisterCallback {
                override fun onSuccess(deviceToken: String) {
                    //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                    Timber.d("注册成功：deviceToken：-------->  $deviceToken")
                    pushToken.postValue(deviceToken)
                }

                override fun onFailure(s: String, s1: String) {
                    Timber.d("注册失败：-------->  s:$s,s1:$s1")
                }
            })

            mPushAgent.messageHandler =
                PushMessageHandler()

            registerActivity(context)
        }

        private fun registerActivity(application: Application) {
            application.registerActivityLifecycleCallbacks(object :
                com.moon.libbase.base.EmptyActivityLifecycleCallbacks() {
                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    super.onActivityCreated(activity, savedInstanceState)
                    PushAgent.getInstance(activity).onAppStart()
                }
            })
        }
    }
}