package com.example.eventmakr.eventmakr.Fragments.VendorNavBarFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;


public class VendorNavBar extends android.app.Fragment implements View.OnClickListener {
    private ImageView mButtonHome, mButtonChat, mButtonCart, mButtonMenu;

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
        mButtonMenu = (ImageView) view.findViewById(R.id.vendorNavButton_menu);
        mButtonHome.setOnClickListener(this);
        mButtonChat.setOnClickListener(this);
        mButtonCart.setOnClickListener(this);
        mButtonMenu.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.vendorNavButton_home:
                if (getActivity().findViewById(R.id.navVendorActivityLayout).isShown()){
                    getActivity().findViewById(R.id.vendorActivityLayout).setVisibility(View.VISIBLE);
                    getActivity().findViewById(R.id.navVendorActivityLayout).setVisibility(View.GONE);
                }
                else {
                    Intent intent = new Intent(getActivity(), ConsumerActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.vendorNavButton_chat:

                    getChatFragment();

                break;
            case R.id.vendorNavButton_cart:
                    getCartFragment();

                break;
            case R.id.vendorNavButton_menu:
                getMenuFragment();
                break;
            default:
        }
    }

    public void getMenuFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getVendorMenuFragment())
                .addToBackStack(null)
                .commit();
    }

    public void getChatFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getChatHomeFragment())
                .addToBackStack(null)
                .commit();
//        getActivity().findViewById(R.id.vendorActivityLayout).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navVendorActivityLayout).setVisibility(View.VISIBLE);
    }

    public void getCartFragment () {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getCartFragment())
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
