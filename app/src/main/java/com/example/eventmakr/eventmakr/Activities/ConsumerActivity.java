package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageViewToolbarIcon, mImageViewBackGround;
    private FloatingActionButton mFabNewEvent;
    private FrameLayout mLayoutEventsList, mLayoutConsumerNav, mLayoutConsumer;
    public static Boolean mVendorMode, mConsumerMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFabNewEvent = (FloatingActionButton) findViewById(R.id.fabNewEvent);
        mImageViewToolbarIcon = (ImageView) findViewById(R.id.imageViewIcon);
        mImageViewBackGround = (ImageView) findViewById(R.id.imageViewBackground);
        loadBackground();
        mLayoutEventsList = (FrameLayout) findViewById(R.id.containerEventsList);
        mLayoutConsumerNav = (FrameLayout) findViewById(R.id.navConsumerActivityLayout);
        mLayoutConsumer = (FrameLayout) findViewById(R.id.consumerActivityLayout);

        mFabNewEvent.setOnClickListener(this);
        mImageViewToolbarIcon.setOnClickListener(this);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getEventsList();

        mVendorMode = false;
        mConsumerMode = true;

//        if (EventsAdapter.mEventName != null){
//            Log.i("is shown", "shown");
//            getEventBannerFragment();
//        }

    }

    void loadBackground() {
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcupcakebw.jpg?alt=media&token=6f2ad4a1-9b52-489c-832e-31d2fc241ae4")
                .centerCrop()
                .crossFade()
                .into(mImageViewBackGround);
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
                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerDropdownFragment())
                .addToBackStack(null)
                .commit();
    }

    void getEventsList() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.consumerActivityLayout, FragmentUtil.getEventsList())
                .addToBackStack(null)
                .commit();
    }

    public void getEventBannerFragment () {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerEventBannerFragment, FragmentUtil.getEventBannerFragment())
                .commit();
        Log.i("Event Banner", "Loaded");
    }

    @Override
    public void onBackPressed() {
        if (FragmentUtil.getEventsList().isVisible()){
            Log.i("eventslist", "Shown");
        }
        if (mLayoutConsumerNav.isShown()){
            ViewAnimator.animate(mLayoutConsumerNav)
                    .slideRight()
                    .duration(200)
                    .andAnimate(mLayoutConsumer)
                    .slideLeft()
                    .duration(200)
                    .start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLayoutConsumer.setVisibility(View.VISIBLE);
                    mLayoutConsumerNav.setVisibility(View.GONE);
                }
            },50);
        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
