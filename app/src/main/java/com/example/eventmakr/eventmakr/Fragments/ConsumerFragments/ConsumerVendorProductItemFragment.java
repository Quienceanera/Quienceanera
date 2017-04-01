package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.Objects.VendorOrderItem;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ConsumerVendorProductItemFragment extends android.app.Fragment implements SpecialInstructionsDialogFragment.DialogListener{
    private static final String TAG = "ConsumerVendorProductItemFragment";
//    private OnFragmentInteractionListener mListener;
    private CardView mButtonProductItemSelect;
    private Context mContext;
    private DatabaseReference mUserCartRef, mVendorCartRef, mPushRef, mUserMenuRef;
    private VendorProfileProductAdapter mVendorProfileProductAdapter;
    private ImageView mImageViewProductItem;
    private TextView mTextViewProductItemName, mTextViewProductItemDetails, mTextViewProductItemPrice, mTextViewProductVendorName;
    public static String mProductKey;
    private String mProductImage, mProductName, mProductDetails, mProductPrice, mProductQuantity, mVendorUid, mUid, mKey, mVendorName, mInstructions1;
    private EditText mEditTextQuantity;

    public ConsumerVendorProductItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", TAG);
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

        mButtonProductItemSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextQuantity.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.layoutEvent),"Choose a Quantity", Snackbar.LENGTH_SHORT);
                    view = snackbar.getView();
                    view.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.blue));
                    snackbar.show();
                }else {
                    Log.i("EditTextQty", "Not Null");
                    mProductQuantity = mEditTextQuantity.getText().toString();
                    getSpecialInstructions();
                }
            }
        });
        return view;
    }

    public void getProductInfo() {
        mUserMenuRef.child(VendorProfileProductAdapter.mProductKey).addValueEventListener(new ValueEventListener() {
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
                mTextViewProductItemPrice.setText("$ " + mProductPrice+" ea");
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

    void getSpecialInstructions(){
                FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                .setImageRecourse(R.drawable.lead_pencil)
                .setTextTitle("Add Special Instructions?")
                .setTitleColor(R.color.blue)
                .setPositiveButtonText("Yes")
                        .setNegativeButtonText("No")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                        getSpecialInstructionsDialogFragment();
                    }
                })
                        .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {
                                dialog.dismiss();
                                getKey();
                            }
                        })
                .build();
        alert.show();
    }

    void getSpecialInstructionsDialogFragment(){
        SpecialInstructionsDialogFragment specialInstructionsDialogFragment = new SpecialInstructionsDialogFragment();
                specialInstructionsDialogFragment.setTargetFragment(ConsumerVendorProductItemFragment.this, 300);

        specialInstructionsDialogFragment.show(getFragmentManager(),"SpecialInstructionsDialogFragment");
    }

    @Override
    public void onFinish(String mInstructions) {
        mInstructions1 = mInstructions;
        getKey();
    }


   public void getKey () {
            mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderRef().child(VendorAdapter.mVendorUid);
            Log.i("EventAdapter Key", EventsAdapter.mEventKey);
        mVendorCartRef = FirebaseUtil.getConsumerSideVendorOrderRef();
        mPushRef = mUserCartRef.push();
        mKey = mPushRef.getKey();
        addToMyItems();
    }

    public void addToMyItems() {

        if (EventsAdapter.mEventKey != null) {
            Items items = new Items(
                    VendorProfileProductAdapter.mProductKey,
                    mProductQuantity,
                    mProductPrice,
                    mInstructions1,
                    mProductName,
                    mProductImage,
                    VendorAdapter.mVendorName,
                    VendorAdapter.mVendorUid,
                    VendorAdapter.mVendorUid
            );
            mUserCartRef.child(VendorProfileProductAdapter.mProductKey).setValue(items);
        }

        if (EventsAdapter.mEventKey != null) {
            VendorOrderItem vendorOrderItem = new VendorOrderItem(
                    VendorProfileProductAdapter.mProductKey,
                    mProductQuantity,
                    mProductPrice,
                    mInstructions1,
                    mProductName,
                    mProductImage
            );
            mVendorCartRef.child(VendorProfileProductAdapter.mProductKey).setValue(vendorOrderItem);
        }
        pushToCart();
    }

    void pushToCart () {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());

        mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(VendorAdapter.mVendorUid);

        Cart cart = new Cart(
                EventsAdapter.mEventDate,
                EventsAdapter.mEventType,
                EventsAdapter.mEventAddress,
                EventsAdapter.mEventName,
                EventsAdapter.mEventKey,
                FirebaseUtil.getUid(),
                VendorAdapter.mVendorUid,
                null,
                null,
                VendorAdapter.mVendorName,
                VendorAdapter.mVendorLogo,
                mCurrentTimestamp,
                "false",
                "false"
        );
        mUserCartRef.setValue(cart);


        Log.i("Push to Cart", "True");
        returnToVendorProfile();
    }

    void returnToVendorProfile () {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.layoutEvent),"Added to Cart!", Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.green));
        snackbar.show();
        getActivity().onBackPressed();
    }
}
