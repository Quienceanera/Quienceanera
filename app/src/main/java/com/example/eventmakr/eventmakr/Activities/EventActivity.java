package com.example.eventmakr.eventmakr.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class EventActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView mBackGround;
    private String mEventTypeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mToolbar = (Toolbar) findViewById(R.id.toolbarEvent);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);

        mBackGround = (ImageView) findViewById(R.id.imageViewBackgroundEvents);


        getVendorCategory();
        getBackground();
    }
    void getVendorCategory(){
        getFragmentManager()
                .beginTransaction()
                .add(R.id.containerEventActivity, FragmentUtil.getConsumerVendorCategoryFragment())
                .addToBackStack(null)
                .commit();
    }

    void getBackground(){
        String type = EventsAdapter.mEventType;
        switch (type) {
            case "Wedding":
                mEventTypeUrl = "https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fwedding.jpg?alt=media&token=c155bd76-6e1a-4fb6-b775-8ca72b893126";
                break;
            case "Quienceanera":
                mEventTypeUrl = "https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fmobile_bg.jpg?alt=media&token=8930f92d-f5f0-45dd-b9f9-51775faac1e2";
                break;
            case "Birthday":
                mEventTypeUrl = "https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbirthday.jpg?alt=media&token=9ae47551-bcbe-44cb-aaab-6fe2699311dd";
                break;
            case "Baby Shower":
                mEventTypeUrl = "https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbaby.jpg?alt=media&token=a804bad3-cb61-4690-91d4-879c031102a5";
                break;
            case "Graduation":
                mEventTypeUrl = "https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fgraduation.jpg?alt=media&token=d86c1680-0bfa-4349-b872-009a6097a530";
                break;
            default:
        }
        Glide.with(this)
                .load(mEventTypeUrl)
                .centerCrop()
                .crossFade()
                .into(mBackGround);
    }
}
