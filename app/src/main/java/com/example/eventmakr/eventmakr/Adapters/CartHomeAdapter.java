package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.PayActivity;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.ViewHolders.CartHomeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class CartHomeAdapter extends FirebaseRecyclerAdapter<Cart, CartHomeViewholder>{
    private Context mContext;
    public static String mVendorUid, mTotalPrice;

    public CartHomeAdapter(Class<Cart> modelClass, int modelLayout, Class<CartHomeViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final CartHomeViewholder viewHolder, final Cart model, final int position) {

        viewHolder.mTextViewCartHomeVendorName.setText(model.getVendorName());
        viewHolder.mTextViewEventName.setText("For "+model.getEventName());
        viewHolder.mTextViewCartHomeTimestamp.setText("Submitted On: "+model.getTimeStamp());
        viewHolder.mTextViewCartHomePriceTotal.setText("Total Price: $"+model.getPriceTotal());
        viewHolder.mTextViewCartHomeCount.setText("Qty: "+model.getItemCount());
        Glide.with(mContext)
                .load(model.getVendorLogo())
                .centerCrop()
                .into(viewHolder.mImageViewCartHome);

        viewHolder.mCardViewCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVendorUid = model.getVendorUid();
                mTotalPrice = model.getPriceTotal();
                getCart();
            }
        });
    }

    private void getCart() {
        mContext.startActivity(new Intent(mContext, PayActivity.class));
    }
}
