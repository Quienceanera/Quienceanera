package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.eventmakr.eventmakr.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView mImageViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mImageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);

//        ViewAnimator.animate(mImageViewSplash)
//                .bounceIn()
//                .fadeOut()
//                .duration(300)
//                .start();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
