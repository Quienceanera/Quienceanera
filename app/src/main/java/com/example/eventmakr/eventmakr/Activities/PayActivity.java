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

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ConsumerViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.RecyclerFragment.OrderListRecyclerFragment;
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
        setContentView(R.layout.activity_pay);
        mToolbar = (Toolbar) findViewById(R.id.toolbarPay);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
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

        if (Objects.equals(CartHomeAdapter.mConfirm, "true")){
            Log.i(TAG+"2", CartHomeAdapter.mConfirm);
            mViewPagerAdapter.addFragments(new OrderListRecyclerFragment(), "");
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

    void getCartDetailFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerPayDetails, FragmentUtil.getCartDetailFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PayActivity.this, VendorActivity.class));
        super.onBackPressed();
    }
}
