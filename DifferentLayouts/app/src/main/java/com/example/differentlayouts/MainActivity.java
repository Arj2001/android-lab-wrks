package com.example.differentlayouts;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    Button notifBtn;
    String channelId = "my_channel_id";
    String channelName = "App Notification";

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelId )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Notification Title")
            .setContentText("Hey this a notifcation you just created by clicking the button")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.RED);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_notification);

        createNotificationChannel();



        notifBtn = findViewById(R.id.notifcationBtn);

        notifBtn.setOnClickListener(v->{
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        101
                );

            }
            notificationManager.notify(12, builder.build());
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
            );
            // Allow vibration for notifications

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}