package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.PayActivity;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.CartHomeViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.graphics.Color.rgb;

public class CartHomeAdapter extends FirebaseRecyclerAdapter<Cart, CartHomeViewholder>{
    public final static String TAG = CartHomeAdapter.class.getSimpleName();
    private Context mContext;
    private DatabaseReference mDatabaseConfirm;
    private int mPosition;
    public static String mVendorUid, mTotalPrice, mCartHomeKey, mCartHomeName;

    public CartHomeAdapter(Class<Cart> modelClass, int modelLayout, Class<CartHomeViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final CartHomeViewholder viewHolder, final Cart model,  int position) {
        Log.i(TAG,TAG);
        mPosition = position;
        mDatabaseConfirm = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(model.getVendorUid());
        mDatabaseConfirm.keepSynced(true);
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

        viewHolder.mCardViewCartHome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mCartHomeName = model.getEventName();
                mVendorUid = model.getVendorUid();
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle("Delete Order?")
                        .setTitleColor(R.color.blue)
                        .setTextSubTitle("For: "+mCartHomeName)
                        .setNegativeButtonText("Cancel")
                        .setPositiveButtonText("Yes")
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                DeleteUtil.deleteOrderHomeItem();
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
                if (dataSnapshot.hasChild("confirm")){
                    Log.i("Datasnapshot", "True");
                    viewHolder.mTextViewConfirm.setText("        Order\nConfirmed!");
                    viewHolder.mCardViewCartHome.setCardBackgroundColor(rgb(175,209,54));
                }else{
                    Log.i("Datasnapshot", "false");
                    viewHolder.mTextViewConfirm.setText("      Awaiting\nConfirmation");
                    viewHolder.mCardViewCartHome.setCardBackgroundColor(rgb(255,255,255));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getCart() {
        mContext.startActivity(new Intent(mContext, PayActivity.class));
    }
}
