package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.Utils.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ItemsAdapter extends FirebaseRecyclerAdapter<Items, Viewholder> {

    private static final String TAG = Items.class.getSimpleName();
    private Context mContext;
    public static String mItemsKey;
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public ItemsAdapter(Class<Items> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
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
    protected void populateViewHolder(Viewholder viewHolder, final Items model, int position) {
        viewHolder.mTextViewItemsName.setText(model.getName());
        viewHolder.mTextViewItemsQuantity.setText(model.getQuantity());
        viewHolder.mTextViewItemsPrice.setText("$ " + model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewItems);


        viewHolder.mImageViewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q = Integer.parseInt(model.getQuantity());
                float p = Float.parseFloat((model.getPrice()));
                final float t = (q * p);
                Toast.makeText(mContext, String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
