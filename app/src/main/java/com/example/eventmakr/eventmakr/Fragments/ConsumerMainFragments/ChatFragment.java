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
import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChatFragment extends android.app.Fragment implements View.OnClickListener {
    private static final String TAG = ChatFragment.class.getSimpleName();
    private String mPhotoUrl, mUsername, mUid, mChatPath, mChatKey, mVendorUid;
    private DatabaseReference mDatabaseReference, mDatabaseRef, mNewMessageRef, mVendorMessageRef, mConsumerMessageRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String SENDER_ID = FirebaseUtil.getUid();
    private Viewholder mViewHolder;
    private FloatingActionButton mFabSend;
    private EditText mEditTextChat;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        if (mAuth.getCurrentUser() != null){
            mPhotoUrl = mAuth.getCurrentUser().getPhotoUrl().toString();
            mUsername = mAuth.getCurrentUser().getDisplayName();
            mUid = mAuth.getCurrentUser().getUid();
        }

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
                    mEditTextChat.setError(getString(R.string.empty));
                }else {
                    postChat();
                }
                break;
            default:
        }
    }
    void postChat() {
        SimpleDateFormat time = new SimpleDateFormat(getString(R.string.date_format), Locale.US);
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseRef = FirebaseUtil.getBaseRef().push();
        mChatKey = mDatabaseRef.getKey();
//        Log.i("Chat Message Key", mChatKey);
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){

            mVendorMessageRef = FirebaseUtil.getVendorSideVendorMessageRef().child(mChatKey);
            mConsumerMessageRef = FirebaseUtil.getVendorSideConsumerMessageRef().child(mChatKey);
            mDatabaseReference = FirebaseUtil.getNotificationRef().child("messages").push();
            mNewMessageRef = FirebaseUtil.getBaseRef().child("newMessage").child(VendorOrderHomeAdapter.mCustomerUid).child(VendorOrderHomeAdapter.mEventKey).push();

        } else {
                mConsumerMessageRef = FirebaseUtil.getConsumerSideConsumerMessageRef().child(mChatKey);
                mVendorMessageRef = FirebaseUtil.getConsumerSideVendorMessageRef().child(mChatKey);
            mDatabaseReference = FirebaseUtil.getNotificationRef().child("messages").push();
            mNewMessageRef = FirebaseUtil.getBaseRef().child("newMessage").child(CartHomeAdapter.mVendorUid).child(EventsAdapter.mEventKey).push();
        }

        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            Message message = new Message(
                    mEditTextChat.getText().toString(),
                    FirebaseUtil.getUserName(),
                    FirebaseUtil.getUser().getPhotoUrl().toString(),
                    VendorOrderHomeAdapter.mEventKey,
                    FirebaseUtil.getUid(),
                    VendorOrderHomeAdapter.mCustomerUid,
                    mCurrentTimestamp
            );
            mVendorMessageRef.setValue(message);
            mConsumerMessageRef.setValue(message);
            mDatabaseReference.setValue(message);
            mNewMessageRef.setValue("true");
            mEditTextChat.setText("");
        } else {
            Message message = new Message(
                    mEditTextChat.getText().toString(),
                    mUsername,
                    mPhotoUrl,
                    EventsAdapter.mEventKey,
                    FirebaseUtil.getUid(),
                    CartHomeAdapter.mVendorUid,
                    mCurrentTimestamp
            );
            mConsumerMessageRef.setValue(message);
            mVendorMessageRef.setValue(message);
            mDatabaseReference.setValue(message);
            mNewMessageRef.setValue("true");
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
