package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.Viewholder;

public class RecyclerVendorFragment extends Fragment {

    private static final String TAG = "RecyclerVendorFragment";
    private RecyclerView mRecyclerView;
    private VendorAdapter mVendorAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mVendorAdapter = new VendorAdapter(
                Vendor.class,
                R.layout.vendor_card_view,
                Viewholder.class,
                FirebaseUtil.getVendorRef(),
                getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vendor_item_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewVendorList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVendorAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);
        return rootView;
    }
}
