package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;

public class VendorMenuFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "VendorMenuFragment";

    public VendorMenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.vendor_menu_fragment, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
