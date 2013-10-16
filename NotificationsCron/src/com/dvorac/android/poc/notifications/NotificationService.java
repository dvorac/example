package com.dvorac.android.poc.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class NotificationService extends Service {
    public static final String TAG = NotificationService.class.getSimpleName();

    private NotificationManager mNM;
    private int count = 0;
    private int ID = 34734768;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        showNotification(++count);
        return START_STICKY;
    }

    private void showNotification(int count) {
        // construct notification
        Notification n = new Notification.Builder(this)
                .setNumber(count)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setTicker("new notification!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("New Notif Title")
                .setContentText("Content text here")
                .build();

        // publish notification
        mNM.notify(ID, n);
    }
}
