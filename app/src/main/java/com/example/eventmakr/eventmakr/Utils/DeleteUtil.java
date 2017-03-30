package com.example.eventmakr.eventmakr.Utils;

import android.util.Log;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.CartListAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.MenuAdapter;

public abstract class DeleteUtil {
    public static void getDeleteEvent(){
        FirebaseUtil.getEventsRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderInfoRef().removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderRef().removeValue();
    }

    public static void deleteProductItem(){
        FirebaseUtil.getVendorSideVendorProductRef().child(MenuAdapter.mProductKey).removeValue();
    }

    public static void deleteOrderListItem(){
        Log.i("Delete: Cart Item Key", CartListAdapter.mCartItemKey +" "+CartListAdapter.mVendorUid);
        FirebaseUtil.getConsumerSideVendorOrderRef().child(CartListAdapter.mCartItemKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderRef().child(CartHomeAdapter.mVendorUid).child(CartListAdapter.mCartItemKey).removeValue();
    }

    public static void deleteOrderHomeItem(){
        FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(CartHomeAdapter.mVendorUid).removeValue();
        FirebaseUtil.getConsumerSideVendorOrderInfoRef().removeValue();
    }
}
