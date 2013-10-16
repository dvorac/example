package com.dvorac.android.poc.notifications;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private AlarmManager mAM;
    private PendingIntent mAlarm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate layout
        setContentView(R.layout.main);

        // get system AlarmManager
        mAM = (AlarmManager)getSystemService(ALARM_SERVICE);

        // construct intent to start notification service on alarm
        mAlarm = PendingIntent.getService(
                getApplicationContext(),
                0,
                new Intent(getApplicationContext(), NotificationService.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void postNotification(View v) {
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
    }

    public void startAlarm(View v) {
        // start alarm
        mAM.setInexactRepeating(AlarmManager.RTC, 10000, 10000, mAlarm);
    }

    public void stopAlarm(View v) {
        // stop alarm
        mAM.cancel(mAlarm);
    }
}