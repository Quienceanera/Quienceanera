package com.example.eventmakr.eventmakr.Utils;

import android.util.Log;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.PreCartAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorProductsAdapter;

public abstract class DeleteUtil {
    public static void getDeleteEvent(){
        FirebaseUtil.getEventsRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderInfoRef().removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderRef().removeValue();
    }

    public static void deleteProductItem(){
        FirebaseUtil.getVendorSideVendorProductRef().child(VendorProductsAdapter.mProductKey).removeValue();
    }

    public static void deleteOrderListItem(){
        Log.i("Delete: Cart Item Key", PreCartAdapter.mPreCartItemKey +" "+PreCartAdapter.mVendorUid);
        FirebaseUtil.getConsumerSideVendorOrderRef().child(PreCartAdapter.mPreCartItemKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderRef().child(PreCartAdapter.mVendorUid).child(PreCartAdapter.mPreCartItemKey).removeValue();
    }

    public static void deleteOrderHomeItem(){
        FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(CartHomeAdapter.mVendorUid).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderRef().child(CartHomeAdapter.mVendorUid).removeValue();
        FirebaseUtil.getConsumerSideVendorOrderInfoRef().removeValue();
        FirebaseUtil.getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid).removeValue();
    }

    public static void deleteVendorOrderHomeItem(){
        FirebaseUtil.getVendorSideVendorOrderHomeRef().child(VendorOrderHomeAdapter.mOrderHomeKey).removeValue();
        FirebaseUtil.getVendorSideVendorOrderListRef().removeValue();
        FirebaseUtil.getVendorSideVendorMessageRef().removeValue();

    }
}
