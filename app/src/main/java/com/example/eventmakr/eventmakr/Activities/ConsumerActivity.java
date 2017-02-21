package com.example.eventmakr.eventmakr.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ConsumerInputFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerActivity extends AppCompatActivity implements ConsumerInputFragment.OnFragmentInteractionListener{

    private ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);

        mBackButton = (ImageButton) findViewById(R.id.toolbarBackButton);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getConsumerInputFragment();

    }
    void getConsumerInputFragment() {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.consumerActivityLayout, FragmentUtil.getConsumerDropdownFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
