package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorProductFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = "VendorProductFragment";
    private FloatingActionButton mFabAddProduct;

    public VendorProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_vendor_menu_add, container, false);

        mFabAddProduct = (FloatingActionButton) view.findViewById(R.id.fabAddMenuItem);
        mFabAddProduct.setOnClickListener(this);
        getMenuList();

        return view;
    }

    void getMenuList() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerMenuItemList, FragmentUtil.getVendorProductRecyclerFragment())
                .commit();
    }
    void openDialogFragment(){
        new VendorAddProductDialogFragment().show(getFragmentManager(), "VendorAddProductDialogFragment");
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabAddMenuItem:
                openDialogFragment();
                break;
            default:
        }

    }
}
