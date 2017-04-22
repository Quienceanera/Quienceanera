package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Activities.EventActivity;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorCategoryFragment;
import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorAdapter extends FirebaseRecyclerAdapter<Vendor, Viewholder> {

    private  static final String TAG = VendorAdapter.class.getSimpleName();
    private Context mContext;
    private Intent mIntent;
    private Query mQuery;
    public static String mVendorUid, mCategory, mPriceRange, mVendorLogo, mVendorName, mPlaceId;

    public VendorAdapter(Class<Vendor> modelClass, int modelLayout, Class<Viewholder> viewHolderClass,final Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        this.mQuery = ref;
        mCategory = ConsumerVendorCategoryFragment.mCategory;
//        mPriceRange = ConsumerBudgetFragment.mPriceRange;
    }

    @Override
    protected void populateViewHolder(final Viewholder viewHolder, final Vendor model, final int position) {
        Log.i(TAG,TAG);
        mVendorUid = getRef(position).getKey();
        mPlaceId = model.getPlaceId();
        mVendorLogo = model.getLogo();
        mVendorUid = model.getVendorUid();
        mVendorName = model.getName();
        if (model.getPrice().equals(mPriceRange)){
            viewHolder.mTextViewVendorName.setText(model.getName());
            viewHolder.mTextViewVendorAddress.setText(model.getAddress()+" "+model.getZipcode());
            Glide.with(mContext)
                    .load(model.getLogo())
                    .centerCrop()
                    .crossFade()
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.mImageViewVendor);

        } else if (model.getPrice().equals(mPriceRange)){
            viewHolder.mCardViewVendorItem.setVisibility(View.GONE);
        }

        viewHolder.mCardViewVendorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVendorUid = getRef(position).getKey();
//                mPlaceId = model.getPlaceId();
                mVendorLogo = model.getLogo();
//                mVendorUid = model.getVendorUid();
                mVendorName = model.getName();

                EventActivity eventActivity = (EventActivity)mContext;
                Bundle bundle = new Bundle();
                bundle.putString("VendorCategory", model.getCategory());
                bundle.putString("VendorUid", model.getVendorUid());
                bundle.putString("PlaceId", model.getPlaceId());

                eventActivity.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerEventActivity, FragmentUtil.getConsumerVendorProfileFragment(bundle))
                        .setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit)
                        .addToBackStack(null)
                        .commit();
            }
        });

        if (model.getPrice().equals(mContext.getString(R.string.$))) {
            viewHolder.mImageViewPrice1.setVisibility(View.VISIBLE);
        } if (model.getPrice().equals(mContext.getString(R.string.$$))) {
            viewHolder.mImageViewPrice1.setVisibility(View.VISIBLE);
            viewHolder.mImageViewPrice2.setVisibility(View.VISIBLE);
        } if (model.getPrice().equals(mContext.getString(R.string.$$$))) {
            viewHolder.mImageViewPrice1.setVisibility(View.VISIBLE);
            viewHolder.mImageViewPrice2.setVisibility(View.VISIBLE);
            viewHolder.mImageViewPrice3.setVisibility(View.VISIBLE);

        }
    }

}
