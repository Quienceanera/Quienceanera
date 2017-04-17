package com.example.eventmakr.eventmakr.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.RecommendVendorDialogFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.AnimationUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.example.eventmakr.eventmakr.Utils.OnRevealAnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.ads.AdView;

public class EventActivity extends AppCompatActivity {

    private static final String TAG = EventActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FloatingActionButton mFabRecommendVendor, mFabRevealTarget;
    private CoordinatorLayout mLayoutEvent;
    private FrameLayout mLayoutVendors;
    private ImageView mBackGround;
    private String mEventTypeUrl;
    private Context mContext;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.i(TAG, TAG);

        setupEnterAnimation();

        mToolbar = (Toolbar) findViewById(R.id.toolbarEvents);
        mToolbar.setNavigationIcon(R.drawable.close);
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        mLayoutEvent = (CoordinatorLayout) findViewById(R.id.layoutEvent);
        mLayoutVendors = (FrameLayout) findViewById(R.id.containerEventActivity);

        mFabRevealTarget = (FloatingActionButton) findViewById(R.id.fabRevealTarget);
        mFabRecommendVendor = (FloatingActionButton) findViewById(R.id.fabRecommendVendor);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!FragmentUtil.getConsumerVendorCategoryFragment().isVisible()){
                    AnimationUtil.animateRevealHide(getApplicationContext(), mLayoutEvent, R.color.colorAccentLight, ConsumerActivity.mFabWidth, new OnRevealAnimationListener() {
                        @Override
                        public void onRevealHide() {
                            startActivity(new Intent(getApplicationContext(), ConsumerActivity.class));
                        }

                        @Override
                        public void onRevealShow() {

                        }
                    });
                    onBackPressed();
                }
            }
        });
        mFabRecommendVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecommendDialogFragment();
            }
        });

    }

    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.arc_motion_transition);
        transition.setDuration(300);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealShow(mLayoutEvent);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void animateRevealShow(final CoordinatorLayout mLayoutEvent) {
        int cx = (mLayoutEvent.getLeft() + mLayoutEvent.getRight())/2;
        int cy = (mLayoutEvent.getTop() + mLayoutEvent.getBottom()/2);
        AnimationUtil.animateRevealShow(this, mLayoutEvent, ConsumerActivity.mFabWidth / 2, R.color.pink, cx, cy,
                new OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        Animation mFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                        mFadeIn.setDuration(200);
                        mLayoutVendors.startAnimation(mFadeIn);
                        mFabRevealTarget.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onRevealShow() {
                        initViews();
                    }
                });
    }

    private void initViews() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                Animation mFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                animation.setDuration(300);
                mFadeOut.setDuration(300);
                mLayoutVendors.startAnimation(mFadeOut);
                mFabRevealTarget.setVisibility(View.INVISIBLE);
                mLayoutVendors.setVisibility(View.VISIBLE);
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
                .setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (FragmentUtil.getConsumerVendorCategoryFragment().isVisible()){
            AnimationUtil.animateRevealHide(this, mLayoutEvent, R.color.colorAccentLight, ConsumerActivity.mFabWidth, new OnRevealAnimationListener() {
                @Override
                public void onRevealHide() {

                }

                @Override
                public void onRevealShow() {

                }
            });
        }

    }
}
