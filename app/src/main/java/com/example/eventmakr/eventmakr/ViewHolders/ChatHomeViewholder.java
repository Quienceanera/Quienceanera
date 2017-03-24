package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatHomeViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewChatHome;
    public CircleImageView mImageViewChatHome;
    public TextView mTextViewChatHomeVendorName, mTextViewChatHomeTimestamp, mTextViewChatHomeEventName, mTextViewChatHomeEventDate;

    public ChatHomeViewholder(View itemView) {
        super(itemView);

        mCardViewChatHome = (CardView) itemView.findViewById(R.id.cardViewChatHome);
        mImageViewChatHome = (CircleImageView) itemView.findViewById(R.id.imageViewChatHome);
        mTextViewChatHomeVendorName = (TextView) itemView.findViewById(R.id.textViewChatHomeVendorName);
        mTextViewChatHomeTimestamp = (TextView) itemView.findViewById(R.id.textViewChatHomeTime);
        mTextViewChatHomeEventName = (TextView) itemView.findViewById(R.id.textViewChatHomeEventName);
        mTextViewChatHomeEventDate = (TextView) itemView.findViewById(R.id.textViewChatHomeEventDate);

    }
}
