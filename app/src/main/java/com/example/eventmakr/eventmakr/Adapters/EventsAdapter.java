package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsViewholder>{
    private Context mContext;


    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final EventsViewholder viewHolder, final Events model, final int position) {


    }

    private void getCart() {
        ConsumerActivity consumerActivity = (ConsumerActivity)mContext;
        consumerActivity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getCartDetailFragment())
                .addToBackStack(null)
                .commit();
    }
}
