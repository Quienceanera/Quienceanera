package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.Objects.Chat;
import com.example.eventmakr.eventmakr.Objects.ChatHome;
import com.example.eventmakr.eventmakr.Objects.VendorOrderHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.eventmakr.eventmakr.Utils.FirebaseUtil.getConsumerSideConsumerChatRef;

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
        getActivity().getActionBar().setTitle("Contact Vendor to confirm");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_vendor, container, false);
        mTextViewTotal = (TextView) view.findViewById(R.id.textViewTotalPrice);
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
            mItemsRef = FirebaseUtil.getConsumerSideConsumerOrderRef().child(EventsAdapter.mEventKey).child(mVendorUid);

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
                    quantity = (int) (mChildQuantity * Double.parseDouble(String.valueOf(mChildTotal)));
                }
                mTextViewTotal.setText("Total Price " + "$" +String.valueOf(sum));
                mPriceTotal = String.valueOf(sum);
                mQuantity = String.valueOf(quantity);
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
        mVendorName = VendorAdapter.mVendorName;
        mVendorLogo = VendorAdapter.mVendorLogo;
        mVendorUid = VendorAdapter.mVendorUid;
        pushToChatHome();
    }


    void pushToChatHome() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        if (EventsAdapter.mEventKey != null){
            mUserChatHomeRef = getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey).child(mVendorUid);
        }

        mVendorChatHomeRef = FirebaseUtil.getConsumerSideVendorChatRef().push();
        if (EventsAdapter.mEventKey != null){
            ChatHome chatHome = new ChatHome(
                    mVendorName,
                    mVendorLogo,
                    mVendorUid,
                    FirebaseUtil.getUid(),
                    mCurrentTimestamp,
                    EventsAdapter.mEventKey,
                    EventsAdapter.mEventName,
                    EventsAdapter.mEventDate
            );
            mUserChatHomeRef.setValue(chatHome);
            mVendorChatHomeRef.setValue(chatHome);
            postChat();
        }
    }


    void postChat() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        if (EventsAdapter.mEventKey != null){
            mUserMessageRef = FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).child(mVendorUid);
        }
        mVendorMessageRef = FirebaseUtil.getConsumerSideVendorMessageRef();
        mUserChatPushRef = mUserMessageRef.push();
        mChatKey = mUserChatPushRef.getKey();
        Chat chat = new Chat(
                mVendorWelcome,
                mVendorName,
                mVendorLogo,
                EventsAdapter.mEventKey,
                FirebaseUtil.getUid(),
                mVendorUid,
                mCurrentTimestamp
        );
        mUserMessageRef.child(mChatKey).setValue(chat);
        mVendorMessageRef.child(mChatKey).setValue(chat);
        pushToCart();
    }

    void pushToCart () {
        mOrderId = mChatKey;
        mUserCartRef = FirebaseUtil.getConsumerSideConsumerOrderInfoRef().child(VendorAdapter.mVendorUid);
        mVendorCartRef = FirebaseUtil.getConsumerSideVendorOrderInfoRef();
        mConsumerId = FirebaseUtil.getUid();
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());

        if (EventsAdapter.mEventKey != null){
            Cart cart = new Cart(
                    EventsAdapter.mEventDate,
                    EventsAdapter.mEventType,
                    EventsAdapter.mEventAddress,
                    EventsAdapter.mEventName,
                    mOrderId,
                    mConsumerId,
                    mVendorUid,
                    mPriceTotal,
                    mQuantity,
                    mVendorName,
                    mVendorLogo,
                    mCurrentTimestamp
            );
            mUserCartRef.setValue(cart);
        }

        if (EventsAdapter.mEventKey != null){
            VendorOrderHome vendorOrderHome = new VendorOrderHome(
                    FirebaseUtil.getUserName(),
                    FirebaseUtil.getUser().getPhotoUrl().toString(),
                    FirebaseUtil.getUid(),
                    mPriceTotal,
                    mQuantity,
                    mCurrentTimestamp,
                    EventsAdapter.mEventKey,
                    EventsAdapter.mEventName,
                    EventsAdapter.mEventDate
            );
            mVendorCartRef.setValue(vendorOrderHome);
        }
    }


}
