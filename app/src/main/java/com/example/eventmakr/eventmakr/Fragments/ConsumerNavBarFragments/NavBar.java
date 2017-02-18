package com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;


public class NavBar extends Fragment implements View.OnClickListener {
    private ImageView mButtonHome, mButtonChat, mButtonCart, mButtonUser;
    private FragmentManager mFragmentManager;


    public NavBar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumer_nav_bar, container, false);
        mButtonHome = (ImageView) view.findViewById(R.id.navButton_home);
        mButtonChat = (ImageView) view.findViewById(R.id.navButton_chat);
        mButtonCart = (ImageView) view.findViewById(R.id.navButton_cart);
        mButtonUser = (ImageView) view.findViewById(R.id.navButton_user);
        mButtonHome.setOnClickListener(this);
        mButtonChat.setOnClickListener(this);
        mButtonCart.setOnClickListener(this);
        mButtonUser.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.navButton_home:
                Intent intent = new Intent(getActivity(), ConsumerActivity.class);
                startActivity(intent);
                break;
            case R.id.navButton_chat:
                getChatFragment();
                break;
            case R.id.navButton_cart:
                getCartFragment();
                break;
            case R.id.navButton_user:
                getUserFragment();
                break;
            default:
        }
    }

    public void getUserFragment () {
        FragmentManager mFragmentManagerSupport = getActivity().getSupportFragmentManager();
        mFragmentManagerSupport
                .beginTransaction()
                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getUserFragment())
                .addToBackStack(null)
                .commit();
    }

    public void getChatFragment () {
        FragmentManager mFragmentManagerSupport = getActivity().getSupportFragmentManager();
        mFragmentManagerSupport
                .beginTransaction()
                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getChatFragment())
                .addToBackStack(null)
                .commit();
    }

    public void getCartFragment () {
        FragmentManager mFragmentManagerSupport = getActivity().getSupportFragmentManager();
        mFragmentManagerSupport
                .beginTransaction()
                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getCartFragment())
                .addToBackStack(null)
                .commit();
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

}
