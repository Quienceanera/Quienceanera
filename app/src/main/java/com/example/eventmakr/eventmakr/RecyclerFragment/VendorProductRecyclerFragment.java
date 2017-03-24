package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.MenuAdapter;
import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class VendorProductRecyclerFragment extends Fragment {

    private static final String TAG = "VendorProductRecyclerFragment";
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;
//    private LinearLayoutManager mLayoutManager;
    private GridLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mMenuAdapter = new MenuAdapter(
                Menu.class,
                R.layout.menu_card_view,
                Viewholder.class,
                FirebaseUtil.getVendorSideVendorProductRef(),
                getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vendor_menu_item_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMenuItemList);
//        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMenuAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMenuAdapter != null) {
            mMenuAdapter.cleanup();
        }
    }
}
