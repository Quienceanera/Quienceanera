package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.Utils.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VendorProfileProductAdapter extends FirebaseRecyclerAdapter<Menu, Viewholder> {

    private  static final String TAG = VendorProfileProductAdapter.class.getSimpleName();
    private Context mContext;
    private DatabaseReference mDatabaseReference;
    public static String mProductKey;

    public VendorProfileProductAdapter(Class<Menu> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, final Menu model, final int position) {
        viewHolder.mTextViewVendorProductItemName.setText(model.getName());
        viewHolder.mTextViewVendorProductItemPrice.setText(model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewVendorProductItem);

        viewHolder.mImageViewVendorProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductKey = model.getKey();
                getProductItemFragment();
//                Toast.makeText(mContext, model.getKey(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    void getProductItemFragment () {
        ((ConsumerActivity) mContext)
                .getFragmentManager()
                .beginTransaction()
                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorProductItemFragment())
                .addToBackStack(null)
                .commit();
    }
}
