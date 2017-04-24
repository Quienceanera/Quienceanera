package com.example.eventmakr.eventmakr.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityOptionsCompat;
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

import java.util.Locale;

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsViewholder>{
    private static final String TAG = EventsAdapter.class.getSimpleName();
    private Context mContext;
    private Typeface mTypeFace;
    private AssetManager mAssetManager;
    public static String mEventKey, mEventName, mEventDate, mEventAddress, mEventType, mVendorUid;
    private View mTransitionView;
    private Query mQuery;
    private String getEventKey;

    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        this.mQuery = ref;
        this.mAssetManager = mContext.getAssets();
        this.mTypeFace = Typeface.createFromAsset(mAssetManager, String.format(Locale.US, "fonts/%s", "Calligraffitti-Regular.ttf"));
    }

    @Override
    protected void populateViewHolder(final EventsViewholder viewHolder, final Events model, final int position) {
        Log.i(TAG,TAG);

        mTransitionView = viewHolder.mCardViewEvents.findViewById(R.id.cardViewEvents);

        viewHolder.mTextViewEvents.setText(model.getEventName());
        viewHolder.mTextViewEvents.setTypeface(mTypeFace);
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
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, mTransitionView, "eventsTransition");
                intent.putExtra(getEventKey, getRef(position).getKey());
                mContext.startActivity(intent, activityOptions.toBundle());

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
