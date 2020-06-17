package com.muneiah.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
NotificationManager mNotificationManger;
public static final String CHANNEL_ID="muneiah.tellakula.apssdc.android";
public static final String NOTIFICATION_ID="MyNotification";
PendingIntent pi;
Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationCannel();
        i=new Intent(this,MainActivity.class);
        pi=PendingIntent.getActivity(this,12,i,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void sendMyNotification(View view) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setContentTitle("APSSDC ANDROID NOTIFICATION..!");
        builder.setContentText("Please Mute your micro phonses then follow the class..");
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pi);
        builder.addAction(R.drawable.ic_replay_black_24dp,"Reply",pi);
        mNotificationManger.notify(3483,builder.build());

    }
    public void createNotificationCannel(){
        mNotificationManger= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"muni",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            mNotificationManger.createNotificationChannel(notificationChannel);
        }
    }
}
