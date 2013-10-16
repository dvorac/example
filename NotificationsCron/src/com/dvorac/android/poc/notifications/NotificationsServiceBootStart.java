package com.dvorac.android.poc.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationsServiceBootStart extends BroadcastReceiver {
    public static final String TAG = NotificationsServiceBootStart.class.getSimpleName();
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onBootComplete: starting " + NotificationService.TAG);

        // construct intent to start notification service on alarm
        PendingIntent alarm = PendingIntent.getService(
                context,
                0,
                new Intent(context, NotificationService.class),
                PendingIntent.FLAG_CANCEL_CURRENT);

        // start alarm
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC, 10000, 10000, alarm);
    }
}
