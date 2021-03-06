package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Activities.PayActivity;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.Objects.VendorOrderHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContactVendorFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ContactVendorFragment";
    private TextView mTextViewTotal, mTextViewTotalQuantity;
    private CardView mButtonContactVendor;
    private String mPrice, mQuantity, mChatKey, mChatWelcome;
    private DatabaseReference mDatabaseRef, mItemsRef, mUserCartRef, mVendorCartRef, mUserChatHomeRef, mVendorChatHomeRef, mUserMessageRef, mVendorMessageRef;
    private String mVendorUid, mVendorWelcome, mPriceTotal, mItemCount;

    public ContactVendorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_vendor, container, false);
        mTextViewTotal = (TextView) view.findViewById(R.id.textViewTotalPrice);
        mTextViewTotalQuantity = (TextView) view.findViewById(R.id.textViewTotalQuantity);
        mButtonContactVendor = (CardView) view.findViewById(R.id.buttonContactVendor);
        mButtonContactVendor.setOnClickListener(this);

        getTotalPrice();
        getChildRecyclerItems();
        return view;
    }

    void getChildRecyclerItems () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerItemsFragment, FragmentUtil.getOrderRecyclerFragment())
                .commit();
    }

    void getTotalPrice () {
        mItemsRef = FirebaseUtil.getConsumerSideConsumerOrderRef().child(CartHomeAdapter.mVendorUid);
        mItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum = 0;
                int quantity = 0;
                for (DataSnapshot child: dataSnapshot.getChildren()) {
//                    int mChildrenNum = ((int) dataSnapshot.getChildrenCount());
                    Float mChildPrice = Float.parseFloat(child.child("price").getValue().toString());
                    int mChildQuantity = Integer.valueOf(child.child("quantity").getValue().toString());
                    double mChildTotal = mChildPrice * mChildQuantity;
                    sum += mChildTotal;
                    quantity += mChildQuantity;
//                    quantity = (int) (mChildQuantity * Double.parseDouble(String.valueOf(mChildrenNum)));
                    Log.i("Quantity inside", String.valueOf(quantity));
                }
                mTextViewTotal.setText(String.valueOf(sum));
                mTextViewTotalQuantity.setText("Qty:"+String.valueOf(quantity));
                mPriceTotal = String.valueOf(sum);
                mQuantity = String.valueOf(quantity);
                Log.i("Total Price", String.valueOf(sum));
                Log.i("Quantity", String.valueOf(quantity));
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
            case R.id.buttonContactVendor:
                contactVendor();
                break;
            default:
        }
    }

    void contactVendor () {
        mVendorWelcome = "Chat with us here!";
//        pushToChatHome();
        postMessage();
        Log.i("Contact Vendor", "True");
    }

//    void pushToChatHome() {
//        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm", Locale.US);
//        final String mCurrentTimestamp = time.format(new Date());
//
//        mUserChatHomeRef = getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid);
//        mVendorChatHomeRef = FirebaseUtil.getConsumerSideVendorChatRef().child(FirebaseUtil.getUid());
//
//        ChatHome chatHome = new ChatHome(
//                CartHomeAdapter.mVendorName,
//                CartHomeAdapter.mVendorLogo,
//                CartHomeAdapter.mVendorUid,
//                FirebaseUtil.getUserName(),
//                FirebaseUtil.getUser().getPhotoUrl().toString(),
//                FirebaseUtil.getUid(),
//                mCurrentTimestamp,
//                EventsAdapter.mEventKey,
//                EventsAdapter.mEventName,
//                EventsAdapter.mEventDate
//            );
//            mUserChatHomeRef.setValue(chatHome);
//            mVendorChatHomeRef.setValue(chatHome);
//            postMessage();
//        Log.i("Push to chat home", "True");
//    }

    void postMessage() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm", Locale.US);
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseRef = FirebaseUtil.getBaseRef().push();
        mChatKey = "1";
        mUserMessageRef = FirebaseUtil.getConsumerSideConsumerMessageRef().child(mChatKey);
        mVendorMessageRef = FirebaseUtil.getConsumerSideVendorMessageRef().child(mChatKey);

        Message message = new Message(
                mVendorWelcome,
                CartHomeAdapter.mVendorName,
                CartHomeAdapter.mVendorLogo,
                EventsAdapter.mEventKey,
                CartHomeAdapter.mVendorUid,
                FirebaseUtil.getUid(),
                mCurrentTimestamp
        );

        mUserMessageRef.setValue(message);
        mVendorMessageRef.setValue(message);

        pushToCart();
        Log.i("Post Message", "True");
    }

    void pushToCart () {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm", Locale.US);
        final String mCurrentTimestamp = time.format(new Date());

        mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(CartHomeAdapter.mVendorUid);
        mVendorCartRef = FirebaseUtil.getConsumerSideVendorOrderInfoRef();

            Cart cart = new Cart(
                    EventsAdapter.mEventDate,
                    EventsAdapter.mEventType,
                    EventsAdapter.mEventAddress,
                    EventsAdapter.mEventName,
                    EventsAdapter.mEventKey,
                    FirebaseUtil.getUid(),
                    CartHomeAdapter.mVendorUid,
                    mPriceTotal,
                    mQuantity,
                    CartHomeAdapter.mVendorName,
                    CartHomeAdapter.mVendorLogo,
                    mCurrentTimestamp,
                    "true",
                    "false"
            );
            mUserCartRef.setValue(cart);

            VendorOrderHome vendorOrderHome = new VendorOrderHome(
                    FirebaseUtil.getUserName(),
                    FirebaseUtil.getUser().getPhotoUrl().toString(),
                    FirebaseUtil.getUid(),
                    mPriceTotal,
                    mQuantity,
                    mCurrentTimestamp,
                    EventsAdapter.mEventKey,
                    EventsAdapter.mEventName,
                    EventsAdapter.mEventDate,
                    EventsAdapter.mEventAddress
            );
            mVendorCartRef.setValue(vendorOrderHome);
        FirebaseUtil.getNotificationRef().child("orders").push().setValue(vendorOrderHome);

        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                .setImageRecourse(R.drawable.message_text_outline2)
                .setBackgroundColor(R.color.colorAccentLighter)
                .setTextTitle(CartHomeAdapter.mVendorName)
                .setTitleColor(R.color.blue)
                .setTextSubTitle("Has Been Contacted!")
                .setPositiveButtonText("Continue")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                        restartActivity();
                    }
                })
                .build();
        alert.show();
        Log.i("Push to Cart", "True");
    }

    void restartActivity(){
        CartHomeAdapter.mConfirm = "true";
        Bundle bundle = new Bundle();
        getFragmentManager()
                .beginTransaction()
                .remove(FragmentUtil.getCartDetailFragment(bundle))
                .commit();
        startActivity(new Intent(getActivity(), PayActivity.class));
    }
}
