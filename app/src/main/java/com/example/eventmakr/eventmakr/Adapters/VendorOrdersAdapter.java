package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Objects.VendorOrderItem;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderListViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorOrdersAdapter extends FirebaseRecyclerAdapter<VendorOrderItem, VendorOrderListViewholder> {

    private static final String TAG = VendorOrdersAdapter.class.getSimpleName();
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
        viewHolder.mTextViewOrderItemQuantity.setText(mContext.getString(R.string.qty) + model.getQuantity());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mImageViewOrderItem);
        Log.i(TAG,TAG);
    }
}
