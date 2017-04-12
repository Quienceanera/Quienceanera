package com.example.eventmakr.eventmakr.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.eventmakr.eventmakr.Activities.MainActivity;
import com.example.eventmakr.eventmakr.R;
import com.google.firebase.messaging.RemoteMessage;

import static android.graphics.Color.rgb;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "MyFMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " +
                remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());

        String notificationTitle = null, notificationBody = null;

        //Check if message contains a notification payload
        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Data is not null" + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: "+ remoteMessage.getNotification().getBody());
            notificationBody = remoteMessage.getNotification().getBody();
            notificationTitle = remoteMessage.getNotification().getTitle();
        }

        sendNotification(notificationTitle, notificationBody);
    }

    private void sendNotification(String notificationTitle, String notificationBody) {
        Log.e(TAG, "sendNotification");
//        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
//            Intent intent = new Intent(this, VendorActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                    .setAutoCancel(false)
//                    .setStyle(new NotificationCompat.BigTextStyle())
//                    .setSmallIcon(R.drawable.logoi)
//                    .setContentIntent(pendingIntent)
//                    .setContentTitle(notificationTitle)
//                    .setContentText(notificationBody)
//                    .setColor(rgb(135, 193, 239))
//                    .setSound(defaultSoundUri);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(0, notificationBuilder.build());
//
//        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSmallIcon(R.drawable.logoi)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationBody)
                    .setColor(rgb(135, 193, 239))
                    .setSound(defaultSoundUri);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
//        }



    }

}
