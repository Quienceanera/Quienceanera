package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorProfileProductAdapter;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.Objects.VendorOrderItem;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class ConsumerVendorProductItemFragment extends android.app.Fragment implements View.OnClickListener {
    private static final String TAG = "ConsumerVendorProductItemFragment";
//    private OnFragmentInteractionListener mListener;
    private CardView mButtonProductItemSelect;
    private Context mContext;
    private DatabaseReference mUserCartRef, mVendorCartRef, mPushRef, mUserMenuRef;
    private VendorProfileProductAdapter mVendorProfileProductAdapter;
    private ImageView mImageViewProductItem;
    private TextView mTextViewProductItemName, mTextViewProductItemDetails, mTextViewProductItemPrice, mTextViewProductVendorName;
    public static String mProductKey;
    private String mProductImage, mProductName, mProductDetails, mProductPrice, mProductQuantity, mVendorUid, mUid, mKey, mVendorName;
    private EditText mEditTextQuantity;

    public ConsumerVendorProductItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mProductKey = mVendorProfileProductAdapter.mProductKey;
        mVendorUid = mVendorProfileProductAdapter.mVendorUid;
        mVendorName = mVendorProfileProductAdapter.mVendorName;
        mUid = FirebaseUtil.getUser().getUid();
        mUserMenuRef = FirebaseUtil.getConsumerSideVendorProductRef();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_item, container, false);
        mButtonProductItemSelect = (CardView) view.findViewById(R.id.buttonProductItemSelect);
        mImageViewProductItem = (ImageView) view.findViewById(R.id.imageViewVendorProduct);
        mTextViewProductItemName = (TextView) view.findViewById(R.id.textViewVendorProductName);
        mTextViewProductItemDetails = (TextView) view.findViewById(R.id.textViewVendorProductDetails);
        mTextViewProductItemPrice = (TextView) view.findViewById(R.id.textViewVendorProductPrice);
        mTextViewProductVendorName = (TextView) view.findViewById(R.id.textViewVendorName);
        mEditTextQuantity = (EditText) view.findViewById(R.id.editTextQuantity);

        getProductInfo();

        mButtonProductItemSelect.setOnClickListener(this);
        return view;
    }

    public void getProductInfo() {
        mUserMenuRef.child(mProductKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProductImage = (String) dataSnapshot.child("photo").getValue();
                mProductName = (String) dataSnapshot.child("name").getValue();
                mProductDetails = (String) dataSnapshot.child("details").getValue();
                mProductPrice = (String) dataSnapshot.child("price").getValue();

                Glide.with(mContext)
                        .load(mProductImage)
                        .centerCrop()
                        .into(mImageViewProductItem);
                mTextViewProductItemName.setText(mProductName);
                mTextViewProductItemDetails.setText(mProductDetails);
                mTextViewProductItemPrice.setText("$ " + mProductPrice);
                mTextViewProductVendorName.setText(VendorAdapter.mVendorName);

                mEditTextQuantity.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
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
            case R.id.buttonProductItemSelect:
                if (mEditTextQuantity != null){
                    getKey();
                }else {
                }
                break;
                default:
        }

    }
    void getKey () {
            mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderRef().child(VendorAdapter.mVendorUid);
            Log.i("EventAdapter Key", EventsAdapter.mEventKey);
        mVendorCartRef = FirebaseUtil.getConsumerSideVendorOrderRef();
        mPushRef = mUserCartRef.push();
        mKey = mPushRef.getKey();
        addToMyItems();
    }

    public void addToMyItems() {
        mProductQuantity = mEditTextQuantity.getText().toString();

        if (EventsAdapter.mEventKey != null) {
            Items items = new Items(
                    mProductKey,
                    mProductQuantity,
                    mProductPrice,
                    null,
                    mProductName,
                    mProductImage,
                    mVendorName,
                    mKey,
                    mVendorUid
            );
            mUserCartRef.child(mKey).setValue(items);
        }

        if (EventsAdapter.mEventKey != null) {
            VendorOrderItem vendorOrderItem = new VendorOrderItem(
                    mProductKey,
                    mProductQuantity,
                    mProductPrice,
                    "totalPrice",
                    mProductName,
                    mProductImage
            );
            mVendorCartRef.child(mKey).setValue(vendorOrderItem);
        }
        returnToVendorProfile();

    }
    void returnToVendorProfile () {
        Snackbar.make(getActivity().findViewById(R.id.layoutEvent),"ADDED TO CART!", Snackbar.LENGTH_SHORT).show();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerVendorProfileFragment())
                .disallowAddToBackStack()
                .commit();
    }


}
