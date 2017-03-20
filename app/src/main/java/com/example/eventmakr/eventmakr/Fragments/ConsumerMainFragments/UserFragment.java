package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends android.app.Fragment {

    private CircleImageView mCircleImageViewUser;
    private TextView mTextViewUserName, mTextViewUserEmail, mTextViewUserLocation;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        mCircleImageViewUser = (CircleImageView) view.findViewById(R.id.circleImageViewUser);
        mTextViewUserName = (TextView) view.findViewById(R.id.textViewUserName);
        mTextViewUserEmail = (TextView) view.findViewById(R.id.textViewUserEmail);
        mTextViewUserLocation = (TextView) view.findViewById(R.id.textViewUserLocation);

        insertUserData();
        return view;
    }

    void insertUserData() {
        Glide.with(this)
                .load(FirebaseUtil.getUser().getPhotoUrl())
                .centerCrop()
                .into(mCircleImageViewUser);
        mTextViewUserName.setText(FirebaseUtil.getUser().getDisplayName());
        mTextViewUserEmail.setText(FirebaseUtil.getUser().getEmail());
    }

}
