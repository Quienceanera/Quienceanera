package com.example.eventmakr.eventmakr.Activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ConsumerViewPagerAdapter;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

import java.util.Objects;

public class PayActivity extends AppCompatActivity {
    private static final String TAG = PayActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ConsumerViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mLayoutConfirm, mLayoutChat, mLayoutCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        setContentView(R.layout.activity_pay);
        getEnterAnimation();


        VendorActivity.mVendorMode = false;
        mToolbar = (Toolbar) findViewById(R.id.toolbarPay);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayActivity.this, ConsumerActivity.class));
            }
        });
        mLayoutChat = (LinearLayout) findViewById(R.id.layoutChatBanner);
        mLayoutCheckOut = (LinearLayout) findViewById(R.id.layoutCheckOutBanner);
        mLayoutConfirm = (LinearLayout) findViewById(R.id.layoutConfirmBanner);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutPay);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerPay);
        mViewPagerAdapter = new ConsumerViewPagerAdapter(getFragmentManager(), this);
        Log.i(TAG+"1", CartHomeAdapter.mConfirm);

//        getCartDetailFragment();
    }

    void getlayout(){
        if (Objects.equals(CartHomeAdapter.mConfirm, "true")){
            Log.i(TAG+"2", CartHomeAdapter.mConfirm);
            mViewPagerAdapter.addFragments(new CartFragment(), "");
            mViewPagerAdapter.addFragments(new ChatFragment(),"");
            mLayoutCheckOut.setVisibility(View.VISIBLE);
        } else{
            mViewPagerAdapter.addFragments(new ContactVendorFragment(), "");
            mLayoutConfirm.setVisibility(View.VISIBLE);
        }

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0 && Objects.equals(CartHomeAdapter.mConfirm, "true")){
                    mLayoutConfirm.setVisibility(View.VISIBLE);
                    mLayoutChat.setVisibility(View.GONE);
                    mLayoutCheckOut.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 0){
                    mLayoutConfirm.setVisibility(View.GONE);
                    mLayoutChat.setVisibility(View.GONE);
                    mLayoutCheckOut.setVisibility(View.VISIBLE);
                }
                if (tab.getPosition() == 1){
                    mLayoutConfirm.setVisibility(View.GONE);
                    mLayoutChat.setVisibility(View.VISIBLE);
                    mLayoutCheckOut.setVisibility(View.GONE);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void getEnterAnimation() {
        Slide slide = new Slide(Gravity.RIGHT);
        slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
        getWindow().setEnterTransition(slide);
        slide.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                getCartDetailFragment();
                getlayout();
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


    void getCartDetailFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerPayDetails, FragmentUtil.getCartDetailFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode && EventsAdapter.mEventKey == null){
            supportFinishAfterTransition();
            startActivity(new Intent(PayActivity.this, VendorActivity.class));
        }else{
            supportFinishAfterTransition();
            startActivity(new Intent(PayActivity.this, ConsumerActivity.class));
        }
    }
}
