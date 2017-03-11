package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.ViewHolders.CartListViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class CartListAdapter extends FirebaseRecyclerAdapter<Items, CartListViewholder>{
    private Context mContext;
    public static String mVendorUid;


    public CartListAdapter(Class<Items> modelClass, int modelLayout, Class<CartListViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final CartListViewholder viewHolder, final Items model, final int position) {
        viewHolder.mTextViewCartItemName.setText(model.getName());
        viewHolder.mTextViewCartItemVendorName.setText("");
        viewHolder.mTextViewCartItemPrice.setText("$"+model.getPrice());
        viewHolder.mTextViewCartItemQuantity.setText("Qty: "+model.getQuantity());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewCartItem);

        viewHolder.mCardViewCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getCart();
            }
        });
    }

}
