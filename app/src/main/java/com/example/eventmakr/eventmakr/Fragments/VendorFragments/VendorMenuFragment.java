package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorMenuFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "VendorMenuFragment";
    private TextView mButtonProfile, mButtonDocuments, mButtonProducts;

    public VendorMenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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
                    getVendorInputFragment();
                break;
            case R.id.buttonVendorMenuDocuments:
                break;
            case R.id.buttonVendorMenuProducts:
                getVendorProductFragment();
                default:
        }

    }
    void getVendorInputFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getVendorInputFragment())
                .commit();
    }
    void getVendorProductFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getVendorProductFragment())
                .commit();
    }
}
