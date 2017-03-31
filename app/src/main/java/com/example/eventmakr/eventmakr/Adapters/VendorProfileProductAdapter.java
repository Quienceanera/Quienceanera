package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.EventActivity;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorProfileProductAdapter extends FirebaseRecyclerAdapter<Menu, Viewholder> {
    private static final String TAG = VendorProfileProductAdapter.class.getSimpleName();
    private Context mContext;
    public static String mProductKey, mVendorUid, mVendorName;

    public VendorProfileProductAdapter(Class<Menu> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, final Menu model, final int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewVendorProductItemName.setText(model.getName());
        viewHolder.mTextViewVendorProductItemPrice.setText(model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .thumbnail(0.5f)
                .into(viewHolder.mImageViewVendorProductItem);

        viewHolder.mImageViewVendorProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductKey = model.getKey();
                mVendorUid = model.getVendorUid();
                mVendorName = model.getVendorName();
                getProductItemFragment();
            }
        });
    }
    private void getProductItemFragment () {
        ((EventActivity) mContext)
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerVendorProductItemFragment())
                .addToBackStack(null)
                .commit();
    }
}
