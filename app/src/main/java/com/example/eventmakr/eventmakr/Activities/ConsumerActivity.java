package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageViewToolbarIcon;
    private FloatingActionButton mFabNewEvent;
    private FrameLayout mLayoutEventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFabNewEvent = (FloatingActionButton) findViewById(R.id.fabNewEvent);
        mImageViewToolbarIcon = (ImageView) findViewById(R.id.imageViewIcon);
        mLayoutEventsList = (FrameLayout) findViewById(R.id.containerEventsList);
        mFabNewEvent.setOnClickListener(this);
        mImageViewToolbarIcon.setOnClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabNewEvent:
                getConsumerInputFragment();
                mFabNewEvent.setVisibility(View.GONE);
                mLayoutEventsList.setVisibility(View.GONE);
                break;
            case R.id.imageViewIcon:
                startActivity(new Intent(this, MainActivity.class));
            default:
        }
    }

    void getConsumerInputFragment() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.consumerActivityLayout, FragmentUtil.getConsumerDropdownFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
//            super.onBackPressed();
        }
    }

}
