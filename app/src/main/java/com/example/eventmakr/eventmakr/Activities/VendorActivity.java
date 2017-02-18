package com.example.eventmakr.eventmakr.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class VendorActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView mCardViewProducts, mCardViewDocuments, mCardViewInputInfo;
    private RelativeLayout mLayoutVendorMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        mCardViewProducts = (CardView) findViewById(R.id.cardViewProducts);
        mCardViewDocuments = (CardView) findViewById(R.id.cardViewDocuments);
        mCardViewInputInfo = (CardView) findViewById(R.id.cardViewInputInfo);
        mLayoutVendorMenu = (RelativeLayout) findViewById(R.id.layoutVendorMain);

        mCardViewProducts.setOnClickListener(this);
        mCardViewDocuments.setOnClickListener(this);
        mCardViewInputInfo.setOnClickListener(this);

    }

    void getVendorInputFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.vendorActivityLayout, FragmentUtil.getVendorInputFragment()).commit();
    }

    void getVendorProductFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.vendorActivityLayout, FragmentUtil.getVendorProductFragment()).commit();
    }

    void transitionOutMainMenu () {
        ViewAnimator.animate(mLayoutVendorMenu)
                .bounceOut()
                .duration(300)
                .start();
        mLayoutVendorMenu.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        transitionOutMainMenu();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewProducts:
                transitionOutMainMenu();
                getVendorProductFragment();
                Toast.makeText(this, "products", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewDocuments:
                transitionOutMainMenu();
                Toast.makeText(this, "documents", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewInputInfo:
                transitionOutMainMenu();
                getVendorInputFragment();
                Toast.makeText(this, "info", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

    }
}
