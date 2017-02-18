package com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Objects.Chat;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.Viewholder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private String mPhotoUrl, mUsername, mUid, mChatPath, mChatKey;
    private DatabaseReference mDatabaseReference, mDatabaseRef;
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
//        Toast.makeText(getActivity(), "chat opened", Toast.LENGTH_SHORT).show();

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
                postChat();
                Toast.makeText(getActivity(), "send", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
    void postChat() {
        SimpleDateFormat time = new SimpleDateFormat("MM/dd-hh:mm");
        final String mCurrentTimestamp = time.format(new Date());
        mDatabaseReference = FirebaseUtil.getChatRef();
        mDatabaseRef = mDatabaseReference.push();
        mChatKey = mDatabaseRef.getKey();
        Chat chat = new Chat(
                mEditTextChat.getText().toString(),
                mUsername,
                mPhotoUrl,
                mChatKey,
                mUid,
                mCurrentTimestamp
        );
        mDatabaseRef.setValue(chat);
        mEditTextChat.setText("");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
