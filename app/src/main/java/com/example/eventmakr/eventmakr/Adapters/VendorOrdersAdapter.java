package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.Objects.VendorOrderItem;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderListViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorOrdersAdapter extends FirebaseRecyclerAdapter<VendorOrderItem, VendorOrderListViewholder> {

    private static final String TAG = Items.class.getSimpleName();
    private Context mContext;

    public VendorOrdersAdapter(Class<VendorOrderItem> modelClass, int modelLayout, Class<VendorOrderListViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(VendorOrderListViewholder viewHolder, final VendorOrderItem model, int position) {
        viewHolder.mTextViewOrderItemName.setText(model.getName());
        viewHolder.mTextViewOrderItemCustomerName.setText("");
        viewHolder.mTextViewOrderItemPrice.setText("$"+model.getPrice());
        viewHolder.mTextViewOrderItemQuantity.setText("Qty: "+model.getQuantity());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewOrderItem);

        viewHolder.mCardViewOrderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getCart();
            }
        });
    }
}
