package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Activities.VendorExtrasActivity;
import com.example.eventmakr.eventmakr.R;

public class VendorMenuFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "VendorMenuFragment";
    private TextView mButtonProfile, mButtonDocuments, mButtonProducts;
    public static int mFragmentIntent;

    public VendorMenuFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vendor_menu_fragment, container, false);
        mButtonProfile = (TextView) view.findViewById(R.id.buttonVendorMenuProfile);
        mButtonDocuments = (TextView) view.findViewById(R.id.buttonVendorMenuDocuments);
        mButtonProducts = (TextView) view.findViewById(R.id.buttonVendorMenuProducts);
        mButtonProfile.setOnClickListener(this);
        mButtonDocuments.setOnClickListener(this);
        mButtonProducts.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.buttonVendorMenuProfile:
                mFragmentIntent = 1;
                openVendorExtras();
                break;
            case R.id.buttonVendorMenuDocuments:
                mFragmentIntent = 2;
                openVendorExtras();
                break;
            case R.id.buttonVendorMenuProducts:
                mFragmentIntent = 3;
                openVendorExtras();
                default:
        }

    }

    void openVendorExtras(){
        startActivity(new Intent(getActivity(), VendorExtrasActivity.class));
    }


}
