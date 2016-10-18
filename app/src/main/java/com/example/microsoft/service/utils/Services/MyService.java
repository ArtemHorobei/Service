package com.example.microsoft.service.utils.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.microsoft.service.R;
import com.example.microsoft.service.UI.MainActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by Microsoft on 18.10.2016.
 */
public class MyService extends Service {
    NotificationManager nm;
    Notification notif;

    public MyService() {
    }

    @Override
    public void onCreate() {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }
    void sendNotif() {
        notif = new Notification();
        Intent intent = new Intent(this, ServiceDownload.class);
        intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pIntent = PendingIntent.getService(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(MyService.this);
        builder.setAutoCancel(false);
        builder.setTicker("-------");
        builder.setContentTitle("Download");
        builder.setContentText("Images");
        builder.setSmallIcon(R.drawable.img_1);
        builder.setContentIntent(pIntent);
        builder.setOngoing(true);
        builder.setSubText("This is subtext...");
        builder.setNumber(100);
        builder.build();
        notif = builder.getNotification();
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.number = 3;
        nm.notify(1, notif);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
