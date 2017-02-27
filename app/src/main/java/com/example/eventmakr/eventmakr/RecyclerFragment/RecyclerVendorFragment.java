package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerBudgetFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerVendorCategoryFragment;
import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class RecyclerVendorFragment extends Fragment {

    private static final String TAG = "RecyclerVendorFragment";
    private RecyclerView mRecyclerView;
    private VendorAdapter mVendorAdapter;
    public static String mCategory, mPriceRange;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mCategory = ConsumerVendorCategoryFragment.mCategory;
        mPriceRange = ConsumerBudgetFragment.mPriceRange;
        if (mCategory != null) {
            Toast.makeText(getActivity(), mCategory, Toast.LENGTH_SHORT).show();
            mVendorAdapter = new VendorAdapter(
                    Vendor.class,
                    R.layout.vendor_card_view,
                    Viewholder.class,
                    FirebaseUtil.getVendorRef().child(mCategory),
                    getActivity());
            mVendorAdapter.notifyDataSetChanged();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vendor_item_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewVendorList);
        if (mLayoutManager == null) {
            mRecyclerView.setLayoutManager(mLayoutManager);
        } else {
            mLayoutManager.removeAllViews();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setAdapter(mVendorAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVendorAdapter != null) {
            mVendorAdapter.cleanup();
            mVendorAdapter.notifyDataSetChanged();
        }
    }
}
