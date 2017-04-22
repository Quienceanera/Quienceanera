package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ConsumerViewPagerAdapter;
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
    private FrameLayout mLayoutPayDetails;
    private String mVendorUid, mTotalPrice, mVendorName, mVendorLogo, mReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        setContentView(R.layout.activity_pay);

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            Log.i(TAG, "extras are null");

        } else {
            mVendorUid = extras.getString("VendorUid");
            mTotalPrice = extras.getString("TotalPrice");
            mVendorName = extras.getString("VendorName");
            mVendorLogo = extras.getString("VendorLogo");
            mReady = extras.getString("Ready");
            Log.i(TAG, extras.toString());
        }
        getEnterAnimation();
//
//        getWindow().setEnterTransition(new Fade(Fade.IN));
//        getWindow().setExitTransition(new Fade(Fade.OUT));

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
        mLayoutPayDetails = (FrameLayout) findViewById(R.id.containerPayDetails);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutPay);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerPay);
        mViewPagerAdapter = new ConsumerViewPagerAdapter(getFragmentManager(), this);
//        Log.i(TAG+"1", mReady);
        if (Objects.equals(mReady, "true")){
            Log.i(TAG+"2", mReady);
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

        getCartDetailFragment();
    }

    private void getEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.arc_motion_transition);
//        transition.setDuration(800);
//        getWindow().setEnterTransition(transition);
//        transition.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });

    }

    void getCartDetailFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("VendorUid", mVendorUid);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerPayDetails, FragmentUtil.getCartDetailFragment(bundle))
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
//        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode && EventsAdapter.mEventKey == null){
//            supportFinishAfterTransition();
//            startActivity(new Intent(PayActivity.this, VendorActivity.class));
//        }else{
//            supportFinishAfterTransition();
//            startActivity(new Intent(PayActivity.this, ConsumerActivity.class));
//        }
    }
}
