package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

public class CartHomeViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewCartHome;
    public ImageView mImageViewCartHome, mNotificationMessage;
    public TextView mTextViewCartHomeVendorName, mTextViewCartHomeTimestamp, mTextViewCartHomePriceTotal, mTextViewCartHomeCount, mTextViewEventName, mTextViewConfirm;
    public View mYellowBorder;

    public CartHomeViewholder(View itemView) {
        super(itemView);

        mCardViewCartHome = (CardView) itemView.findViewById(R.id.cardViewCartHome);
        mImageViewCartHome = (ImageView) itemView.findViewById(R.id.imageViewCartHome);
        mNotificationMessage = (ImageView) itemView.findViewById(R.id.notificationMessage);
        mTextViewCartHomeVendorName = (TextView) itemView.findViewById(R.id.textViewCartHomeVendorName);
        mTextViewCartHomeTimestamp = (TextView) itemView.findViewById(R.id.textViewCartHomeTime);
        mTextViewCartHomePriceTotal = (TextView) itemView.findViewById(R.id.textViewCartHomePriceTotal);
        mTextViewCartHomeCount = (TextView) itemView.findViewById(R.id.textViewCartHomeCount);
        mTextViewEventName = (TextView) itemView.findViewById(R.id.textViewCartHomeEventName);
        mTextViewConfirm = (TextView) itemView.findViewById(R.id.textViewCartConfirm);

        mYellowBorder = itemView.findViewById(R.id.yellowBorder);


    }
}
