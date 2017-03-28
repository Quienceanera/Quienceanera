package com.example.eventmakr.eventmakr.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
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

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getVendorCategory();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    void getVendorCategory(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerVendorCategoryFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
