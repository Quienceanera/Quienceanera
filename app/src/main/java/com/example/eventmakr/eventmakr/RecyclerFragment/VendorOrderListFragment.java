package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.VendorOrdersAdapter;
import com.example.eventmakr.eventmakr.Objects.VendorOrderItem;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.VendorOrderListViewholder;

public class VendorOrderListFragment extends Fragment {
    private static final String TAG = "VendorOrderListFragment";
    private RecyclerView mRecyclerView;
    private VendorOrdersAdapter mAdapter;
    private GridLayoutManager mLayoutManger;

    public VendorOrderListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            mAdapter = new VendorOrdersAdapter(
                    VendorOrderItem.class,
                    R.layout.vendor_order_item,
                    VendorOrderListViewholder.class,
                    FirebaseUtil.getVendorSideVendorOrderListRef(),
                    getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vendor_order_list, container, false);
        view.setTag(TAG);
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewOrderList);
        mLayoutManger = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                mCount = mAdapter.getItemCount();
//                mLastPosition = mLayoutManger.findLastCompletelyVisibleItemPosition();
//                if (mLastPosition == -1 || (positionStart >= (mCount - 1) && mLastPosition == (positionStart - 1))) {
//                    mRecyclerView.scrollToPosition(positionStart);
//                }
//            }
//        });
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
