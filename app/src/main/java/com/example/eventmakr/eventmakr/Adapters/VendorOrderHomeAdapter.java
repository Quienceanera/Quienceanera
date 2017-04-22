package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Activities.VendorOrderActivity;
import com.example.eventmakr.eventmakr.Objects.VendorOrderHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderHomeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.rgb;

public class VendorOrderHomeAdapter extends FirebaseRecyclerAdapter<VendorOrderHome, VendorOrderHomeViewholder>{
    private final static String TAG = VendorOrderHomeAdapter.class.getSimpleName();
    private Context mContext;
    public static String mCustomerUid, mEventKey, mTotalPrice, mOrderHomeKey;
    private Query mRef;
    private Boolean mProcessConfirm = false;
    private DatabaseReference mDatabaseConfirm, mDatabaseNewMessage;

    public VendorOrderHomeAdapter(Class<VendorOrderHome> modelClass, int modelLayout, Class<VendorOrderHomeViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
        this.mRef = ref;
    }

    @Override
    protected void populateViewHolder(final VendorOrderHomeViewholder viewHolder, final VendorOrderHome model, final int position) {
        Log.i(TAG,TAG);
        mOrderHomeKey = getRef(position).getKey();

        mDatabaseConfirm = FirebaseUtil.getVendorSideVendorOrderHomeRef().child(mOrderHomeKey);
        mDatabaseConfirm.keepSynced(true);
        viewHolder.mTextViewVendorOrderHomeCustomerName.setText(model.getCustomerName());
        viewHolder.mTextViewVendorOrderHomeEventName.setText(mContext.getString(R.string.for_string)+" "+model.getEventName());
        viewHolder.mTextViewVendorOrderHomeTimestamp.setText(mContext.getString(R.string.submitted_on)+" "+ model.getTimestamp());
        viewHolder.mTextViewVendorOrderHomePriceTotal.setText(mContext.getString(R.string.total)+(mContext.getString(R.string.$))+ model.getTotalPrice());
        viewHolder.mTextViewVendorOrderHomeCount.setText(mContext.getString(R.string.qty)+" "+model.getTotalQuantity());

        Glide.with(mContext)
                .load(model.getCustomerPhoto())
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mImageViewVendorOrderHome);

        viewHolder.mCardViewVendorOrderHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerUid = model.getCustomerUid();
                mEventKey = model.getEventKey();
                mTotalPrice = model.getTotalPrice();
                mDatabaseNewMessage.removeValue();
                getOrders();
            }
        });

        viewHolder.mCardViewVendorOrderHome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOrderHomeKey = getRef(position).getKey();
                mCustomerUid = model.getCustomerUid();
                mEventKey = model.getEventKey();

                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setBackgroundColor(R.color.colorAccentLighter)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle(mContext.getString(R.string.delete)+" "+model.getEventName()+" "+mContext.getString(R.string.and_all_its_content))
                        .setTitleColor(R.color.blue)
                        .setTextSubTitle(mContext.getString(R.string.for_string)+" "+model.getEventName())
                        .setNegativeButtonText(mContext.getString(R.string.cancel))
                        .setPositiveButtonText(mContext.getString(R.string.yes))
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                DeleteUtil.deleteVendorOrderHomeItem();
                                dialog.dismiss();
                            }
                        })
                        .build();
                alert.show();

                return false;
            }
        });

        mDatabaseConfirm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(FirebaseUtil.getUid())){
                    Log.i("Datasnapshot", "True");
                    viewHolder.mTextViewConfirm.setText(R.string.order_confirmed);
                    viewHolder.mCardViewVendorOrderHome.setCardBackgroundColor(rgb(175,209,54));
                }else{
                    Log.i("Datasnapshot", "false");
                    viewHolder.mTextViewConfirm.setText(R.string.awaiting_confirmation);
                    viewHolder.mCardViewVendorOrderHome.setCardBackgroundColor(rgb(255,255,255));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabaseNewMessage = FirebaseUtil.getBaseRef().child("newMessage").child(FirebaseUtil.getUid()).child(model.getEventKey());
        mDatabaseNewMessage.keepSynced(true);
        mDatabaseNewMessage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Log.i(TAG, dataSnapshot.toString());
                    viewHolder.mNotificationMessage.setVisibility(View.VISIBLE);
                }else {
                    viewHolder.mNotificationMessage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getOrders() {
        mContext.startActivity(new Intent(mContext, VendorOrderActivity.class));
    }
}
