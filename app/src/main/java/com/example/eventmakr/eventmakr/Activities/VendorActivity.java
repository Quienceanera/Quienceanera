package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.VendorViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorMenuFragment;
import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorOrderHome;
import com.example.eventmakr.eventmakr.R;
import com.github.florent37.viewanimator.ViewAnimator;

public class VendorActivity extends AppCompatActivity{
    private static final String TAG = VendorActivity.class.getSimpleName();
    private ImageView mImageViewBackground, mImageViewLogo, mImageViewMainBg, mImageViewMainBg2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private VendorViewPagerAdapter mViewPagerAdapter;
    public static Boolean mVendorMode = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        Log.i(TAG, TAG);

        mImageViewBackground = (ImageView) findViewById(R.id.imageViewBackgroundVendor);
        mImageViewLogo = (ImageView) findViewById(R.id.imageViewVendorLogo);
        mImageViewMainBg2 = (ImageView) findViewById(R.id.imageViewMainBg2);
        mImageViewMainBg = (ImageView) findViewById(R.id.imageViewMainBg);
        loadBackground();

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerVendor);
        mViewPagerAdapter = new VendorViewPagerAdapter(getFragmentManager(), this);
        mViewPagerAdapter.addFragments(new VendorOrderHome(), "");
//        mViewPagerAdapter.addFragments(new CartFragment(), "");
        mViewPagerAdapter.addFragments(new VendorMenuFragment(), "");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mVendorMode = true;
        ConsumerActivity.mConsumerMode = false;
        Log.i("vendor mode", VendorActivity.mVendorMode.toString() + ConsumerActivity.mConsumerMode.toString());

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

    void loadBackground() {
//        if (FirebaseUtil.getUser().getPhotoUrl()!= null){
//            Glide.with(this)
//                    .load(FirebaseUtil.getUser().getPhotoUrl())
//                    .centerCrop()
//                    .crossFade()
//                    .into(mImageViewBackground);
//        } else {
            Glide.with(this)
                    .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcupcakebw.jpg?alt=media&token=6f2ad4a1-9b52-489c-832e-31d2fc241ae4")
                    .centerCrop()
                    .crossFade()
                    .into(mImageViewBackground);
//        }


        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Flogo.png?alt=media&token=f3fb17ed-6cb5-41ff-a8de-41f283efb14c")
                .crossFade()
                .into(mImageViewLogo);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fconf2.png?alt=media&token=7cd08c24-fe3f-4b39-a847-92f1086e4031")
                .crossFade()
                .centerCrop()
                .into(mImageViewMainBg);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fconf4.png?alt=media&token=00a359cd-4467-4b1c-938c-7c41d5f64f6e")
                .crossFade()
                .centerCrop()
                .into(mImageViewMainBg2);

        ViewAnimator.animate(mImageViewMainBg)
                .translationY(-1200,1600)
                .duration(35000)
                .repeatCount(5)
                .start();

        ViewAnimator.animate(mImageViewMainBg2)
                .translationY(-1500,2000)
                .duration(60000)
                .repeatCount(5)
                .start();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ViewAnimator.animate(mImageViewLogo)
                .slideBottom()
                .descelerate()
                .duration(1000)
                .start();
    }

//
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.cardViewProducts:
//                getVendorProductFragment();
//                break;
//            case R.id.cardViewDocuments:
//                break;
//            case R.id.cardViewInputInfo:
//                getVendorInputFragment();
//                break;
//            default:
//        }
//
//    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VendorActivity.this, MainActivity.class));
            super.onBackPressed();
    }
}
