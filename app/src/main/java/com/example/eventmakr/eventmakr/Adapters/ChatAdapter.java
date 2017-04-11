package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.Query;

public class ChatAdapter extends FirebaseRecyclerAdapter<Message, Viewholder>{
    private static final String TAG = ChatAdapter.class.getSimpleName();
    private Context context;
    private String mUid, mChatKey, mRefKey, mEventKey, mVendorUid;
    private Query mQuery;

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public ChatAdapter(Class<Message> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
        this.mQuery = ref;
    }

    @Override
    protected void populateViewHolder(final Viewholder viewHolder, final Message model, final int position) {
        Log.i(TAG,TAG);
        mUid = FirebaseUtil.getUid();
        if (mUid.equals(model.getSenderUid())) {
            viewHolder.mTextViewChatUser.setText(model.getText());
            viewHolder.mCardViewChatUser.setVisibility(View.VISIBLE);
            viewHolder.mCardViewChat.setVisibility(View.INVISIBLE);
            viewHolder.mCircleImageViewChat.setVisibility(View.INVISIBLE);

            viewHolder.mCardViewChatUser.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mChatKey = getRef(position).getKey();
                    mEventKey = model.getEventKey();
                    mVendorUid = model.getReceiverUid();
                    ViewAnimator.animate(viewHolder.mLayoutDeleteComment)
                            .slideRight()
                            .descelerate()
                            .duration(300)
                            .start();
                    ViewAnimator.animate(viewHolder.mCardViewChat)
                            .slideTop()
                            .duration(200)
                            .start();
                    viewHolder.mCardViewChat.setVisibility(View.INVISIBLE);
                    viewHolder.mLayoutDeleteComment.setVisibility(View.VISIBLE);
                    return false;
                }
            });
            viewHolder.mFabDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteMessage();
                    viewHolder.mCardViewChat.setVisibility(View.VISIBLE);
                    viewHolder.mLayoutDeleteComment.setVisibility(View.GONE);
                }
            });
            viewHolder.mFabCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewAnimator.animate(viewHolder.mLayoutDeleteComment)
                            .slideRight()
                            .descelerate()
                            .duration(300)
                            .start();

                    ViewAnimator.animate(viewHolder.mCardViewChat)
                            .slideBottom()
                            .duration(200)
                            .start();
                    viewHolder.mCardViewChat.setVisibility(View.VISIBLE);
                    viewHolder.mLayoutDeleteComment.setVisibility(View.INVISIBLE);
                    viewHolder.mCardViewChat.setVisibility(View.GONE);
                }
            });
        } else {
            viewHolder.mTextViewChat.setText(model.getText());
            viewHolder.mCardViewChatUser.setVisibility(View.INVISIBLE);
            viewHolder.mCardViewChat.setVisibility(View.VISIBLE);
            viewHolder.mCircleImageViewChat.setVisibility(View.VISIBLE);
            if (model.getPhotoUrl() == null) {
                viewHolder.mCircleImageViewChat.setImageDrawable(ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.ic_account_circle_black_36dp));
            } else {
                Glide.with(viewHolder.itemView.getContext())
                        .load(model.getPhotoUrl())
                        .priority(Priority.NORMAL)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.mCircleImageViewChat);
            }
        }
        ViewAnimator.animate(viewHolder.mLayoutChatItem)
                .slideBottom()
                .descelerate()
                .duration(300)
                .start();

    }
    private void deleteMessage() {
        Log.i("Delete Message Key", mChatKey);
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            FirebaseUtil.getVendorSideConsumerMessageRef().child(mChatKey).removeValue();
            FirebaseUtil.getVendorSideVendorMessageRef().child(mChatKey).removeValue();
        }else{
            FirebaseUtil.getConsumerSideConsumerMessageRef().child(mEventKey).child(mVendorUid).child(mChatKey).removeValue();
            FirebaseUtil.getConsumerSideVendorMessageRef().child(mChatKey).removeValue();
        }
    }
}
