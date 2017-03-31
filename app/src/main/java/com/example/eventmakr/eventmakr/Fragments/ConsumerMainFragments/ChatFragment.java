package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ChatHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatFragment extends android.app.Fragment implements View.OnClickListener {

    private String mPhotoUrl, mUsername, mUid, mChatPath, mChatKey, mVendorUid;
    private DatabaseReference mDatabaseReference, mDatabaseRef, mConsumerSideVendorRef, mVendorMessageRef, mConsumerMessageRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Viewholder mViewHolder;
    private FloatingActionButton mFabSend;
    private EditText mEditTextChat;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoUrl = mAuth.getCurrentUser().getPhotoUrl().toString();
        mUsername = mAuth.getCurrentUser().getDisplayName();
        mUid = mAuth.getCurrentUser().getUid();
        mVendorUid = ChatHomeAdapter.mVendorUid;

        getChildMessagesList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mFabSend = (FloatingActionButton) view.findViewById(R.id.fabSend);
        mEditTextChat = (EditText) view.findViewById(R.id.chatEditText);
        mFabSend.setOnClickListener(this);
        mEditTextChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mFabSend.setEnabled(true);
                } else {
                    mFabSend.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fabSend:
                if (TextUtils.isEmpty(mEditTextChat.getText().toString())){
                }else {
                    postChat();
                }
                break;
            default:
        }
    }
    void postChat() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseRef = FirebaseUtil.getBaseRef().push();
        mChatKey = mDatabaseRef.getKey();
        Log.i("Chat Message Key", mChatKey);
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){

            mVendorMessageRef = FirebaseUtil.getVendorSideVendorMessageRef().child(mChatKey);
            mConsumerMessageRef = FirebaseUtil.getVendorSideConsumerMessageRef().child(mChatKey);

        } else {
                mConsumerMessageRef = FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid).child(mChatKey);
                mVendorMessageRef = FirebaseUtil.getConsumerSideVendorMessageRef().child(mChatKey);

        }
        Message message = new Message(
                mEditTextChat.getText().toString(),
                mUsername,
                mPhotoUrl,
                EventsAdapter.mEventKey,
                FirebaseUtil.getUid(),
                CartHomeAdapter.mVendorUid,
                mCurrentTimestamp
        );

        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            mVendorMessageRef.setValue(message);
            mConsumerMessageRef.setValue(message);
            mEditTextChat.setText("");
        } else {
            mConsumerMessageRef.setValue(message);
            mVendorMessageRef.setValue(message);
            mEditTextChat.setText("");
        }
    }

    void getChildMessagesList() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerChatItemList, FragmentUtil.getChatItemFragment())
                .commit();
    }
}
