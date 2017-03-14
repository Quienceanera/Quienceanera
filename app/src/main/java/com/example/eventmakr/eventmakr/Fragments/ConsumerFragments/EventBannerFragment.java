package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.R;

public class EventBannerFragment extends Fragment {
    private TextView mTextViewToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_banner_fragment, container, false);
        mTextViewToolbar = (TextView) view.findViewById(R.id.textViewToolbar);
        if (EventsAdapter.mEventName != null){
            mTextViewToolbar.setText(EventsAdapter.mEventName);
        }
        if (ConsumerInputFragment.mEventName != null){
            mTextViewToolbar.setText(ConsumerInputFragment.mEventName);
        }
        return view;
    }
}
