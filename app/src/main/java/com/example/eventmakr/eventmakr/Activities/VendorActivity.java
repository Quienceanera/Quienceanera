package com.example.eventmakr.eventmakr.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.ViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments.ChatHomeFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorMenuFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.RecyclerFragment.VendorOrderHomeListFragment;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView mCardViewProducts, mCardViewDocuments, mCardViewInputInfo;
    private RelativeLayout mLayoutVendorMenu;
    private ImageView mImageViewToolbarIcon, mImageViewBackground;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private FrameLayout mFrameLayout;
    public static Boolean mVendorMode = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarVendor);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mImageViewToolbarIcon = (ImageView) findViewById(R.id.imageViewIconVendor);
        mImageViewBackground = (ImageView) findViewById(R.id.imageViewBackgroundVendor);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerVendor);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        mViewPagerAdapter.addFragments(new VendorOrderHomeListFragment(), "");
        mViewPagerAdapter.addFragments(new VendorMenuFragment(), "");
        mViewPagerAdapter.addFragments(new ChatHomeFragment(), "");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        loadBackground();
        getOrderFragment();
        ConsumerActivity.mConsumerMode = false;

        mImageViewToolbarIcon.setOnClickListener(this);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    void getOrderFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getVendorOrderHomeListFragment())
                .commit();
    }

    void loadBackground() {
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcupcakebw.jpg?alt=media&token=6f2ad4a1-9b52-489c-832e-31d2fc241ae4")
                .centerCrop()
                .crossFade()
                .into(mImageViewBackground);
    }

    void getVendorInputFragment() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.vendorActivityLayout, FragmentUtil.getVendorInputFragment())
                .addToBackStack(null)
                .commit();
    }

    void getVendorProductFragment() {
        getFragmentManager()
            .beginTransaction()
                .replace(R.id.vendorActivityLayout, FragmentUtil.getVendorProductFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewProducts:
                getVendorProductFragment();
                break;
            case R.id.cardViewDocuments:
//                transitionOutMainMenu();
                break;
            case R.id.cardViewInputInfo:
//                transitionOutMainMenu();
                getVendorInputFragment();
                break;
            default:
        }

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
