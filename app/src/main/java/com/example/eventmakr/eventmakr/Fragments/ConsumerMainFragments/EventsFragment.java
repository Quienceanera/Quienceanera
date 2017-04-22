package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.CreateEventDialogFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class EventsFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = EventsFragment.class.getSimpleName();
    private FloatingActionButton mFabNewEvent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.events_fragment, container, false);
        getActivity().getWindow().getAttributes().windowAnimations = R.style.EventsFragment;
//        mFabNewEvent = (FloatingActionButton) view.findViewById(R.id.fabNewEvent);
//        mFabNewEvent.setOnClickListener(this);

        getEventsList();
        return view;
    }

    void getEventsList() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerEvents, FragmentUtil.getEventsList())
                .commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabNewEvent:
                getCreateEventDialog();
                break;
        }
    }

    void getCreateEventDialog(){
        new CreateEventDialogFragment().show(getFragmentManager(), "CreateEventDialogFragment");
    }
}
