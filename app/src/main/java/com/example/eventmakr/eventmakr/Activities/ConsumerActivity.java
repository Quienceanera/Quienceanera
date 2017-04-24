package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.Adapters.ViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.CreateEventDialogFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.database.DatabaseReference;

public class ConsumerActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = ConsumerActivity.class.getSimpleName();
    private ImageView mImageViewBackGround, mImageViewLogo, mImageViewMainBg, mImageViewMainBg2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private FloatingActionButton mFabNewEvent, mFabSearchVendors, mFabSendInvite;
    private CardView mCardViewCreateEvent, mCardViewSearchVendors, mCardViewSendInvite;
    private DatabaseReference mDatabaseNewMessage;
    public static Boolean mVendorMode, mConsumerMode;
    private CoordinatorLayout mLayoutConsumer;
    private final int REQUEST_INVITE = 1;
    private int mFabWidth, mFabPosition;
    private String mEventKey;
    private int mImageWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        postponeEnterTransition();
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                Log.i(TAG, "extras are null");
                mEventKey = EventsAdapter.mEventKey;

            } else {
                mEventKey = extras.getString(mEventKey);
                mImageWidth = extras.getInt("ImageWidth");
//                Log.i(TAG, mEventKey);
            }

        Fade explode = new Fade(Fade.OUT);
        explode.setDuration(300);
        getWindow().setExitTransition(explode);


        mFabNewEvent = (FloatingActionButton) findViewById(R.id.fabNewEvent);
        mFabSearchVendors = (FloatingActionButton) findViewById(R.id.fabSearchVendor);
        mFabSendInvite = (FloatingActionButton) findViewById(R.id.fabSendInvite);
        mCardViewCreateEvent = (CardView) findViewById(R.id.cardViewCreateEvent_helper);
        mCardViewSearchVendors = (CardView) findViewById(R.id.cardViewSearchVendor_helper);
        mCardViewSendInvite = (CardView) findViewById(R.id.cardViewSendInvite_helper);
        mLayoutConsumer = (CoordinatorLayout) findViewById(R.id.layoutConsumer);

        mFabNewEvent.setOnClickListener(this);
        mCardViewCreateEvent.setOnClickListener(this);
        if (mEventKey != null){
            mFabSearchVendors.setOnClickListener(this);
            mCardViewSearchVendors.setOnClickListener(this);
            mFabSendInvite.setOnClickListener(this);
            mCardViewSendInvite.setOnClickListener(this);
        }

        mImageViewBackGround = (ImageView) findViewById(R.id.imageViewBackground);
        mImageViewLogo = (ImageView) findViewById(R.id.imageViewConsumerLogo);
        mImageViewMainBg = (ImageView) findViewById(R.id.imageViewMainBg);
        mImageViewMainBg2 = (ImageView) findViewById(R.id.imageViewMainBg2);
        loadBackground();

        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutConsumer);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerConsumer);

        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), this);

        mViewPagerAdapter.addFragments(FragmentUtil.getEventsFragment(), "");

        mFabSearchVendors.setVisibility(View.GONE);
        mCardViewSearchVendors.setVisibility(View.GONE);

        mFabSendInvite.setVisibility(View.GONE);
        mCardViewSendInvite.setVisibility(View.GONE);
        if (mEventKey != null){
            mViewPagerAdapter.addFragments(FragmentUtil.getCartFragment(), "");
            mViewPagerAdapter.addFragments(FragmentUtil.getUserFragment(), "");
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
                            .rollIn()
                            .duration(300)
                            .andAnimate(mCardViewCreateEvent)
                            .zoomIn()
                            .descelerate()
                            .duration(300)
                            .start();
                }
                if (tab.getPosition() == 1){
                    mFabSearchVendors.setVisibility(View.VISIBLE);
                    mCardViewSearchVendors.setVisibility(View.VISIBLE);
                    ViewAnimator.animate(mFabSearchVendors)
                            .rollIn()
                            .duration(300)
                            .andAnimate(mCardViewSearchVendors)
                            .zoomIn()
                            .descelerate()
                            .duration(300)
                            .start();
                }
                if (tab.getPosition() == 2){
                    mFabSendInvite.setVisibility(View.VISIBLE);
                    mCardViewSendInvite.setVisibility(View.VISIBLE);
                    ViewAnimator.animate(mFabSendInvite)
                            .rollIn()
                            .duration(300)
                            .andAnimate(mCardViewSendInvite)
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
                            .rollOut()
                            .duration(300)
                            .andAnimate(mCardViewCreateEvent)
                            .zoomOut()
                            .descelerate()
                            .duration(300)
                            .start();
                }
                if (tab.getPosition() == 1){
                    ViewAnimator.animate(mFabSearchVendors)
                            .rollOut()
                            .duration(300)
                            .andAnimate(mCardViewSearchVendors)
                            .zoomOut()
                            .descelerate()
                            .duration(300)
                            .start();
                }
                if (tab.getPosition() == 2){
                    ViewAnimator.animate(mFabSendInvite)
                            .rollOut()
                            .duration(300)
                            .andAnimate(mCardViewSendInvite)
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
        Transition transition = getWindow().getEnterTransition();
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                        if (mTabLayout.getTabCount() > 1){
                            TabLayout.Tab tab = mTabLayout.getTabAt(1);
                            tab.select();
                        }
                        transition.removeListener(this);
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

    private void scheduleStartPostponedTransition(final View sharedElement){
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                }
        );
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
        scheduleStartPostponedTransition(mFabNewEvent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabNewEvent:
            case R.id.cardViewCreateEvent_helper:
                getCreateEventDialog();
                break;
            case R.id.fabSearchVendor:
            case R.id.cardViewSearchVendor_helper:
                getEventsActivity();
                break;
            case R.id.fabSendInvite:
            case R.id.cardViewSendInvite_helper:
                onInviteClicked();
                break;
            default:
        }
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
//                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
//                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
//                .setCallToActionText(getString(R.string.invitation_cta))
                .setEmailHtmlContent("&lthtml&gt&ltbody&gt"
                        + "&lta href=\"%%APPINVITE_LINK_PLACEHOLDER%%\"&gtXYZ Free Trial&lt/a&gt"
                        + "\n" +
                        "Your invited!\n" +
                        "No Images? Click here\n" +
                        "\n" +
                        "\n" +
                        " \n" +
                        "Quienceaneras\n" +
                        "Unsubscribe\n")
                .setEmailSubject("Quienceaneras")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }

    void getCreateEventDialog(){
        new CreateEventDialogFragment().show(getFragmentManager(), "CreateEventDialogFragment");
    }

    void getEventsActivity(){
        mFabWidth = mFabSearchVendors.getWidth();

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("mFabWidth", mFabSearchVendors.getWidth());
        ActivityCompat.startActivity(this, intent,
                ActivityOptionsCompat.
                        makeSceneTransitionAnimation(this, mFabSearchVendors, "reveal")
                        .toBundle());
//        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
}
