package com.moon.notification.message

import android.content.Context
import com.umeng.message.UmengMessageHandler
import com.umeng.message.entity.UMessage

/**
 * @author ry
 * @date 2019-11-22
 */
class PushMessageHandler : UmengMessageHandler() {
    override fun dealWithNotificationMessage(context: Context?, msg: UMessage) {
        try {
            //todo
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return super.dealWithNotificationMessage(context, msg)
    }

}