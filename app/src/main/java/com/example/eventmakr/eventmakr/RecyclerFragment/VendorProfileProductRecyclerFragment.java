package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.VendorProfileProductAdapter;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class VendorProfileProductRecyclerFragment extends Fragment {

    private static final String TAG = "VendorProfileProductRecyclerFragment";
    private RecyclerView mRecyclerView;
    private VendorProfileProductAdapter mVendorProfileProductAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mVendorProfileProductAdapter = new VendorProfileProductAdapter(
                Menu.class,
                R.layout.vendor_product_card_view,
                Viewholder.class,
                FirebaseUtil.getConsumerSideVendorProductRef(),
                getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vendor_menu_item_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMenuItemList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setAdapter(mVendorProfileProductAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVendorProfileProductAdapter != null) {
            mVendorProfileProductAdapter.cleanup();
        }
    }
}
