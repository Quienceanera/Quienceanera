package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class VendorOrderActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_order);
        mToolbar = (Toolbar) findViewById(R.id.toolbarVendorOrder);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorOrderActivity.this, VendorActivity.class));
            }
        });
        getOrderDetailFragment();
        getCartDetailList();
    }
    void getOrderDetailFragment(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerVendorOrderDetails, FragmentUtil.getOrderDetailFragment())
                .commit();
    }

    void getCartDetailList() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerVendorOrder, FragmentUtil.getOrderListItemFragment())
                .commit();
    }
}
