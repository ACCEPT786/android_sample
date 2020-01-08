package com.moon.notification.message;

import android.content.Context;
import android.content.Intent;

import com.umeng.message.entity.UMessage;

/**
 * @author ry
 * @date 2019-11-15
 */
public interface IMsgNotification {
    void showNotification(Context context, UMessage msg, Intent intent);
}
