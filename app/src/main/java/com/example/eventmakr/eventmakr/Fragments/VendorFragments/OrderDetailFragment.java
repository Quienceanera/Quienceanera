package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class OrderDetailFragment extends android.app.Fragment {

    private CircleImageView mImageViewOrderDetail;
    private TextView mTextViewOrderDetailDate, mTextViewOrderDetailEvent, mTextViewOrderDetailAddress, mTextViewOrderDetailCustomerName;
    private String mOrderDetailDate, mOrderDetailEvent, mOrderDetailAddress, mCustomerPhoto, mCustomerName, mCustomerUid;
    private DatabaseReference mOrderInfoRef;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mOrderInfoRef = FirebaseUtil.getVendorSideVendorOrderHomeRef().child(VendorOrderHomeAdapter.mEventKey);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        mImageViewOrderDetail = (CircleImageView) view.findViewById(R.id.imageViewOrderDetail);
        mTextViewOrderDetailDate = (TextView) view.findViewById(R.id.textViewOrderDetailDate);
        mTextViewOrderDetailEvent = (TextView) view.findViewById(R.id.textViewOrderDetailEventName);
        mTextViewOrderDetailAddress = (TextView) view.findViewById(R.id.textViewOrderDetailAddress);
        mTextViewOrderDetailCustomerName = (TextView) view.findViewById(R.id.textViewOrderDetailCustomerName);

        getCartDetailList();

        mOrderInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mOrderDetailDate = (String) dataSnapshot.child("eventDate").getValue();
                mOrderDetailEvent = (String) dataSnapshot.child("eventName").getValue();
//                mOrderDetailAddress = (String) dataSnapshot.child("eventAddress").getValue();
                mCustomerPhoto = (String) dataSnapshot.child("customerPhoto").getValue();
                mCustomerName = (String) dataSnapshot.child("customerName").getValue();

                Glide.with(getActivity())
                        .load(mCustomerPhoto)
                        .centerCrop()
                        .into(mImageViewOrderDetail);
                mTextViewOrderDetailCustomerName.setText(mCustomerName);
                mTextViewOrderDetailDate.setText("Event date: "+mOrderDetailDate);
                mTextViewOrderDetailEvent.setText("For "+mOrderDetailEvent);
//                mTextViewOrderDetailAddress.setText("Zip: "+mOrderDetailAddress);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    void getCartDetailList() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerOrderDetailFragment, FragmentUtil.getOrderListItemFragment())
                .commit();
    }

}
