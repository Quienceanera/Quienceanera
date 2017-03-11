package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.CartListAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerInputFragment;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.CartListViewholder;

public class CartListItemFragment extends Fragment {
    private static final String TAG = "CartListItemFragment";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private String mKey;
    private int mCount, mLastPosition;
    private CartListAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;

    public CartListItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (EventsAdapter.mEventKey != null) {
            mAdapter = new CartListAdapter(
                    Items.class,
                    R.layout.fragment_cart_item,
                    CartListViewholder.class,
                    FirebaseUtil.getUserCartList().child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid),
                    getActivity());
        }
        if (ConsumerInputFragment.mEventKey != null) {
            mAdapter = new CartListAdapter(
                    Items.class,
                    R.layout.fragment_cart_item,
                    CartListViewholder.class,
                    FirebaseUtil.getUserCartList().child(ConsumerInputFragment.mEventKey).child(CartHomeAdapter.mVendorUid),
                    getActivity());
        }

        mLayoutManger = new LinearLayoutManager(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_item_list, container, false);
        view.setTag(TAG);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCartList);
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
