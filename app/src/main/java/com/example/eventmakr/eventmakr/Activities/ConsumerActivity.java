package com.example.eventmakr.eventmakr.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.ViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageViewBackGround, mImageViewLogo, mImageViewMainBg, mImageViewMainBg2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    public static Boolean mVendorMode, mConsumerMode, mContactVendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);

        mImageViewBackGround = (ImageView) findViewById(R.id.imageViewBackground);
        mImageViewLogo = (ImageView) findViewById(R.id.imageViewConsumerLogo);
        mImageViewMainBg = (ImageView) findViewById(R.id.imageViewMainBg);
        mImageViewMainBg2 = (ImageView) findViewById(R.id.imageViewMainBg2);
        loadBackground();

        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutConsumer);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerConsumer);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);
        mViewPagerAdapter.addFragments(FragmentUtil.getEventsFragment(), "");
        if (EventsAdapter.mEventKey != null){
            mViewPagerAdapter.addFragments(FragmentUtil.getChatHomeFragment(), "");
            mViewPagerAdapter.addFragments(FragmentUtil.getCartFragment(), "");
            mViewPagerAdapter.addFragments(FragmentUtil.getUserFragment(), "");
            if (ConsumerActivity.mContactVendor){
                mViewPagerAdapter.getItem(1);
            }
        }

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mContactVendor = false;
        mVendorMode = false;
        mConsumerMode = true;

//        Snackbar.make(findViewById(R.id.layoutConsumer), "Choose or create an event to get started!", Snackbar.LENGTH_SHORT).show();
    }

    void loadBackground() {
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcupcakebw.jpg?alt=media&token=6f2ad4a1-9b52-489c-832e-31d2fc241ae4")
                .centerCrop()
                .crossFade()
                .into(mImageViewBackGround);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            default:
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
