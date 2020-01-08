package com.moon.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class NotificationRepo {

    public static Notification getMessageNotification(Context context, String channelID, String title, String content, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);
        PendingIntent pi = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //todo change logo
        return builder.setSmallIcon(R.drawable.notify_logo)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pi)
                .build();
    }

    public static void createNotificationChannel(
            Context context, String id, String name, int importance) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager.getNotificationChannel(id) == null) {
                NotificationChannel channel =
                        new NotificationChannel(id, name, importance);
                channel.enableLights(true);

                channel.setSound(null, null);
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                notificationManager.createNotificationChannel(channel);
            }
        }
    }


}
