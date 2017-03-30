package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.Objects.ChatHome;
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

import static com.example.eventmakr.eventmakr.Utils.FirebaseUtil.getConsumerSideConsumerChatRef;

public class ContactVendorFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ContactVendorFragment";
    private TextView mTextViewTotal, mTextViewTotalQuantity;
    private CardView mButtonContactVendor;
    private String mPrice, mQuantity, mChatKey, mChatWelcome;
    private DatabaseReference mDatabaseRef, mItemsRef, mUserCartRef, mVendorCartRef, mUserChatHomeRef, mVendorChatHomeRef, mUserMessageRef, mVendorMessageRef;
    private String mVendorUid, mVendorWelcome, mPriceTotal, mItemCount;
    private FloatingActionButton mFabRecommendVendor;

    public ContactVendorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_vendor, container, false);
        mTextViewTotal = (TextView) view.findViewById(R.id.textViewTotalPrice);
        mTextViewTotalQuantity = (TextView) view.findViewById(R.id.textViewTotalQuantity);
        mButtonContactVendor = (CardView) view.findViewById(R.id.buttonContactVendor);
        mButtonContactVendor.setOnClickListener(this);
        mVendorUid = VendorAdapter.mVendorUid;

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
        mItemsRef = FirebaseUtil.getConsumerSideConsumerOrderRef().child(mVendorUid);
        mItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum = 0;
                int quantity = 0;
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    int mChildrenNum = ((int) dataSnapshot.getChildrenCount());
                    Float mChildPrice = Float.parseFloat(child.child("price").getValue().toString());
                    int mChildQuantity = Integer.valueOf(child.child("quantity").getValue().toString());
                    double mChildTotal = mChildPrice * mChildQuantity;
                    sum += mChildTotal;
                    quantity += mChildQuantity;
//                    quantity = (int) (mChildQuantity * Double.parseDouble(String.valueOf(mChildrenNum)));
                    Log.i("Quantity inside", String.valueOf(quantity));
                }
                mTextViewTotal.setText("Total Price: " + "$" +String.valueOf(sum));
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
        pushToChatHome();
        Log.i("Contact Vendor", "True");
    }

    void pushToChatHome() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());

        mUserChatHomeRef = getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).child(mVendorUid);
        mVendorChatHomeRef = FirebaseUtil.getConsumerSideVendorChatRef().child(FirebaseUtil.getUid());

        ChatHome chatHome = new ChatHome(
                VendorAdapter.mVendorName,
                VendorAdapter.mVendorLogo,
                VendorAdapter.mVendorUid,
                FirebaseUtil.getUserName(),
                FirebaseUtil.getUser().getPhotoUrl().toString(),
                FirebaseUtil.getUid(),
                mCurrentTimestamp,
                EventsAdapter.mEventKey,
                EventsAdapter.mEventName,
                EventsAdapter.mEventDate
            );
            mUserChatHomeRef.setValue(chatHome);
            mVendorChatHomeRef.setValue(chatHome);
            postMessage();
        Log.i("Push to chat home", "True");
    }

    void postMessage() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseRef = FirebaseUtil.getBaseRef().push();
        mChatKey = "1";
        mUserMessageRef = FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).child(mVendorUid).child(mChatKey);
        mVendorMessageRef = FirebaseUtil.getConsumerSideVendorMessageRef().child(mChatKey);

        Message message = new Message(
                mVendorWelcome,
                VendorAdapter.mVendorName,
                VendorAdapter.mVendorLogo,
                EventsAdapter.mEventKey,
                mVendorUid,
                mVendorUid,
                mCurrentTimestamp
        );

        mUserMessageRef.setValue(message);
        mVendorMessageRef.setValue(message);

        pushToCart();
        Log.i("Post Message", "True");
    }

    void pushToCart () {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());

        mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(VendorAdapter.mVendorUid);
        mVendorCartRef = FirebaseUtil.getConsumerSideVendorOrderInfoRef();

            Cart cart = new Cart(
                    EventsAdapter.mEventDate,
                    EventsAdapter.mEventType,
                    EventsAdapter.mEventAddress,
                    EventsAdapter.mEventName,
                    EventsAdapter.mEventKey,
                    FirebaseUtil.getUid(),
                    mVendorUid,
                    mPriceTotal,
                    mQuantity,
                    VendorAdapter.mVendorName,
                    VendorAdapter.mVendorLogo,
                    mCurrentTimestamp
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
        FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                .setImageRecourse(R.drawable.message_text_outline2)
                .setTextTitle("Contacted!")
                .setTitleColor(R.color.blue)
                .setTextSubTitle("View your messages and orders in the Chat and Cart tabs!")
                .setPositiveButtonText("Continue")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        goToConsumerActivity();
                        dialog.dismiss();
                    }
                })
                .build();
        alert.show();
        Log.i("Push to Cart", "True");
    }

    void goToConsumerActivity(){
        ConsumerActivity.mContactVendor = true;
        startActivity(new Intent(getActivity(), ConsumerActivity.class));
    }
}
