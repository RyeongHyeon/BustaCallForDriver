package com.example.user.bustacallfordriver.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.view.Activity_Main;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by user on 2016-10-31.
 */
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    AppController app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = (AppController) getApplicationContext();
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //추가한것
        String title;
        String message;
        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();

        sendNotification(title, message);
    }

    public void setIntent(Intent intent, String title, String messageBody) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("FCM Push Test")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification(String title, String messageBody) {
        Intent intent = null;

        if (app != null) {//실행중
            intent = new Intent(this, Activity_Main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            setIntent(intent, title, messageBody);

        } else {//앱 꺼졌을 때
            intent = new Intent(this, Activity_Main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            setIntent(intent, title, messageBody);

        }
    }
//    public static boolean isRunningProcess(Context context, String packageName) {
//
//        boolean isRunning = false;
//
//        ActivityManager actMng = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//
//        List<ActivityManager.RunningAppProcessInfo> list = actMng.getRunningAppProcesses();
//
//        for (ActivityManager.RunningAppProcessInfo rap : list) {
//            if (rap.processName.equals(packageName)) {
//                isRunning = true;
//                break;
//            }
//        }
//
//        return isRunning;
//    }
}
