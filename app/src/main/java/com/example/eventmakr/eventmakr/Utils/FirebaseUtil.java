package com.example.eventmakr.eventmakr.Utils;

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

    public static DatabaseReference getChatRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        return FirebaseDatabase.getInstance().getReference().child("chat").child(FirebaseUtil.getUid()).child(mVendorUid);
    }

    public static DatabaseReference getChatHomeRef() {
        return FirebaseDatabase.getInstance().getReference().child("chat").child(FirebaseUtil.getUid());
    }

    public static DatabaseReference getVendorRef() {
        return getBaseRef().child("vendor");
    }

    public static DatabaseReference getMenuRef () {
        mVendorUid = FirebaseUtil.getUid();

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

    public static  DatabaseReference getCartRef() {
        mVendorUid = VendorAdapter.mVendorUid;
        if (mVendorUid!= null) {
            return getBaseRef().child("cart").child(FirebaseUtil.getUid()).child(mVendorUid);
        }
        return null;
    }

//    public static DatabaseReference getOrderRef () {
//        mVendorUid = VendorProfileProductAdapter.mVendorUid;
//        if (mVendorKey != null) {
//            return getBaseRef().child("cart").child(FirebaseUtil.getUid()).child(mVendorKey);
//        }
//        return null;
//    }

    public static FirebaseUser getUser () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }
    public static String getUserName () {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return firebaseUser.getDisplayName();
    }
}
