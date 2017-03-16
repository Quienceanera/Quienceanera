package com.example.eventmakr.eventmakr.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorOrderHomeViewholder extends RecyclerView.ViewHolder {

    public CardView mCardViewVendorOrderHome;
    public CircleImageView mImageViewVendorOrderHome;
    public TextView mTextViewVendorOrderHomeCustomerName, mTextViewVendorOrderHomeTimestamp, mTextViewVendorOrderHomePriceTotal, mTextViewVendorOrderHomeCount, mTextViewVendorOrderHomeEventName;

    public VendorOrderHomeViewholder(View itemView) {
        super(itemView);

        mCardViewVendorOrderHome = (CardView) itemView.findViewById(R.id.cardViewVendorOrderHome);
        mImageViewVendorOrderHome = (CircleImageView) itemView.findViewById(R.id.imageViewVendorOrderHome);
        mTextViewVendorOrderHomeCustomerName = (TextView) itemView.findViewById(R.id.textViewVendorOrderHomeCustomerName);
        mTextViewVendorOrderHomeTimestamp = (TextView) itemView.findViewById(R.id.textViewVendorOrderHomeTime);
        mTextViewVendorOrderHomePriceTotal = (TextView) itemView.findViewById(R.id.textViewVendorOrderHomePriceTotal);
        mTextViewVendorOrderHomeCount = (TextView) itemView.findViewById(R.id.textViewVendorOrderHomeCount);
        mTextViewVendorOrderHomeEventName = (TextView) itemView.findViewById(R.id.textViewVendorOrderHomeEventName);

    }
}
