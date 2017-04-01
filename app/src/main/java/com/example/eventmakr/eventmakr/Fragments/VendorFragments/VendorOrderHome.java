package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorOrderHome extends Fragment {
    private static final String TAG = "VendorOrderHome";

    public VendorOrderHome() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vendor_order_home, container, false);
        Log.i(TAG, TAG);
        getOrderList();
        return view;
    }

    void getOrderList(){
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerOrderHome, FragmentUtil.getVendorOrderHomeListFragment())
                .commit();
    }
}
