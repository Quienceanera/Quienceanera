package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
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

import static com.example.eventmakr.eventmakr.R.drawable.chat;

public class ContactVendorFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ContactVendorFragment";
    private TextView mTextViewTotal;
    private CardView mButtonContactVendor;
    private String mPrice, mQuantity, mKey, mChatWelcome;
    private DatabaseReference mDatabaseReference, mDatabaseRef;
    private String mVendorLogo, mVendorName, mVendorUid, mVendorWelcome, mChatKey;



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
        FirebaseUtil.getCartRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mKey = dataSnapshot.getKey();
                mPrice = (String) dataSnapshot.child(mKey).child("price").getValue();
                mQuantity = (String) dataSnapshot.child("quantity").getValue();
                Toast.makeText(getActivity(), mPrice + " " + mQuantity, Toast.LENGTH_SHORT).show();

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

    void pushToChat () {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseReference = FirebaseUtil.getChatRef();
        mDatabaseRef = mDatabaseReference.push();
        mChatKey = mDatabaseRef.getKey();
        ChatHome chatHome = new ChatHome(
                mVendorName,
                mVendorLogo,
                mVendorUid,
                mCurrentTimestamp,
                mChatKey
        );
        mDatabaseRef.setValue(chat);
    }

    void contactVendor () {
//        mVendorWelcome = "Chat with us here!";
        mVendorName = VendorAdapter.mVendorName;
        mVendorLogo = VendorAdapter.mVendorLogo;
        mVendorUid = VendorAdapter.mVendorUid;
        pushToChat();
    }

}
