package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ChatHomeFragment extends android.app.Fragment implements View.OnClickListener {

    private String mPhotoUrl, mUsername, mUid, mChatPath, mChatKey;
    private DatabaseReference mDatabaseReference, mDatabaseRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public ChatHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoUrl = mAuth.getCurrentUser().getPhotoUrl().toString();
        mUsername = mAuth.getCurrentUser().getDisplayName();
        mUid = mAuth.getCurrentUser().getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chat_home, container, false);
        getChatHomeList();

        return view;
    }

    void getChatHomeList(){
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerChatHomeList, FragmentUtil.getChatHomeItemFragment())
                .commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
//            case R.id.
//                break;
            default:
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("ChatHome onStop", "Stop");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("ChatHome onPause", "Pause");
    }
}
