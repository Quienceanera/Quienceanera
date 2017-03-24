package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ChatActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mToolbar = (Toolbar) findViewById(R.id.toolbarChat);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode){
                    startActivity(new Intent(ChatActivity.this, VendorActivity.class));
                }else {
                    startActivity((new Intent(ChatActivity.this, ConsumerActivity.class)));
                }
            }
        });
        getChatFragment();
    }
    void getChatFragment(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerChat, FragmentUtil.getChatFragment())
                .commit();
    }
}
