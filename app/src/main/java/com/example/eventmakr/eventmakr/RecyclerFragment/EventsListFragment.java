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

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;

public class EventsListFragment extends Fragment {
    private static final String TAG = "EventsListFragment";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private EventsAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;

    public EventsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mAdapter = new EventsAdapter(
                Events.class,
                R.layout.events_item,
                EventsViewholder.class,
                FirebaseUtil.getEventsRef(),
                getActivity());

        mLayoutManger = new LinearLayoutManager(getActivity());

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_item_list, container, false);
        view.setTag(TAG);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEventsList);
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
