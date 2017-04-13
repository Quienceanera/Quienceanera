package com.example.eventmakr.eventmakr.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.DeleteUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.Query;

public class VendorProductsAdapter extends FirebaseRecyclerAdapter<Menu, Viewholder> {

    private  static final String TAG = VendorProductsAdapter.class.getSimpleName();
    private Context mContext;
    public static String mProductKey;

    public VendorProductsAdapter(Class<Menu> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, final Menu model, int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewMenuItemName.setText(model.getName());
        viewHolder.mTextViewMenuItemDescription.setText(model.getDetails());
        viewHolder.mTextViewMenuItemPrice.setText("$"+model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewMenuItem);

        ViewAnimator.animate(viewHolder.mCardViewMenuItem)
                .slideBottom()
                .duration(500)
                .start();

        viewHolder.mCardViewMenuItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mProductKey = model.getKey();
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(mContext)
                        .setImageRecourse(R.drawable.delete)
                        .setTextTitle("Delete?")
                        .setTitleColor(R.color.blue)
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
                                DeleteUtil.deleteProductItem();
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
