package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.CartListAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.CartListViewholder;

public class OrderListRecyclerFragment extends Fragment {
    private static final String TAG = "OrderListRecyclerFragment";
    private RecyclerView mRecyclerView;
    private CartListAdapter mAdapter;
    private GridLayoutManager mLayoutManger;

    public OrderListRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (EventsAdapter.mEventKey != null) {
            mAdapter = new CartListAdapter(
                    Items.class,
                    R.layout.fragment_cart_item,
                    CartListViewholder.class,
                    FirebaseUtil.getConsumerSideConsumerOrderRef().child(CartHomeAdapter.mVendorUid),
                    getActivity());
        }
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            mAdapter = new CartListAdapter(
                    Items.class,
                    R.layout.fragment_cart_item,
                    CartListViewholder.class,
                    FirebaseUtil.getVendorSideVendorOrderListRef(),
                    getActivity());
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_item_list, container, false);
        view.setTag(TAG);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCartList);
        mLayoutManger = new GridLayoutManager(getActivity(), 2);
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
