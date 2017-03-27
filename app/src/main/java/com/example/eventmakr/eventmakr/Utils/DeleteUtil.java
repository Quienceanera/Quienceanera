package com.example.eventmakr.eventmakr.Utils;

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;

public abstract class DeleteUtil {
    public static void getDeleteEvent(){
        FirebaseUtil.getEventsRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).removeValue();
        FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(EventsAdapter.mEventKey).removeValue();
    }
}
