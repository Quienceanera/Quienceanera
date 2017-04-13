package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Objects.Cart;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.CartHomeViewholder;

public class CartRecyclerFragment extends Fragment {
    private static final String TAG = "CartRecyclerFragment";
    RecyclerView mRecyclerView;
    private CartHomeAdapter mAdapter;
    GridLayoutManager mLayoutManger;

    public CartRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG);
            mAdapter = new CartHomeAdapter(
                    Cart.class,
                    R.layout.fragment_cart_home_item,
                    CartHomeViewholder.class,
                    FirebaseUtil.getConsumerSideConsumerOrderInfoRef(),
                    getActivity());

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_home_item_list, container, false);
        view.setTag(TAG);
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewCartHomeList);
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
