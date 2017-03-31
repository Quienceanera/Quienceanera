package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.ViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.CreateEventDialogFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageViewBackGround, mImageViewLogo, mImageViewMainBg, mImageViewMainBg2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private FloatingActionButton mFabNewEvent;
    private CardView mCardViewCreateEvent;
    public static Boolean mVendorMode, mConsumerMode;
    private CoordinatorLayout mLayoutConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);

        mFabNewEvent = (FloatingActionButton) findViewById(R.id.fabNewEvent);
        mCardViewCreateEvent = (CardView) findViewById(R.id.cardViewCreateEvent_helper);
        mLayoutConsumer = (CoordinatorLayout) findViewById(R.id.layoutConsumer);
        mFabNewEvent.setOnClickListener(this);

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
            mViewPagerAdapter.addFragments(FragmentUtil.getCartFragment(), "");
            mViewPagerAdapter.addFragments(FragmentUtil.getUserFragment(), "");
//            mTabLayout.getTabAt(1).select();
        }

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mVendorMode = false;
        mConsumerMode = true;

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    ViewAnimator.animate(mFabNewEvent)
                            .bounceIn()
                            .duration(500)
                            .andAnimate(mCardViewCreateEvent)
                            .zoomIn()
                            .descelerate()
                            .duration(300)
                            .start();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    ViewAnimator.animate(mFabNewEvent)
                            .bounceOut()
                            .duration(300)
                            .andAnimate(mCardViewCreateEvent)
                            .zoomOut()
                            .descelerate()
                            .duration(300)
                            .start();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        makeUserProfile();
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
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fconf4.png?alt=media&token=00a359cd-4467-4b1c-938c-7c41d5f64f6e")
                .crossFade()
                .centerCrop()
                .into(mImageViewMainBg2);

        ViewAnimator.animate(mImageViewMainBg2)
                .translationY(-1600,2200)
                .duration(70000)
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
            case R.id.fabNewEvent:
                getCreateEventDialog();
            default:
        }
    }

//    void makeUserProfile(){
//        User user = new User(
//                FirebaseUtil.getUserName(),
//                FirebaseUtil.getUid(),
//                FirebaseUtil.getUser().getEmail(),
//                null,
//                false,
//                false
//        );
//        FirebaseUtil.getConsumerProfileRef().setValue(user);
//    }

    void getCreateEventDialog(){
        new CreateEventDialogFragment().show(getFragmentManager(), "CreateEventDialogFragment");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
}
