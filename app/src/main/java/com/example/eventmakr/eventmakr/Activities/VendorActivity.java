package com.example.eventmakr.eventmakr.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView mCardViewProducts, mCardViewDocuments, mCardViewInputInfo;
    private RelativeLayout mLayoutVendorMenu;
    private ImageView mImageViewToolbarIcon, mImageViewBackground;
    private TabLayout mTabLayout;
    private FrameLayout mFrameLayout;

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

//        mCardViewProducts = (CardView) findViewById(R.id.cardViewProducts);
//        mCardViewDocuments = (CardView) findViewById(R.id.cardViewDocuments);
//        mCardViewInputInfo = (CardView) findViewById(R.id.cardViewInputInfo);
        mImageViewToolbarIcon = (ImageView) findViewById(R.id.imageViewIconVendor);
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        mFrameLayout = (FrameLayout) findViewById(R.id.containerTabLayout);
        mImageViewBackground = (ImageView) findViewById(R.id.imageViewBackgroundVendor);

        loadBackground();
        getOrderFragment();
//        mCardViewProducts.setOnClickListener(this);
//        mCardViewDocuments.setOnClickListener(this);
//        mCardViewInputInfo.setOnClickListener(this);
        mImageViewToolbarIcon.setOnClickListener(this);

//        mTabLayout.addTab(mTabLayout.newTab().setText("ORDERS"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("MENU"));
//
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Fragment fragment = null;
//                switch (tab.getPosition()){
//                    case 0:
//                        fragment = new VendorOrderListFragment();
//                        break;
//                    case 1:
//                        fragment = new VendorOrderListFragment();
//                        break;
//                }
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.containerTabLayout, fragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

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

//    void transitionOutMainMenu () {
//        ViewAnimator.animate(mLayoutVendorMenu)
//                .bounceOut()
//                .duration(300)
//                .start();
//        mLayoutVendorMenu.setVisibility(View.GONE);
//    }

    @Override
    protected void onPause() {
        super.onPause();
//        transitionOutMainMenu();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewProducts:
//                transitionOutMainMenu();
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
