package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.eventmakr.eventmakr.Fragments.VendorFragments.VendorMenuFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorExtrasActivity extends AppCompatActivity {
    private static final String TAG = VendorExtrasActivity.class.getSimpleName();

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


        if (VendorMenuFragment.mFragmentIntent == 1){
            getVendorInputFragment();
        }
        if (VendorMenuFragment.mFragmentIntent == 2){

        }
        if (VendorMenuFragment.mFragmentIntent == 3){
            getVendorProductFragment();
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
