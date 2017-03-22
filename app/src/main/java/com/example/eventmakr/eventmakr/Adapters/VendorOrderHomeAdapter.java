package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.VendorOrderActivity;
import com.example.eventmakr.eventmakr.Objects.VendorOrderHome;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderHomeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorOrderHomeAdapter extends FirebaseRecyclerAdapter<VendorOrderHome, VendorOrderHomeViewholder>{
    private Context mContext;
    public static String mCustomerUid, mEventKey, mTotalPrice;

    public VendorOrderHomeAdapter(Class<VendorOrderHome> modelClass, int modelLayout, Class<VendorOrderHomeViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final VendorOrderHomeViewholder viewHolder, final VendorOrderHome model, final int position) {

        viewHolder.mTextViewVendorOrderHomeCustomerName.setText(model.getCustomerName());
        viewHolder.mTextViewVendorOrderHomeEventName.setText("For "+model.getEventName());
        viewHolder.mTextViewVendorOrderHomeTimestamp.setText("Submitted On: "+model.getTimestamp());
        viewHolder.mTextViewVendorOrderHomePriceTotal.setText("Total Price: "+model.getTotalPrice());
        viewHolder.mTextViewVendorOrderHomeCount.setText("Quantity "+model.getTotalQuantity());
        Glide.with(mContext)
                .load(model.getCustomerPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewVendorOrderHome);

        viewHolder.mCardViewVendorOrderHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerUid = model.getCustomerUid();
                mEventKey = model.getEventKey();
                mTotalPrice = model.getTotalPrice();

                getOrders();
            }
        });
    }

    private void getOrders() {
        mContext.startActivity(new Intent(mContext, VendorOrderActivity.class));
    }
}
