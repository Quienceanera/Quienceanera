package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
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
    private String getEventKey;

    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        this.mQuery = ref;
    }

    @Override
    protected void populateViewHolder(final EventsViewholder viewHolder, final Events model, final int position) {
        Log.i(TAG,TAG);
//        final Animation animateIn = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);
//        viewHolder.mCardViewEvents.startAnimation(animateIn);
//        animateIn.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

        viewHolder.mTextViewEvents.setText(model.getEventName());
        viewHolder.mTextViewEventsDate.setText(model.getEventDate());
        viewHolder.mTextViewEventsZip.setText(model.getEventZip());

        viewHolder.mCardViewEventsHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventKey = model.getEventId();
                mEventName = model.getEventName();
                mEventDate = model.getEventDate();
                mEventName = model.getEventName();
                mEventAddress = model.getEventZip();
                mEventType = model.getEventType();
//                Bundle bundle = new Bundle();
//                bundle.putString("EventKey", getRef(position).getKey());
//                bundle.putString("EventDate", model.getEventDate());
//                bundle.putString("EventName", model.getEventName());
//                bundle.putString("EventZip", model.getEventZip());
//
//                Intent intent = new Intent(mContext, ConstantsUtil.class);
//                mContext.startActivity(intent, bundle);
//                FragmentUtil.getConsumerVendorProductItemFragment(bundle);

                Intent intent = new Intent(mContext, ConsumerActivity.class);
                intent.putExtra(getEventKey, getRef(position).getKey());
                mContext.startActivity(intent);

            }
        });
        viewHolder.mCardViewEventsHelper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEventKey = model.getEventId();
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setBackgroundColor(R.color.colorAccentLighter)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle(mContext.getString(R.string.delete)+" "+model.getEventName()+mContext.getString(R.string.and_all_its_content))
                        .setTitleColor(R.color.blue)
                        .setTextSubTitle(mContext.getString(R.string.for_string)+" "+model.getEventName())
                        .setNegativeButtonText(mContext.getString(R.string.cancel))
                        .setPositiveButtonText(mContext.getString(R.string.yes))
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
                            }
                        })
                        .build();
                alert.show();
                return false;
            }
        });

    }
}
