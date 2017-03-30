package com.example.eventmakr.eventmakr.Utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    private static final String TOPIC = " ";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // Once a token is generated, we subscribe to topic.
        FirebaseMessaging.getInstance()
                .subscribeToTopic(TOPIC);
    }
}
