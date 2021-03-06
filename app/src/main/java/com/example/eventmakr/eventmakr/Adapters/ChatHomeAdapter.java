package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Objects.ChatHome;
import com.example.eventmakr.eventmakr.ViewHolders.ChatHomeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class ChatHomeAdapter extends FirebaseRecyclerAdapter<ChatHome, ChatHomeViewholder>{
    private static final String TAG = ChatHomeAdapter.class.getSimpleName();
    private Context mContext;
    public static String mVendorUid, mConsumerUid, mEventKey, mVendorName;


    public ChatHomeAdapter(Class<ChatHome> modelClass, int modelLayout, Class<ChatHomeViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final ChatHomeViewholder viewHolder, final ChatHome model, final int position) {
        Log.i(TAG,TAG);
        mVendorUid = model.getVendorUid();
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            viewHolder.mTextViewChatHomeVendorName.setText(model.getConsumerName());
            viewHolder.mTextViewChatHomeTimestamp.setText("Order submitted on: " + model.getTimestamp());
            viewHolder.mTextViewChatHomeEventName.setText("For "+model.getEventName());
            viewHolder.mTextViewChatHomeEventDate.setText("Event date: " + model.getEventDate());
            Glide.with(mContext)
                    .load(model.getConsumerPhoto())
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.mImageViewChatHome);
        } else {
            viewHolder.mTextViewChatHomeVendorName.setText(model.getVendorName());
            viewHolder.mTextViewChatHomeTimestamp.setText("Order submitted on: " + model.getTimestamp());
            viewHolder.mTextViewChatHomeEventName.setText("For " + model.getEventName());
            viewHolder.mTextViewChatHomeEventDate.setText("Event date: " + model.getEventDate());
            Glide.with(mContext)
                    .load(model.getVendorPhoto())
                    .centerCrop()
                    .into(viewHolder.mImageViewChatHome);
        }

        viewHolder.mCardViewChatHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVendorUid = model.getVendorUid();
                mConsumerUid = model.getConsumerUid();
                mEventKey = model.getEventKey();
                mVendorName = model.getVendorName();
                Log.i("ConsumerUID", mConsumerUid +" "+ mEventKey);
//                getChat();
            }
        });
    }

//    private void getChat() {
//        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
//            mContext.startActivity(new Intent(mContext, ChatActivity.class));
//        } else {
//            mContext.startActivity(new Intent(mContext, ChatActivity.class));
//        }

//    }
}
