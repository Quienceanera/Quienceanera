package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.Objects.Chat;
import com.example.eventmakr.eventmakr.Objects.ChatHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactVendorFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ContactVendorFragment";
    private TextView mTextViewTotal;
    private CardView mButtonContactVendor;
    private String mPrice, mQuantity, mKey, mChatWelcome;
    private DatabaseReference mItemsRef, mDatabaseRef, mUserCartRef, mVendorCartRef, mUserChatHomeRef, mVendorChatHomeRef, mUserMessageRef, mVendorMessageRef, mDatabaseReference, mUserChatPushRef, mVendorChatPushRef;
    private String mVendorLogo, mVendorName, mVendorUid, mVendorWelcome, mChatKey, mOrderId, mConsumerId, VendorId, mPriceTotal, mItemCount;

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
        mButtonContactVendor = (CardView) view.findViewById(R.id.buttonContactVendor);
        mButtonContactVendor.setOnClickListener(this);
        mVendorUid = VendorAdapter.mVendorUid;
//        mDatabaseRef = mDatabaseReference.push();

//        Log.i("push ref  ", mDatabaseRef.getKey());

        getChildRecyclerItems();
        return view;
    }

    void getChildRecyclerItems () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerItemsFragment, FragmentUtil.getRecyclerItemsFragment())
                .commit();
    }

    void getTotalPrice () {
        mItemsRef = FirebaseUtil.getUserCartRef().child(mVendorUid);
        mItemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    int mChildrenNum = ((int) dataSnapshot.getChildrenCount());
                    Float mChildPrice = Float.parseFloat(child.child("price").getValue().toString());
                    int mChildQuantity = Integer.valueOf(child.child("quantity").getValue().toString());
                    double mChildTotal = mChildPrice * mChildQuantity;
                    Log.i("Price ", String.valueOf(mChildPrice));
                    Log.i("Quantity", String.valueOf(mChildQuantity));
                    Log.i("Total ", String.valueOf(mChildTotal));
                    Log.i("ChildCount ", String.valueOf(mChildrenNum));
//                    Log.i("Total All ", String.valueOf(mTotalAll));
                    double itemSum = mChildTotal;
                    int sum = (int) itemSum;
//                    int i = 0;
                    for (int i = 0; i < mChildrenNum; i += itemSum) {
//                        itemSum = i++;
                        Log.i("Inside TotalAll ", String.valueOf(i));
                    }
//                    Log.i("TotalAll ", String.valueOf(f));
//                    Toast.makeText(getActivity(), String.valueOf(itemSum), Toast.LENGTH_SHORT).show();
                }

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
//                getTotalPrice();
                break;
            default:
        }
    }

    void pushToCart () {
        mOrderId = mChatKey;

        mUserCartRef = FirebaseUtil.getUserCartInfoRef().child(mVendorUid);
        mVendorCartRef = FirebaseUtil.getVendorCartInfoRef();
        mConsumerId = FirebaseUtil.getUid();
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        Cart cart = new Cart(
                mOrderId,
                mConsumerId,
                mVendorUid,
                "price",
                "count",
                mVendorName,
                mVendorLogo,
                mCurrentTimestamp
        );

        mUserCartRef.setValue(cart);
        mVendorCartRef.setValue(cart);

    }

    void pushToChatHome() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mUserChatHomeRef = FirebaseUtil.getUserChatHomeRef().child(mVendorUid);
        mVendorChatHomeRef = FirebaseUtil.getVendorChatHomeRef().child(mVendorUid).child("chat").child(FirebaseUtil.getUid());

        ChatHome chatHome = new ChatHome(
                mVendorName,
                mVendorLogo,
                mVendorUid,
                mCurrentTimestamp,
                null
        );
        mUserChatHomeRef.setValue(chatHome);
        mVendorChatHomeRef.setValue(chatHome);
        postChat();
    }

    void contactVendor () {
        mVendorWelcome = "Chat with us here!";
        mVendorName = VendorAdapter.mVendorName;
        mVendorLogo = VendorAdapter.mVendorLogo;
        mVendorUid = VendorAdapter.mVendorUid;
        pushToChatHome();
    }

    void openChat () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.consumerActivityLayout, FragmentUtil.getChatHomeFragment())
                .commit();
    }

    void postChat() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mUserMessageRef = FirebaseUtil.getUserMessageRef().child(mVendorUid);
        mVendorMessageRef = FirebaseUtil.getVendorMessageRef().child(FirebaseUtil.getUid());
//        mUserChatPushRef = mUserMessageRef.push();
//        mChatKey = mUserChatPushRef.getKey();
//        mVendorChatPushRef = mVendorMessageRef.push();

        Chat chat = new Chat(
                mVendorWelcome,
                mVendorName,
                mVendorLogo,
                mChatKey,
                mVendorUid,
                mCurrentTimestamp
        );
//        Log.i("push key", mDatabaseRef.getKey());
        mUserMessageRef.setValue(chat);
        mVendorMessageRef.setValue(chat);

        pushToCart();
        openChat();
    }

}
