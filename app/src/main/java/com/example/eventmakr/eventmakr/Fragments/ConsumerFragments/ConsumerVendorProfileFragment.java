package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class ConsumerVendorProfileFragment extends android.app.Fragment implements View.OnClickListener {
    private static final String TAG = "ConsumerVendorProfileFragment";
//    private OnFragmentInteractionListener mListener;
    private CardView mButtonMyItems;
    private Context mContext;
    private DatabaseReference mDatabaseReference;
    private ImageView mImageViewVendorProfile;
    private TextView mTextViewVendorName, mTextViewVendorDescription;
    private VendorAdapter mVendorAdapter;
    public static String mVendorKey;
    private String mVendorLogo, mVendorName, mVendorDescription;

    public ConsumerVendorProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mVendorKey = mVendorAdapter.mVendorKey;
        mDatabaseReference = FirebaseUtil.getVendorRef();

        Toast.makeText(mContext, mVendorKey, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_consumer_vendor_profile, container, false);
        mButtonMyItems = (CardView) view.findViewById(R.id.buttonMyItems);
        mImageViewVendorProfile = (ImageView) view.findViewById(R.id.imageViewVendorProfile);
        mTextViewVendorName = (TextView) view.findViewById(R.id.textViewVendorName);
        mTextViewVendorDescription = (TextView) view.findViewById(R.id.textViewVendorDetails);

        getVendorInfo();
        getChildRecyclerVendorProductItems();
        getChildMapFragment();

        mButtonMyItems.setOnClickListener(this);
        return view;
    }

    void getChildRecyclerVendorProductItems () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorProductItemList, FragmentUtil.getRecyclerVendorProfileProductItemFragment())
                .commit();
    }

    void getChildMapFragment () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerMapFragment, FragmentUtil.getMapFragment())
                .commit();
    }

    public void getVendorInfo() {
        mDatabaseReference.child(mVendorKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mVendorLogo = (String) dataSnapshot.child("logo").getValue();
                mVendorName = (String) dataSnapshot.child("name").getValue();
                mVendorDescription = (String) dataSnapshot.child("description").getValue();

                Glide.with(mContext)
                        .load(mVendorLogo)
                        .centerCrop()
                        .into(mImageViewVendorProfile);
                mTextViewVendorName.setText(mVendorName);
                mTextViewVendorDescription.setText(mVendorDescription);

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
                .replace(R.id.consumerActivityLayout, FragmentUtil.getContactVendorFragment())
                .addToBackStack(null)
                .commit();
    }

}
