package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

public class VendorOrderListViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewOrderItem;
    public ImageView mImageViewOrderItem;
    public TextView mTextViewOrderItemName, mTextViewOrderItemCustomerName, mTextViewOrderItemPrice, mTextViewOrderItemQuantity;

    public VendorOrderListViewholder(View itemView) {
        super(itemView);

        mCardViewOrderItem = (CardView) itemView.findViewById(R.id.cardViewOrderItem);
        mImageViewOrderItem = (ImageView) itemView.findViewById(R.id.imageViewOrderItem);
        mTextViewOrderItemName = (TextView) itemView.findViewById(R.id.textViewOrderItemName);
        mTextViewOrderItemCustomerName = (TextView) itemView.findViewById(R.id.textViewOrderItemCustomerName);
        mTextViewOrderItemPrice = (TextView) itemView.findViewById(R.id.textViewOrderItemPrice);
        mTextViewOrderItemQuantity = (TextView) itemView.findViewById(R.id.textViewOrderItemQuantity);

    }
}
