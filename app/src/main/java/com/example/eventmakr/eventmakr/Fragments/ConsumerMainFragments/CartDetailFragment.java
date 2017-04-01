package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
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
    private String mCartDetailDate, mCartDetailEvent, mCartDetailAddress, mVendorLogo, mVendorName;
    private DatabaseReference mCartInfoRef;

    public CartDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        mCartInfoRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(CartHomeAdapter.mVendorUid);
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

            mCartInfoRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mCartDetailDate = (String) dataSnapshot.child("eventDate").getValue();
                    mCartDetailEvent = (String) dataSnapshot.child("eventName").getValue();
                    mCartDetailAddress = (String) dataSnapshot.child("eventAddress").getValue();
                    mVendorLogo = (String) dataSnapshot.child("vendorLogo").getValue();
                    mVendorName = (String) dataSnapshot.child("vendorName").getValue();

                    Glide.with(getActivity())
                            .load(mVendorLogo)
                            .centerCrop()
                            .into(mImageViewCartDetail);
                    mTextViewcartDetailVendorName.setText(mVendorName);
                    mTextViewCartDetailDate.setText("Event date: " + mCartDetailDate);
                    mTextViewCartDetailEvent.setText("For " + mCartDetailEvent);
                    mTextViewCartDetailAddress.setText("Zip: " + mCartDetailAddress);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        getFragmentManager()
//                .beginTransaction()
//                .remove(this)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                .commit();
//    }

}
