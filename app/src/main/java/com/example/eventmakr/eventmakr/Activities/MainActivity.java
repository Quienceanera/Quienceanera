package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.R;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nvanbenschoten.motion.ParallaxImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private CardView mButtonPlanning, mButtonLogIn, mButtonVendor;
    private ParallaxImageView mBackground;
    private CardView mCardView1, mCardView2;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, TAG);

        mCardView1 = (CardView) findViewById(R.id.cardView1);
        mCardView2 = (CardView) findViewById(R.id.cardView2);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mButtonPlanning = (CardView) findViewById(R.id.buttonPlanning);
        mButtonLogIn = (CardView) findViewById(R.id.buttonLogIn);
        mButtonVendor = (CardView) findViewById(R.id.buttonVendor);

        mBackground = (ParallaxImageView) findViewById(R.id.parallaxBackground);
        loadParallaxBg();

        mButtonPlanning.setOnClickListener(this);
        mButtonLogIn.setOnClickListener(this);
        mButtonVendor.setOnClickListener(this);

        if (mFirebaseUser == null) {
            ViewAnimator.animate(mButtonLogIn)
                    .fadeIn()
                    .duration(1300)
                    .start();
            mButtonLogIn.setVisibility(View.VISIBLE);
            Log.i("User", "Not logged in");
        } else {
            mButtonLogIn.setVisibility(View.GONE);
            Log.i("User", "Logged in");
        }
        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());

    }

        void loadParallaxBg(){
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fmobile_bg.jpg?alt=media&token=8930f92d-f5f0-45dd-b9f9-51775faac1e2")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mBackground);
    }

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
                    .andAnimate(mButtonPlanning)
                    .slideBottom()
                    .duration(1200)
                    .start();
            mButtonPlanning.setVisibility(View.VISIBLE);

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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonPlanning:
                Intent consumerIntent = new Intent(MainActivity.this, ConsumerActivity.class);
                startActivity(consumerIntent);
                finish();
                break;
            case R.id.buttonLogIn:
                Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signInIntent);
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
