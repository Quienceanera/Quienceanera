package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorMenuFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorExtrasActivity extends AppCompatActivity {
    private static final String TAG = VendorExtrasActivity.class.getSimpleName();
    private LinearLayout mProfileBanner, mProductBanner, mDocumentsBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_extras);
        Log.i(TAG, TAG);
        android.widget.Toolbar toolbar = (android.widget.Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorExtrasActivity.this, VendorActivity.class));
            }
        });
        mProfileBanner = (LinearLayout) findViewById(R.id.profileBanner);
        mProductBanner = (LinearLayout) findViewById(R.id.productsBanner);
        mDocumentsBanner = (LinearLayout) findViewById(R.id.documentsBanner);


        if (VendorMenuFragment.mFragmentIntent == 1){
            getVendorInputFragment();
            mProfileBanner.setVisibility(View.VISIBLE);
        }
        if (VendorMenuFragment.mFragmentIntent == 2){
            mDocumentsBanner.setVisibility(View.VISIBLE);
        }
        if (VendorMenuFragment.mFragmentIntent == 3){
            getVendorProductFragment();
            mProductBanner.setVisibility(View.VISIBLE);
        }
    }

    void getVendorInputFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerVendorExtras, FragmentUtil.getVendorInputFragment())
                .commit();
    }
    void getVendorProductFragment(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerVendorExtras, FragmentUtil.getVendorProductFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VendorExtrasActivity.this, VendorActivity.class));
        super.onBackPressed();
    }
}
