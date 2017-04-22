package com.example.eventmakr.eventmakr.Utils;

import android.util.Log;

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

    public static DatabaseReference getFeedbackRef(){
        return getBaseRef().child("feedback").child(FirebaseUtil.getUid());
    }


    //TODO: Consumer Side

    public static DatabaseReference getConsumerSideVendorPlaceRef(){
        return getBaseRef().child("vendorPlaceId").child(VendorAdapter.mVendorUid);
    }

    public static DatabaseReference getNotificationRef(){
        return getBaseRef().child("notifications");
    }

    public static DatabaseReference getConsumerProfileRef(){
        return getBaseRef().child("consumerProfile").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getEventsRef() {
        return getBaseRef().child("consumerEvent").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getConsumerSideConsumerMessageRef() {
        return getBaseRef().child("consumerMessage").child(FirebaseUtil.getUid()).child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid);
    }

    public static DatabaseReference getConsumerSideConsumerChatRef() {
        return getBaseRef().child("consumerChat").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getConsumerSideVendorChatRef() {
        if (CartHomeAdapter.mVendorUid != null){
            return getBaseRef().child("vendorChat").child(CartHomeAdapter.mVendorUid);
        }
        return null;
    }

    public static DatabaseReference getConsumerSideVendorMessageRef() {
        if (CartHomeAdapter.mVendorUid != null){
            Log.i("CSideVMessageRef", CartHomeAdapter.mVendorUid);
            return getBaseRef().child("vendorMessage").child(CartHomeAdapter.mVendorUid).child(getUid()).child(EventsAdapter.mEventKey);
        }else {
            Log.i("CSideVMessageRef", ChatHomeAdapter.mVendorUid);
            return getBaseRef().child("vendorMessage").child(CartHomeAdapter.mVendorUid).child(getUid()).child(EventsAdapter.mEventKey);
        }
    }
    public static DatabaseReference getConsumerSideVendorProductRef() {
        return getBaseRef().child("vendorProduct").child(VendorAdapter.mVendorUid);
    }

    public static  DatabaseReference getConsumerSideVendorOrderRef() {
        if (VendorAdapter.mVendorUid != null) {
            return getBaseRef().child("vendorOrder").child(VendorAdapter.mVendorUid).child(EventsAdapter.mEventKey);
        }
        if (CartHomeAdapter.mVendorUid != null) {
            return getBaseRef().child("vendorOrder").child(CartHomeAdapter.mVendorUid).child(EventsAdapter.mEventKey);
        }
        return null;
    }

    public static  DatabaseReference getConsumerSideConsumerOrderRef() {
        return getBaseRef().child("consumerOrder").child(FirebaseUtil.getUid()).child(EventsAdapter.mEventKey);
    }

    public static  DatabaseReference getConsumerSideConsumerOrderInfoRef() {
            return getBaseRef().child("consumerOrderInfo").child(FirebaseUtil.getUid()).child(EventsAdapter.mEventKey);
    }
    public static  DatabaseReference getConsumerSideVendorOrderInfoRef() {
        if (VendorAdapter.mVendorUid != null){
            return getBaseRef().child("vendorOrderInfo").child(VendorAdapter.mVendorUid).child(EventsAdapter.mEventKey);
        }
        if (CartHomeAdapter.mVendorUid != null){
            return getBaseRef().child("vendorOrderInfo").child(CartHomeAdapter.mVendorUid).child(EventsAdapter.mEventKey);
        }
        return null;
    }


    //TODO: Vendor Side

    public static DatabaseReference getVendorPlaceRef(){
        return getBaseRef().child("vendorPlaceId").child(getUid());
    }

    public static DatabaseReference getVendorSideVendorProductRef() {
        return getBaseRef().child("vendorProduct").child(getUid());
    }

    public static DatabaseReference getVendorSideVendorChatRef() {
        return getBaseRef().child("vendorChat").child(getUid());
    }

    public static DatabaseReference getVendorSideVendorMessageRef() {
        return getBaseRef().child("vendorMessage").child(getUid()).child(VendorOrderHomeAdapter.mCustomerUid).child(VendorOrderHomeAdapter.mEventKey);
    }

    public static  DatabaseReference getVendorSideConsumerMessageRef() {
//        Log.i("VSideCMessageRef", ChatHomeAdapter.mConsumerUid+" "+ChatHomeAdapter.mEventKey);
        return getBaseRef().child("consumerMessage").child(VendorOrderHomeAdapter.mCustomerUid).child(VendorOrderHomeAdapter.mEventKey).child(getUid());
    }

    public static  DatabaseReference getVendorSideVendorOrderHomeRef() {
        return getBaseRef().child("vendorOrderInfo").child(FirebaseUtil.getUid());
    }

    public static  DatabaseReference getVendorSideConsumerOrderInfoRef() {
        return getBaseRef().child("consumerOrderInfo").child(VendorOrderHomeAdapter.mCustomerUid).child(VendorOrderHomeAdapter.mEventKey).child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getVendorSideVendorOrderListRef() {
        return getBaseRef().child("vendorOrder").child(getUid()).child(VendorOrderHomeAdapter.mEventKey);
    }

    public static DatabaseReference getVendorSideProfileRef(){
        return getBaseRef().child("vendorProfile").child(getUid());
    }

}