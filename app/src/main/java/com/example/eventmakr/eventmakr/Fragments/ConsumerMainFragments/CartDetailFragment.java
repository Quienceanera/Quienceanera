package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CartDetailFragment extends android.app.Fragment {
    private static final String TAG = CartDetailFragment.class.getSimpleName();
    private ImageView mImageViewCartDetail;
    private TextView mTextViewCartDetailDate, mTextViewCartDetailEvent, mTextViewCartDetailAddress, mTextViewcartDetailVendorName;
    private String mCartDetailDate, mCartDetailEvent, mCartDetailAddress, mVendorLogo, mVendorName, mVendorUid;
    private DatabaseReference mCartInfoRef;
    private LinearLayout mLayoutDetails;
    private ValueEventListener mValueEventListener;

    public CartDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);

        Bundle extras = getArguments();
        if (extras == null){
            Log.i(TAG, "extras are null");

        } else {
            mVendorUid = extras.getString("VendorUid");
            Log.i(TAG, extras.toString());
        }

//        Slide slide = new Slide();
//        slide.setSlideEdge(Gravity.RIGHT);
//        setEnterTransition(slide);
        mCartInfoRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(mVendorUid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cart_detail, container, false);
            mImageViewCartDetail = (ImageView) view.findViewById(R.id.imageViewCartDetail);
            mTextViewCartDetailDate = (TextView) view.findViewById(R.id.textViewCartDetailDate);
            mTextViewCartDetailEvent = (TextView) view.findViewById(R.id.textViewCartDetailEventName);
            mTextViewCartDetailAddress = (TextView) view.findViewById(R.id.textViewCartDetailAddress);
            mTextViewcartDetailVendorName = (TextView) view.findViewById(R.id.textViewCartDetailVendorName);

        mLayoutDetails = (LinearLayout) view.findViewById(R.id.layoutCartDetailTextViews);

        for (int i = 0; i < mLayoutDetails.getChildCount(); i++){
            View child = mLayoutDetails.getChildAt(i);
            child.animate().setStartDelay(100 + i * 500)
                    .setInterpolator(new AccelerateInterpolator())
                    .alpha(1)
                    .scaleX(1)
                    .scaleY(1);
        }

            mValueEventListener = mCartInfoRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mCartDetailDate = (String) dataSnapshot.child("eventDate").getValue();
                    mCartDetailEvent = (String) dataSnapshot.child("eventName").getValue();
                    mCartDetailAddress = (String) dataSnapshot.child("eventAddress").getValue();
                    mVendorLogo = (String) dataSnapshot.child("vendorLogo").getValue();
                    mVendorName = (String) dataSnapshot.child("vendorName").getValue();

                    if (CartDetailFragment.this.isAdded()){
                        Glide.with(mImageViewCartDetail.getContext())
                                .load(mVendorLogo)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(mImageViewCartDetail);
                    }
                    mTextViewcartDetailVendorName.setText(mVendorName);
                    mTextViewCartDetailDate.setText(mCartDetailDate);
                    mTextViewCartDetailEvent.setText(mCartDetailEvent);
                    mTextViewCartDetailAddress.setText(mCartDetailAddress);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mValueEventListener != null){
            mCartInfoRef.removeEventListener(mValueEventListener);
        }
    }

}
