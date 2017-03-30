package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class ItemsAdapter extends FirebaseRecyclerAdapter<Items, Viewholder> {

    private static final String TAG = ItemsAdapter.class.getSimpleName();
    private Context mContext;
    public static String mItemsKey;
    private DatabaseReference mItemsRef;

    public ItemsAdapter(Class<Items> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, final Items model, int position) {
        Log.i(TAG,TAG);
        viewHolder.mTextViewItemsName.setText(model.getName());
        viewHolder.mTextViewItemsQuantity.setText(model.getQuantity());
        viewHolder.mTextViewItemsPrice.setText("$" + model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewItems);
//
//        mItemsRef = FirebaseUtil.getUserCartRef().child(model.getVendorId());
//        mItemsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//            for (DataSnapshot child: dataSnapshot.child("price").getChildren()) {
//                String mChildPrice = child.getKey();
//                Log.i("Price ", mChildPrice);
//
//            }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        viewHolder.mImageViewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
