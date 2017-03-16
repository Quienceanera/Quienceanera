package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.ItemsAdapter;
import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerInputFragment;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class RecyclerItemsFragment extends Fragment{

    private static final String TAG = "RecyclerItemsFragment";
    private RecyclerView mRecyclerView;
    private ItemsAdapter mItemsAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mVendorUid;

    @ Override
    public void onCreate(Bundle savedInstanceState){
        mVendorUid = VendorAdapter.mVendorUid;
        if (EventsAdapter.mEventKey != null){
            if (mVendorUid != null) {
                mItemsAdapter = new ItemsAdapter(
                        Items.class,
                        R.layout.items_card_view,
                        Viewholder.class,
                        FirebaseUtil.getConsumerSideConsumerOrderRef().child(EventsAdapter.mEventKey).child(mVendorUid),
                        getActivity());
            }
        }
        if (ConsumerInputFragment.mEventKey != null) {
            if (mVendorUid != null) {
                mItemsAdapter = new ItemsAdapter(
                        Items.class,
                        R.layout.items_card_view,
                        Viewholder.class,
                        FirebaseUtil.getConsumerSideConsumerOrderRef().child(ConsumerInputFragment.mEventKey).child(mVendorUid),
                        getActivity());
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_items_list, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewItemsList);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mItemsAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mItemsAdapter != null) {
            mItemsAdapter.cleanup();
        }

    }
}
