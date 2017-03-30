package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.EventActivity;
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.github.florent37.viewanimator.ViewAnimator;
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
        String type = model.getEventType();
        switch (type) {
            case "Wedding":
                Glide.with(mContext)
                        .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fwedding.jpg?alt=media&token=c155bd76-6e1a-4fb6-b775-8ca72b893126")
                        .crossFade()
                        .centerCrop()
                        .into(viewHolder.mImageViewEvents);
                break;
            case "Quienceanera":
                Glide.with(mContext)
                        .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fmobile_bg.jpg?alt=media&token=8930f92d-f5f0-45dd-b9f9-51775faac1e2")
                        .crossFade()
                        .centerCrop()
                        .into(viewHolder.mImageViewEvents);
                break;
            case "Birthday":
                Glide.with(mContext)
                        .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbirthday.jpg?alt=media&token=9ae47551-bcbe-44cb-aaab-6fe2699311dd")
                        .crossFade()
                        .centerCrop()
                        .into(viewHolder.mImageViewEvents);
                break;
            case "Baby Shower":
                Glide.with(mContext)
                        .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbaby.jpg?alt=media&token=a804bad3-cb61-4690-91d4-879c031102a5")
                        .crossFade()
                        .centerCrop()
                        .into(viewHolder.mImageViewEvents);
                break;
            case "Graduation":
                Glide.with(mContext)
                        .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fgraduation.jpg?alt=media&token=d86c1680-0bfa-4349-b872-009a6097a530")
                        .crossFade()
                        .centerCrop()
                        .into(viewHolder.mImageViewEvents);
                break;
            default:
        }
                    ViewAnimator.animate(viewHolder.mCardViewEvents)
                            .slideBottom()
                            .duration(300)
                            .start();

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

        viewHolder.mImageViewEvents.setOnClickListener(new View.OnClickListener() {
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
        viewHolder.mImageViewEvents.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEventKey = getRef(position).getKey();
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle("Delete Event And All It's Content?")
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
                                restartActivity();
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
