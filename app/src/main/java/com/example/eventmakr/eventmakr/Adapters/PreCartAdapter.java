package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class PreCartAdapter extends FirebaseRecyclerAdapter<Items, Viewholder> {

    private static final String TAG = PreCartAdapter.class.getSimpleName();
    private Context mContext;
    public static String mPreCartItemKey, mPreCartItemName, mVendorUid;
    private DatabaseReference mItemsRef;

    public PreCartAdapter(Class<Items> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, final Items model, int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewItemsName.setText(model.getName());
        viewHolder.mTextViewItemsQuantity.setText("Qty: " + model.getQuantity());
        viewHolder.mTextViewItemsPrice.setText("$" + model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewItems);

        if (model.getInstructions() != null) {
            viewHolder.mIconInstructions.setVisibility(View.VISIBLE);
            viewHolder.mImageViewItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                            .setBackgroundColor(R.color.colorAccentLighter)
                            .setTextTitle("Special Instructions")
                            .setTextSubTitle(model.getInstructions())
                            .setTitleColor(R.color.blue)
                            .setPositiveButtonText("Okay")
                            .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                                @Override
                                public void OnClick(View view, Dialog dialog) {
                                    dialog.dismiss();
                                }
                            })
                            .build();
                    alert.show();
                }
            });
        }

        Log.i("Mode", VendorActivity.mVendorMode.toString()+" "+ ConsumerActivity.mConsumerMode.toString());
        if (!VendorActivity.mVendorMode && ConsumerActivity.mConsumerMode) {

            viewHolder.mImageViewItems.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mPreCartItemKey = model.getKey();
                    mPreCartItemName = model.getName();
                    mVendorUid = model.getVendorId();
                    FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                            .setBackgroundColor(R.color.colorAccentLighter)
                            .setImageRecourse(R.drawable.delete)
                            .setTextTitle("Delete?")
                            .setTextSubTitle(model.getName())
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
                                    DeleteUtil.deleteOrderListItem();
                                    dialog.dismiss();
                                }
                            })
                            .build();
                    alert.show();
                    return false;
                }
            });
        }
    }
}
