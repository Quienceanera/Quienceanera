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

import com.example.eventmakr.eventmakr.Adapters.ChatAdapter;
import com.example.eventmakr.eventmakr.Adapters.ChatHomeAdapter;
import com.example.eventmakr.eventmakr.Objects.Chat;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class ChatItemFragment extends Fragment {
    private static final String TAG = "ChatItemFragment";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private String mKey;
    private int mCount, mLastPosition;
    private ChatAdapter mAdapter;
    private String mVendorUid;
    private LinearLayoutManager mLayoutManger;

    public ChatItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mVendorUid = ChatHomeAdapter.mVendorUid;
        if (mVendorUid != null) {
            mAdapter = new ChatAdapter(
                    Chat.class,
                    R.layout.fragment_chat_item,
                    Viewholder.class,
                    FirebaseUtil.getMessageRef().child(mVendorUid),
                    mContext);
        }
        mLayoutManger = new LinearLayoutManager(mContext);
        mLayoutManger.setStackFromEnd(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_item_list, container, false);
        view.setTag(TAG);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChatList);
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
