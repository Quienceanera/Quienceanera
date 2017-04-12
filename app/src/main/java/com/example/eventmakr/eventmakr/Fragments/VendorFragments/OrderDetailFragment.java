package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


public class OrderDetailFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = OrderDetailFragment.class.getSimpleName();
    private CircleImageView mImageViewOrderDetail;
    private TextView mTextViewOrderDetailDate, mTextViewOrderDetailEvent, mTextViewOrderDetailAddress, mTextViewOrderDetailCustomerName;
    private String mOrderDetailDate, mOrderDetailEvent, mOrderDetailAddress, mCustomerPhoto, mCustomerName, mCustomerUid, mConfirm, mConfirmNotify;
    private DatabaseReference mVendorOrderInfoRef, mConsumerOrderInfoRef;
    private FloatingActionButton mFabConfirm;
    private Boolean mProcessConfirm;
    private int mImage;
    private ValueEventListener mValueEventListener;


    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
            mVendorOrderInfoRef = FirebaseUtil.getVendorSideVendorOrderHomeRef().child(VendorOrderHomeAdapter.mEventKey);
        mConsumerOrderInfoRef = FirebaseUtil.getVendorSideConsumerOrderInfoRef();

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

        mFabConfirm = (FloatingActionButton) view.findViewById(R.id.fabConfirm);
        mFabConfirm.setOnClickListener(this);

        mVendorOrderInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mOrderDetailDate = (String) dataSnapshot.child("eventDate").getValue();
                mOrderDetailEvent = (String) dataSnapshot.child("eventName").getValue();
                mOrderDetailAddress = (String) dataSnapshot.child("zipCode").getValue();
                mCustomerPhoto = (String) dataSnapshot.child("customerPhoto").getValue();
                mCustomerName = (String) dataSnapshot.child("customerName").getValue();

                Glide.with(getActivity())
                        .load(mCustomerPhoto)
                        .centerCrop()
                        .into(mImageViewOrderDetail);
                mTextViewOrderDetailCustomerName.setText(mCustomerName);
                mTextViewOrderDetailDate.setText("Event date: "+mOrderDetailDate);
                mTextViewOrderDetailEvent.setText("For "+mOrderDetailEvent);
                mTextViewOrderDetailAddress.setText("Zip: "+mOrderDetailAddress);

                if (dataSnapshot.hasChild(FirebaseUtil.getUid())){
                    Log.i("Datasnapshot", "True");
                    mConsumerOrderInfoRef.child("confirm").setValue("true");
                    mFabConfirm.setImageResource(R.drawable.checkbox_marked_circle);


                }else{
                    mFabConfirm.setImageResource(R.drawable.check_circle_outline);
                    mConsumerOrderInfoRef.child("confirm").removeValue();
                    Log.i("Datasnapshot", "false");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void confirmProcess(){
        mProcessConfirm = true;
        mValueEventListener = mVendorOrderInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mProcessConfirm){
                    if (dataSnapshot.hasChild(FirebaseUtil.getUid())){
                        mVendorOrderInfoRef.child(FirebaseUtil.getUid()).removeValue();
                        mProcessConfirm = false;
                        Log.i("DataSnapshot 1", dataSnapshot.toString());
                        mConfirm = "Confirmation Canceled";
                        mConfirmNotify = "Customer Has Been Notified!";
                        mImage = R.drawable.check_circle_outline2;
                        showConfirmationStatus();
                    }else{
                        mVendorOrderInfoRef.child(FirebaseUtil.getUid()).setValue("true");
                        mProcessConfirm = false;
                        Log.i("DataSnapshot 2", dataSnapshot.toString());
                        mConfirm = "Confirmed!";
                        mConfirmNotify = "Customer Has Been Notified!";
                        mImage = R.drawable.checkbox_marked_circle2;
                        showConfirmationStatus();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void showConfirmationStatus(){
        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                .setBackgroundColor(R.color.colorAccentLighter)
                .setImageRecourse(mImage)
                .setTextTitle(mConfirm)
                .setTitleColor(R.color.blue)
                .setTextSubTitle(mConfirmNotify)
                .setPositiveButtonText("Okay")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        alert.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewAnimator.animate(mFabConfirm)
                .bounceIn()
                .duration(1500)
                .start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mValueEventListener != null){
            mVendorOrderInfoRef.removeEventListener(mValueEventListener);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabConfirm:
                confirmProcess();
                break;
            default:
        }

    }
}
