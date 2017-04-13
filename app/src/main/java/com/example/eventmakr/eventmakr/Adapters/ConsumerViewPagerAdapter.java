package com.example.eventmakr.eventmakr.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.eventmakr.eventmakr.R;

import java.util.ArrayList;

public class ConsumerViewPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> mFragments = new ArrayList<>();
//    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<Integer> mIcons = new ArrayList<>();
    private Context mContext;

    public ConsumerViewPagerAdapter(android.app.FragmentManager fm, Activity context) {
        super(fm);
        this.mContext = context;
    }

    public void addFragments (Fragment mFragments, String mTitles){
        this.mFragments.add(mFragments);
//        this.mTitles.add(mTitles);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    private int[] iconResId = {
            R.drawable.menu,
            R.drawable.message_text_outline,
    };

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable icon = ContextCompat.getDrawable(mContext, iconResId[position]);
        icon.setBounds(0,0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        SpannableString mSpannableString = new SpannableString(" ");
        ImageSpan mImageSpan = new ImageSpan(icon, ImageSpan.ALIGN_BOTTOM);
        mSpannableString.setSpan(mImageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mSpannableString;
    }
}
