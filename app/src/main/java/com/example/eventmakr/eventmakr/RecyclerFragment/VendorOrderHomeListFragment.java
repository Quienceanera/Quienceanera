package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.VendorOrderHomeAdapter;
import com.example.eventmakr.eventmakr.Objects.VendorOrderHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderHomeViewholder;

public class VendorOrderHomeListFragment extends Fragment {
    private static final String TAG = "VendorOrderHomeListFragment";
    private RecyclerView mRecyclerView;
    private VendorOrderHomeAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;

    public VendorOrderHomeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            mAdapter = new VendorOrderHomeAdapter(
                    VendorOrderHome.class,
                    R.layout.vendor_order_home_item,
                    VendorOrderHomeViewholder.class,
                    FirebaseUtil.getVendorSideVendorOrderHomeRef(),
                    getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vendor_order_home_list, container, false);
        view.setTag(TAG);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewVendorOrderHomeList);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }
}
