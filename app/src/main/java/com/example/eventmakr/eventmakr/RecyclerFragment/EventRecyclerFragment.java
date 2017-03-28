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
import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.ViewHolders.EventsViewholder;
import com.github.florent37.viewanimator.ViewAnimator;

public class EventRecyclerFragment extends Fragment {
    private static final String TAG = "EventRecyclerFragment";
    private RecyclerView mRecyclerView;
    private EventsAdapter mAdapter;
    private LinearLayoutManager mLayoutManger;

    public EventRecyclerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mAdapter = new EventsAdapter(
                Events.class,
                R.layout.events_item,
                EventsViewholder.class,
                FirebaseUtil.getEventsRef(),
                getActivity());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_item_list, container, false);
        view.setTag(TAG);
        if (container != null){
            container.removeAllViews();
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEventsList);
        mLayoutManger = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    ViewAnimator.animate(getActivity().findViewById(R.id.fabNewEvent))
                            .rollOut()
                            .duration(500)
                            .andAnimate(getActivity().findViewById(R.id.cardViewCreateEvent_helper))
                            .fadeOut()
                            .duration(500)
                            .start();
                } else if (dy < 0){
                    ViewAnimator.animate(getActivity().findViewById(R.id.fabNewEvent))
                            .rollIn()
                            .duration(500)
                            .andAnimate(getActivity().findViewById(R.id.cardViewCreateEvent_helper))
                            .fadeIn()
                            .duration(500)
                            .start();
                }
                super.onScrolled(recyclerView, dx, dy);
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
