package com.example.eventmakr.eventmakr.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerInputFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerActivity extends AppCompatActivity implements ConsumerInputFragment.OnFragmentInteractionListener{

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        mFragmentManager = getSupportFragmentManager();


        getConsumerInputFragment();

    }
    void getConsumerInputFragment() {
//        Toast.makeText(this, getApplicationContext().toString(), Toast.LENGTH_SHORT).show();
        mFragmentManager.beginTransaction().add(R.id.consumerActivityLayout, FragmentUtil.getConsumerDropdownFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
