package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Adapters.VendorOrderViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorOrdersFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorOrderActivity extends AppCompatActivity {
    private static final String TAG = VendorOrderActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private VendorOrderViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mLayoutOrdersBanner, mLayoutChatBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order);
        Log.i(TAG, TAG);
        mToolbar = (Toolbar) findViewById(R.id.toolbarVendorOrder);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorOrderActivity.this, VendorActivity.class));
            }
        });

        mLayoutChatBanner = (LinearLayout) findViewById(R.id.layoutChatBanner);
        mLayoutOrdersBanner = (LinearLayout) findViewById(R.id.layoutOrderBanner);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutVendorOrder);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerVendorOrder);
        mViewPagerAdapter = new VendorOrderViewPagerAdapter(getFragmentManager(), this);
        mViewPagerAdapter.addFragments(new VendorOrdersFragment(), "");
        mViewPagerAdapter.addFragments(new ChatFragment(), "");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    mLayoutOrdersBanner.setVisibility(View.VISIBLE);
                    mLayoutChatBanner.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 1){
                    mLayoutChatBanner.setVisibility(View.VISIBLE);
                    mLayoutOrdersBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getOrderDetailFragment();
    }
    void getOrderDetailFragment(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerVendorOrderDetails, FragmentUtil.getOrderDetailFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VendorOrderActivity.this, VendorActivity.class));
        super.onBackPressed();
    }

}
