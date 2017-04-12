package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.EventActivity;
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.Query;

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsViewholder>{
    private static final String TAG = EventsAdapter.class.getSimpleName();
    private Context mContext;
    public static String mEventKey, mEventName, mEventDate, mEventAddress, mEventType, mVendorUid;
    private Query mQuery;

    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        this.mQuery = ref;
    }

    @Override
    protected void populateViewHolder(final EventsViewholder viewHolder, final Events model, final int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewEvents.setText(model.getEventName());
        viewHolder.mTextViewEventsDate.setText(model.getEventDate());
        viewHolder.mTextViewEventsZip.setText(model.getEventZip());

        viewHolder.mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventKey = getRef(position).getKey();
                mEventName = model.getEventName();
                mEventDate = model.getEventDate();
                mEventName = model.getEventName();
                mEventAddress = model.getEventZip();
                mEventType = model.getEventType();
                getEventsActivity();
            }
        });

        viewHolder.mCardViewEventsHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventKey = getRef(position).getKey();
                mEventName = model.getEventName();
                mEventDate = model.getEventDate();
                mEventName = model.getEventName();
                mEventAddress = model.getEventZip();
                mEventType = model.getEventType();
                restartActivity();
            }
        });
        viewHolder.mCardViewEventsHelper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEventKey = getRef(position).getKey();
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setBackgroundColor(R.color.colorAccentLighter)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle("Delete "+model.getEventName()+" And All It's Content?")
                        .setTitleColor(R.color.blue)
                        .setTextSubTitle("For: "+model.getEventName())
                        .setNegativeButtonText("Cancel")
                        .setPositiveButtonText("Yes")
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                DeleteUtil.getDeleteEvent();
                                dialog.dismiss();
//                                restartActivity();
                            }
                        })
                        .build();
                alert.show();
                return false;
            }
        });

    }

    private void getEventsActivity() {
        mContext.startActivity(new Intent(mContext, EventActivity.class));
    }

    private void restartActivity(){
        mContext.startActivity(new Intent(mContext, ConsumerActivity.class));
    }
}
