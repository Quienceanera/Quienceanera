package com.example.eventmakr.eventmakr.Utils;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ChatHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {
    public static String mVendorCategory, mVendorUid, mVendorKey;

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getEventsRef() {
        return getBaseRef().child("events").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getUserMessageRef() {
            return FirebaseUtil.getBaseRef().child("users").child(FirebaseUtil.getUid()).child("messages");
    }

    public static DatabaseReference getVendorMessageRef() {
        mVendorUid = ChatHomeAdapter.mVendorUid;
        mVendorUid = VendorAdapter.mVendorUid;
        return FirebaseUtil.getBaseRef().child("vendors").child(mVendorUid).child("messages");
    }

    public static DatabaseReference getUserChatHomeRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        mVendorUid = ChatHomeAdapter.mVendorUid;
        return getBaseRef().child("users").child(FirebaseUtil.getUid()).child("chat");
    }

    public static DatabaseReference getVendorChatHomeRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        mVendorUid = ChatHomeAdapter.mVendorUid;
//        return getBaseRef().child("vendors").child(mVendorUid).child("chat");
        return getBaseRef().child("vendors");
    }

    public static DatabaseReference getVendorRef() {
        return getBaseRef().child("vendor");
    }

    public static DatabaseReference getUserMenuRef () {
        mVendorUid = VendorAdapter.mVendorUid;
        if (mVendorUid != null) {
            return getBaseRef().child("menu").child(mVendorUid);
        }
        return null;
    }

    public static DatabaseReference getMenuRef () {
        mVendorUid = FirebaseUtil.getUid();
        if (mVendorUid != null) {
            return getBaseRef().child("menu").child(mVendorUid);
        }
        return null;
    }

    public static DatabaseReference getConsumerMenuRef () {
        mVendorUid = VendorAdapter.mVendorUid;
        if (mVendorUid != null) {
            return getBaseRef().child("menu").child(mVendorUid);
        }
        return null;
    }

    public static DatabaseReference getUserRef () {
        return getBaseRef().child("users/data");
    }

    public static String getUid () {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static  DatabaseReference getVendorCartRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        if (mVendorUid!= null) {
            return getBaseRef().child("vendors").child(mVendorUid).child("cart").child(FirebaseUtil.getUid());
        }
        return null;
    }
    public static  DatabaseReference getUserCartRef() {
            return getBaseRef().child("users").child(FirebaseUtil.getUid()).child("cartInfo");

    }

    public static  DatabaseReference getUserCartList() {
        mVendorUid = CartHomeAdapter.mVendorUid;
        return getBaseRef().child("users").child(FirebaseUtil.getUid()).child("cart");

    }

    public static  DatabaseReference getUserCartInfoRef() {
        return getBaseRef().child("users").child(FirebaseUtil.getUid()).child("cartInfo");

    }
    public static  DatabaseReference getVendorCartInfoRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        if (mVendorUid!= null) {
            return getBaseRef().child("vendors").child(mVendorUid).child("cartInfo").child(FirebaseUtil.getUid());
        }
        return null;
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
