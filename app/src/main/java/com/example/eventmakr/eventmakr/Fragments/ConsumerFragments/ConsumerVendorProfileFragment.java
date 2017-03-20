package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class ConsumerVendorProfileFragment extends android.app.Fragment implements View.OnClickListener {
    private static final String TAG = "ConsumerVendorProfileFragment";
    private CardView mButtonMyItems, mCardViewMap, mCardViewVendorProfile;
    private Context mContext;
    private DatabaseReference mDatabaseReference;
    private ImageView mImageViewVendorProfile;
    private TextView mTextViewVendorName, mTextViewVendorDescription, mTextViewVendorAddress;
    private String mVendorUid, mVendorCategory;
    private String mVendorLogo, mVendorName, mVendorDescription, mVendorAddress;
    private FrameLayout mLayoutProductList, mLayoutContainer;

    public ConsumerVendorProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        getActivity().getActionBar().setTitle(VendorAdapter.mVendorName);
        mVendorCategory = ConsumerBudgetFragment.mCategory;
        mDatabaseReference = FirebaseUtil.getVendorRef().child(mVendorCategory).child(VendorAdapter.mVendorUid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_consumer_vendor_profile, container, false);
        mButtonMyItems = (CardView) view.findViewById(R.id.buttonMyItems);
        mCardViewMap = (CardView) view.findViewById(R.id.cardViewMap);
        mCardViewVendorProfile = (CardView) view.findViewById(R.id.cardViewVendorProfile);
        mImageViewVendorProfile = (ImageView) view.findViewById(R.id.imageViewVendorProfile);
        mTextViewVendorName = (TextView) view.findViewById(R.id.textViewVendorName);
        mTextViewVendorDescription = (TextView) view.findViewById(R.id.textViewVendorDetails);
        mTextViewVendorAddress = (TextView) view.findViewById(R.id.textViewVendorAddress);
        mLayoutProductList = (FrameLayout) view.findViewById(R.id.containerRecyclerVendorProductItemList);
        mLayoutContainer = (FrameLayout) view.findViewById(R.id.layoutConsumerVendorProfile);
        getVendorInfo();
        getChildRecyclerVendorProductItems();
//        getChildMapFragment();

        mButtonMyItems.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewAnimator.animate(mCardViewVendorProfile)
                .slideLeft()
                .duration(800)
                .andAnimate(mTextViewVendorName)
                .slideRight()
                .duration(700)
                .andAnimate(mTextViewVendorDescription)
                .slideRight()
                .duration(600)
                .andAnimate(mTextViewVendorAddress)
                .slideRight()
                .duration(500)
                .andAnimate(mCardViewMap)
                .slideTop()
                .duration(600)
                .andAnimate(mLayoutProductList)
                .slideLeft()
                .duration(800)
                .descelerate()
                .start();
        mLayoutContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayoutContainer.setVisibility(View.VISIBLE);
            }
        },200);
    }

    void getChildRecyclerVendorProductItems () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorProductItemList, FragmentUtil.getRecyclerVendorProfileProductItemFragment())
                .commit();
    }

//    void getChildMapFragment () {
//        getChildFragmentManager()
//                .beginTransaction()
//                .add(R.id.containerMapFragment, FragmentUtil.getMapFragment())
//                .commit();
//    }

    public void getVendorInfo() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mVendorLogo = (String) dataSnapshot.child("logo").getValue();
                mVendorName = (String) dataSnapshot.child("name").getValue();
                mVendorDescription = (String) dataSnapshot.child("description").getValue();
                mVendorAddress = (String) dataSnapshot.child("address").getValue();

                Glide.with(mContext)
                        .load(mVendorLogo)
                        .centerCrop()
                        .into(mImageViewVendorProfile);
                mTextViewVendorName.setText(mVendorName);
                mTextViewVendorDescription.setText(mVendorDescription);
                mTextViewVendorAddress.setText(mVendorAddress);

                Log.i("Vendor Info ",mVendorCategory +" "+ mVendorLogo +" "+ mVendorName +" "+ mVendorDescription);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonMyItems:
                getContactVendorFragment();
        }

    }

    public void getContactVendorFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getContactVendorFragment())
                .addToBackStack(null)
                .commit();
    }

}
