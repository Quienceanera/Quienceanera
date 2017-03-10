package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsViewholder>{
    private Context mContext;
    public static String mEventKey, mEventName, mEventDate;


    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final EventsViewholder viewHolder, final Events model, final int position) {
        viewHolder.mTextViewEvents.setText(model.getEventType());
        viewHolder.mTextViewEventsDate.setText(model.getEventDate());
        String type = model.getEventType();
        Log.i("id", String.valueOf(type));
        switch (type) {
            case "Wedding":
                viewHolder.mImageViewEvents.setImageResource(R.drawable.wedding);
                break;
            case "Quienceanera":
                viewHolder.mImageViewEvents.setImageResource(R.drawable.q);
                break;
            case "Birthday":
                viewHolder.mImageViewEvents.setImageResource(R.drawable.birthday);
                break;
            case "Baby Shower":
                viewHolder.mImageViewEvents.setImageResource(R.drawable.baby);
                break;
            case "Graduation":
                viewHolder.mImageViewEvents.setImageResource(R.drawable.graduation);
                break;
            default:
        }
        viewHolder.mCardViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventKey = getRef(position).getKey();

                getCategory();
            }
        });
        mEventDate = model.getEventDate();
        mEventName = model.getEventName();
    }

    private void getCategory() {
        ConsumerActivity consumerActivity = (ConsumerActivity)mContext;
        consumerActivity.findViewById(R.id.fabNewEvent).setVisibility(View.GONE);
        consumerActivity.findViewById(R.id.containerEventsList).setVisibility(View.GONE);
        consumerActivity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorCategoryFragment())
                .addToBackStack(null)
                .commit();
    }
}
