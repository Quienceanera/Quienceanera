package com.example.eventmakr.eventmakr.Adapters;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.Query;

public class MenuAdapter extends FirebaseRecyclerAdapter<Menu, Viewholder> {

    private  static final String Tag = MenuAdapter.class.getSimpleName();
    private Context mContext;

    public MenuAdapter(Class<Menu> modelClass, int modelLayout, Class<Viewholder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(Viewholder viewHolder, Menu model, int position) {
        viewHolder.mTextViewMenuItemName.setText(model.getName());
        viewHolder.mTextViewMenuItemDescription.setText(model.getDetails());
        viewHolder.mTextViewMenuItemPrice.setText("$"+model.getPrice());
        Glide.with(mContext)
                .load(model.getPhoto())
                .centerCrop()
                .into(viewHolder.mImageViewMenuItem);


        for (int i = 0; i < position; i++) {
            ViewAnimator.animate(viewHolder.mCardViewMenuItem)
                    .slideBottom()
                    .duration(1500)
                    .start();
            Log.i("for loop", String.valueOf(position));
        }
    }
}
