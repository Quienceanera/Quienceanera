package com.example.eventmakr.eventmakr.Utils;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ChatHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseUser getUser () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }
    public static String getUserName () {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser.getDisplayName();
    }
    public static String getUid () {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static DatabaseReference getVendorRef() {
        return getBaseRef().child("vendor");
    }

    //TODO: Consumer Side
    public static DatabaseReference getEventsRef() {
        return getBaseRef().child("consumerEvent").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getConsumerSideConsumerMessageRef() {
        return getBaseRef().child("consumerMessage").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getConsumerSideConsumerChatRef() {
        return getBaseRef().child("consumerChat").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getConsumerSideVendorChatRef() {
        return getBaseRef().child("vendorChat").child(VendorAdapter.mVendorUid);
    }

    public static DatabaseReference getConsumerSideVendorMessageRef() {
        return getBaseRef().child("vendorMessage").child(VendorAdapter.mVendorUid);
    }

    public static DatabaseReference getConsumerSideVendorProductRef() {
        return getBaseRef().child("consumerProduct").child(VendorAdapter.mVendorUid);
    }

    public static  DatabaseReference getConsumerSideVendorOrderRef() {
            return getBaseRef().child("vendorOrder").child(VendorAdapter.mVendorUid).child(EventsAdapter.mEventKey);
    }

    public static  DatabaseReference getConsumerSideConsumerOrderRef() {
        return getBaseRef().child("consumerOrder").child(FirebaseUtil.getUid()).child(CartHomeAdapter.mVendorUid);
    }

    public static  DatabaseReference getConsumerSideConsumerOrderInfoRef() {
            return getBaseRef().child("consumerOrderInfo").child(FirebaseUtil.getUid()).child(EventsAdapter.mEventKey);
    }
    public static  DatabaseReference getConsumerSideVendorOrderInfoRef() {
            return getBaseRef().child("vendorOrderInfo").child(VendorAdapter.mVendorUid).child(EventsAdapter.mEventKey);
    }

    //TODO: Vendor Side

    public static DatabaseReference getVendorSideVendorProductRef() {
        return getBaseRef().child("vendorProduct").child(getUid());
    }

    public static DatabaseReference getVendorSideVendorChatRef() {
        return getBaseRef().child("vendorChat").child(getUid());
    }

    public static DatabaseReference getVendorSideVendorMessageRef() {
        return getBaseRef().child("vendorMessage").child(getUid()).child(ChatHomeAdapter.mConsumerUid);
    }

    public static  DatabaseReference getVendorSideConsumerMessageRef() {
        return getBaseRef().child("consumerMessage").child(ChatHomeAdapter.mConsumerUid).child(ChatHomeAdapter.mEventKey).child(getUid());
    }

    public static  DatabaseReference getVendorSideVendorOrderHomeRef() {
        return getBaseRef().child("vendorOrder").child(FirebaseUtil.getUid());
    }
    public static DatabaseReference getVendorSideVendorOrderListRef() {
        return getBaseRef().child("vendorOrderList").child(getUid()).child(VendorOrderHomeAdapter.mEventKey);
    }

}