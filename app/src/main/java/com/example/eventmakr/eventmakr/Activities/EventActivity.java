package com.example.eventmakr.eventmakr.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.RecommendVendorDialogFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.ads.AdView;

public class EventActivity extends AppCompatActivity {

    private static final String TAG = EventActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FloatingActionButton mFabRecommendVendor;
    private CoordinatorLayout mLayoutEvent;
    private ImageView mBackGround;
    private String mEventTypeUrl;
    private Context mContext;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.i(TAG, TAG);

        mToolbar = (Toolbar) findViewById(R.id.toolbarEvents);
        mToolbar.setNavigationIcon(R.drawable.close);
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        mLayoutEvent = (CoordinatorLayout) findViewById(R.id.layoutEvent);
        mFabRecommendVendor = (FloatingActionButton) findViewById(R.id.fabRecommendVendor);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this, ConsumerActivity.class));
            }
        });
        mFabRecommendVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecommendDialogFragment();
            }
        });

    }

    void getRecommendDialogFragment(){
        new RecommendVendorDialogFragment().show(getFragmentManager(),"RecommendVendorDialogFragment");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getVendorCategory();
        ViewAnimator.animate(mAdView)
                .slideBottom()
                .duration(300)
                .descelerate()
                .start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    void getVendorCategory(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerVendorCategoryFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
