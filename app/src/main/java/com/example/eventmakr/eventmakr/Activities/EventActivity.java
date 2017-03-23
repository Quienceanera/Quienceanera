package com.example.eventmakr.eventmakr.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class EventActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CoordinatorLayout mLayoutEvent;
    private ImageView mBackGround;
    private String mEventTypeUrl;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mToolbar = (Toolbar) findViewById(R.id.toolbarEvents);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        mLayoutEvent = (CoordinatorLayout) findViewById(R.id.layoutEvent);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this, ConsumerActivity.class));
            }
        });

//        mBackGround = (ImageView) findViewById(R.id.imageViewBackgroundEvents);
//        loadBackGround();
    }
//    void loadBackGround(){
//        Glide.with(this)
//                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcupcakebw.jpg?alt=media&token=6f2ad4a1-9b52-489c-832e-31d2fc241ae4")
//                .centerCrop()
//                .crossFade()
//                .into(mBackGround);
//    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getVendorCategory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("OnStop", "Stopped");
    }

    void getVendorCategory(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerEventActivity, FragmentUtil.getConsumerVendorCategoryFragment())
                .commit();
    }

}
