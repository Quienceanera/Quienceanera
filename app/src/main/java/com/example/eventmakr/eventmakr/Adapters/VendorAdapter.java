package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.Utils.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class VendorAdapter extends FirebaseRecyclerAdapter<Vendor, Viewholder> {

    private  static final String TAG = VendorAdapter.class.getSimpleName();
    private Context mContext;
    private Intent mIntent;
    public static String mVendorKey;

    public VendorAdapter(Class<Vendor> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, Vendor model, final int position) {
        mVendorKey = getRef(position).getKey();
        viewHolder.mTextViewVendorName.setText(model.getName());
        Glide.with(mContext)
                .load(model.getLogo())
                .centerCrop()
                .into(viewHolder.mImageViewVendor);

        viewHolder.mCardViewVendorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVendorProfile();
            }
        });
    }

    private void getVendorProfile() {
        ConsumerActivity consumerActivity = (ConsumerActivity)mContext;
        consumerActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorProfileFragment())
                .addToBackStack(null)
                .commit();

    }
//    private void sendVendorKey() {
//        ConsumerVendorProfileFragment consumerVendorProfileFragment = new ConsumerVendorProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(mKey, "mKey");
//        consumerVendorProfileFragment.setArguments(args);
//
//        ConsumerActivity consumerActivity = (ConsumerActivity)mContext;
//        FragmentTransaction fragmentTransaction = consumerActivity.getSupportFragmentManager().beginTransaction().replace(R.id.consumerActivityLayout, consumerVendorProfileFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

//    @Override
//    public void onFragmentInteraction(String string) {
//        string = mKey;
//    }

}
