package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.ViewHolders.CartListViewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.Query;

public class CartListAdapter extends FirebaseRecyclerAdapter<Items, CartListViewholder>{
    private static final String TAG = CartListAdapter.class.getSimpleName();
    private Context mContext;
    public static String mVendorUid, mCartItemKey, mCartItemName;

    public CartListAdapter(Class<Items> modelClass, int modelLayout, Class<CartListViewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(final CartListViewholder viewHolder, final Items model, final int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewCartItemName.setText(model.getName());
        viewHolder.mTextViewCartItemPrice.setText("$"+model.getPrice());
        viewHolder.mTextViewCartItemQuantity.setText("Qty: "+model.getQuantity());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .thumbnail(1.0f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.mImageViewCartItem);

        if (model.getInstructions() != null) {
            viewHolder.mIconInstructions.setVisibility(View.VISIBLE);
            viewHolder.mCardViewCartItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
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
//        if (!VendorActivity.mVendorMode && ConsumerActivity.mConsumerMode) {
//            Log.i("Mode", VendorActivity.mVendorMode.toString()+" "+ConsumerActivity.mConsumerMode.toString());
//            viewHolder.mCardViewCartItem.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mCartItemKey = getRef(position).getKey();
//                    mCartItemName = model.getName();
//                    FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
//                            .setImageRecourse(R.drawable.delete)
//                            .setTextTitle("Delete?")
//                            .setTextSubTitle(CartListAdapter.mCartItemName)
//                            .setNegativeButtonText("Cancel")
//                            .setPositiveButtonText("Yes")
//                            .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
//                                @Override
//                                public void OnClick(View view, Dialog dialog) {
//                                    dialog.dismiss();
//                                }
//                            })
//                            .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
//                                @Override
//                                public void OnClick(View view, Dialog dialog) {
//                                    DeleteUtil.deleteOrderListItem();
//                                    dialog.dismiss();
//                                }
//                            })
//                            .build();
//                    alert.show();
//                    return false;
//                }
//            });
//        }
    }

}
