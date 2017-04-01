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
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.PreCartAdapter;
import com.example.eventmakr.eventmakr.Objects.Items;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class OrderRecyclerFragment extends Fragment{

    private static final String TAG = "OrderRecyclerFragment";
    private RecyclerView mRecyclerView;
    private PreCartAdapter mPreCartAdapter;
    private GridLayoutManager mLayoutManager;
    private String mVendorUid;

    @ Override
    public void onCreate(Bundle savedInstanceState){
        Log.i(TAG, TAG);
        if (EventsAdapter.mEventKey != null){
            if (CartHomeAdapter.mVendorUid != null) {
                mPreCartAdapter = new PreCartAdapter(
                        Items.class,
                        R.layout.items_card_view,
                        Viewholder.class,
                        FirebaseUtil.getConsumerSideConsumerOrderRef().child(CartHomeAdapter.mVendorUid),
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
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewItemsList);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mPreCartAdapter);
        mLayoutManager.setItemPrefetchEnabled(false);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPreCartAdapter != null) {
            mPreCartAdapter.cleanup();
        }

    }
}
