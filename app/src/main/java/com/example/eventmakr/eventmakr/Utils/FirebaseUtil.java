package com.example.eventmakr.eventmakr.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getChatRef() {
        return FirebaseDatabase.getInstance().getReference().child("Chat");
    }

    public static DatabaseReference getVendorRef() {
        return getBaseRef().child("vendor");
    }

    public static DatabaseReference getMenuRef () {
        return getVendorRef().child("menu");
    }

    public static DatabaseReference getUserRef () {
        return getBaseRef().child("users/data");
    }

    public static String getUid () {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static  DatabaseReference getItemRef () {
        return getBaseRef().child("item");
    }

    public static FirebaseUser getUser () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }
    public static String getUserName () {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser.getDisplayName();
    }
}
