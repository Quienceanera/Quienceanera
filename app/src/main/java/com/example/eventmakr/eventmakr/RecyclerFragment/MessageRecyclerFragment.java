package com.example.eventmakr.eventmakr.RecyclerFragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Activities.ConsumerActivity;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ChatAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.Message;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.Viewholder;

public class MessageRecyclerFragment extends Fragment {
    private static final String TAG = "MessageRecyclerFragment";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private String mKey;
    private int mCount, mLastPosition;
    private ChatAdapter mAdapter;
    private String mVendorUid;
    private LinearLayoutManager mLayoutManger;

    public MessageRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, TAG);
        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
            mAdapter = new ChatAdapter(
                    Message.class,
                    R.layout.fragment_chat_item,
                    Viewholder.class,
                    FirebaseUtil.getVendorSideVendorMessageRef(),
                    mContext);
        } else {
            mAdapter = new ChatAdapter(
                    Message.class,
                    R.layout.fragment_chat_item,
                    Viewholder.class,
                    FirebaseUtil.getConsumerSideConsumerMessageRef().child(EventsAdapter.mEventKey).child(CartHomeAdapter.mVendorUid),
                    mContext);
        }

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_item_list, container, false);
        view.setTag(TAG);
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChatList);
        mLayoutManger = new LinearLayoutManager(mContext);
        mLayoutManger.setStackFromEnd(true);
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
