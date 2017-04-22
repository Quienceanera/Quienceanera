package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.Objects.User;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nvanbenschoten.motion.ParallaxImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private CardView mButtonPlanning, mButtonLogIn, mButtonVendor;
    private ParallaxImageView mBackground;
    private CardView mCardView1, mCardView2, mButtonSignOut;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, TAG);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mCardView1 = (CardView) findViewById(R.id.cardView1);
        mCardView2 = (CardView) findViewById(R.id.cardView2);

        mButtonSignOut = (CardView) findViewById(R.id.signOutButton);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mButtonPlanning = (CardView) findViewById(R.id.buttonPlanning);
        mButtonLogIn = (CardView) findViewById(R.id.buttonLogIn);
        mButtonVendor = (CardView) findViewById(R.id.buttonVendor);

        mBackground = (ParallaxImageView) findViewById(R.id.parallaxBackground);
        loadParallaxBg();

        mButtonPlanning.setOnClickListener(this);
        mButtonLogIn.setOnClickListener(this);
        mButtonVendor.setOnClickListener(this);
        mButtonSignOut.setOnClickListener(this);

        if (mFirebaseUser == null) {
            startSignInActivity();
            mButtonSignOut.setVisibility(View.GONE);

            Log.i("User", "Not logged in");
        } else {
//            mButtonLogIn.setVisibility(View.GONE);
            Log.i("User", "Logged in");
            addUserToDatabase(mFirebaseUser);
        }
        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());

    }

        void loadParallaxBg(){
        Glide.with(this)
                .load(getString(R.string.main_activity_background))
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mBackground);
    }

    private void addUserToDatabase(FirebaseUser firebaseUser){
        User user = new User(
                firebaseUser.getDisplayName(),
                firebaseUser.getUid(),
                firebaseUser.getEmail(),
                firebaseUser.getPhotoUrl() == null ? "" : firebaseUser.getPhotoUrl().toString(),
                "false",
                "false"
        );
        FirebaseUtil.getBaseRef().child("users").child(user.getUid()).setValue(user);

        String instanceId = FirebaseInstanceId.getInstance().getToken();
        if (instanceId != null){
            FirebaseUtil.getBaseRef().child("users").child(firebaseUser.getUid()).child("instanceId").setValue(instanceId);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mBackground.registerSensorManager();

            ViewAnimator.animate(mCardView1)
                    .translationX(0, -800)
                    .duration(1000)
                    .andAnimate(mCardView2)
                    .translationX(0, 800)
                    .duration(1000)
                    .andAnimate(mButtonPlanning, mButtonVendor)
                    .slideBottom()
                    .duration(1200)
                    .start();
            mButtonPlanning.setVisibility(View.VISIBLE);
            mButtonVendor.setVisibility(View.VISIBLE);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                }
            }, 1200);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBackground.unregisterSensorManager();
    }

    void startSignInActivity(){
        Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(signInIntent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonPlanning:
                Intent consumerIntent = new Intent(MainActivity.this, ConsumerActivity.class);

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, mButtonPlanning, "mainToConsumer");
                startActivity(consumerIntent, optionsCompat.toBundle());
                break;
            case R.id.signOutButton:
                mFirebaseAuth.signOut();
                startSignInActivity();
                finish();
                break;
            case R.id.buttonVendor:
                Intent vendorIntent = new Intent(MainActivity.this, VendorActivity.class);
                startActivity(vendorIntent);
                finish();
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
