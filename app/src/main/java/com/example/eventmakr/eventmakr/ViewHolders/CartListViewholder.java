package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

public class CartListViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewCartItem;
    public ImageView mImageViewCartItem;
    public TextView mTextViewCartItemName, mTextViewCartItemVendorName, mTextViewCartItemPrice, mTextViewCartItemQuantity;

    public CartListViewholder(View itemView) {
        super(itemView);

        mCardViewCartItem = (CardView) itemView.findViewById(R.id.cardViewCartItem);
        mImageViewCartItem = (ImageView) itemView.findViewById(R.id.imageViewCartItem);
        mTextViewCartItemName = (TextView) itemView.findViewById(R.id.textViewCartItemName);
//        mTextViewCartItemVendorName = (TextView) itemView.findViewById(R.id.textViewCartItemVendorName);
        mTextViewCartItemPrice = (TextView) itemView.findViewById(R.id.textViewCartItemPrice);
        mTextViewCartItemQuantity = (TextView) itemView.findViewById(R.id.textViewCartItemQuantity);

    }
}
