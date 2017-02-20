package com.example.eventmakr.eventmakr.Fragments.VendorNavBarFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;


public class VendorNavBar extends android.app.Fragment implements View.OnClickListener {
    private ImageView mButtonHome, mButtonChat, mButtonCart, mButtonUser;

    public VendorNavBar() {
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
        final View view = inflater.inflate(R.layout.fragment_vendor_nav_bar, container, false);
        mButtonHome = (ImageView) view.findViewById(R.id.vendorNavButton_home);
        mButtonChat = (ImageView) view.findViewById(R.id.vendorNavButton_chat);
        mButtonCart = (ImageView) view.findViewById(R.id.vendorNavButton_cart);
        mButtonUser = (ImageView) view.findViewById(R.id.vendorNavButton_user);
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
            case R.id.vendorNavButton_home:
                Intent intent = new Intent(getActivity(), VendorActivity.class);
                startActivity(intent);
                break;
            case R.id.vendorNavButton_chat:
                getChatFragment();
                break;
            case R.id.vendorNavButton_cart:
                getCartFragment();
                break;
            case R.id.vendorNavButton_user:
                getUserFragment();
                break;
            default:
        }
    }

    public void getUserFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.navVendorActivityLayout, FragmentUtil.getUserFragment())
                .addToBackStack(null)
                .commit();
    }

    public void getChatFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.navVendorActivityLayout, FragmentUtil.getChatFragment())
                .addToBackStack(null)
                .commit();
    }

    public void getCartFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.navVendorActivityLayout, FragmentUtil.getCartFragment())
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
