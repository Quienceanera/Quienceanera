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

public class VendorOrdersFragment extends Fragment {
    private static final String TAG = "VendorOrdersFragment";

    public VendorOrdersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vendor_orders_fragment, container, false);
        Log.i(TAG,TAG);
        getOrderList();
        return view;
    }

    void getOrderList(){
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerVendorOrders, FragmentUtil.getOrderListItemFragment())
                .commit();
    }
}
