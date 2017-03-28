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

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Adapters.ChatHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.ChatHome;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.ChatHomeViewholder;

public class ChatRecyclerFragment extends Fragment {
    private static final String TAG = "ChatRecyclerFragment";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private String mKey;
    private int mCount, mLastPosition;
    private ChatHomeAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;

    public ChatRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            if (EventsAdapter.mEventKey != null) {
                mAdapter = new ChatHomeAdapter(
                        ChatHome.class,
                        R.layout.fragment_chat_home_item,
                        ChatHomeViewholder.class,
                        FirebaseUtil.getConsumerSideConsumerChatRef().child(EventsAdapter.mEventKey),
                        getActivity());
            }

        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            mAdapter = new ChatHomeAdapter(
                    ChatHome.class,
                    R.layout.fragment_chat_home_item,
                    ChatHomeViewholder.class,
                    FirebaseUtil.getVendorSideVendorChatRef(),
                    getActivity());
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_home_item_list, container, false);
        view.setTag(TAG);
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChatHomeList);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mCount = mAdapter.getItemCount();
                mLastPosition = mLayoutManger.findLastCompletelyVisibleItemPosition();
                if (mLastPosition == -1 || (positionStart >= (mCount - 1) && mLastPosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });
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
