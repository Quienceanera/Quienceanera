package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerVendorCategoryFragment extends android.app.Fragment implements View.OnClickListener {

    private static final String TAG = "ConsumerVendorCategoryFragment";
    public static String mCategory;
    private TextView mTextViewVendorCount;
    private RelativeLayout mLayout1, mLayout2, mLayout3, mLayoutVendors;
    private CardView mCardViewCaterers, mCardViewMixologists, mCardViewFlorists, mCardViewPartySupplies, mCardViewDjs, mCardViewBakeries;
    private ImageView mImageViewCaterers, mImageViewMixoligists, mImageViewFlorists, mImageViewPartySupplies, mImageViewDjs, mImageViewBakeries;

    public ConsumerVendorCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumer_vendor, container, false);
        //Setting Buttons
        mCardViewCaterers = (CardView) view.findViewById(R.id.cardViewCaterers);
        mCardViewMixologists = (CardView) view.findViewById(R.id.cardViewMixologists);
        mCardViewFlorists = (CardView) view.findViewById(R.id.cardViewFlorists);
        mCardViewPartySupplies = (CardView) view.findViewById(R.id.cardViewPartySupplies);
        mCardViewDjs = (CardView) view.findViewById(R.id.cardViewDjs);
        mCardViewBakeries = (CardView) view.findViewById(R.id.cardViewBakeries);

        mImageViewCaterers = (ImageView) view.findViewById(R.id.imageViewCaterers);
        mImageViewMixoligists = (ImageView) view.findViewById(R.id.imageViewMixologists);
        mImageViewFlorists = (ImageView) view.findViewById(R.id.imageViewFlorists);
        mImageViewPartySupplies = (ImageView) view.findViewById(R.id.imageViewPartySupplies);
        mImageViewDjs = (ImageView) view.findViewById(R.id.imageViewDjs);
        mImageViewBakeries = (ImageView) view.findViewById(R.id.imageViewBakeries);
        mLayout1 = (RelativeLayout) view.findViewById(R.id.layoutVendorMenu1);
        mLayout2 = (RelativeLayout) view.findViewById(R.id.layoutVendor2);
        mLayout3 = (RelativeLayout) view.findViewById(R.id.layoutVendor3);
        mLayoutVendors = (RelativeLayout) view.findViewById(R.id.layoutVendors);

        loadImages();

        mTextViewVendorCount = (TextView) view.findViewById(R.id.textViewFoundVendors1);

        mCardViewCaterers.setOnClickListener(this);
        mCardViewMixologists.setOnClickListener(this);
        mCardViewFlorists.setOnClickListener(this);
        mCardViewPartySupplies.setOnClickListener(this);
        mCardViewDjs.setOnClickListener(this);
        mCardViewBakeries.setOnClickListener(this);
        return view;
    }
    void loadImages () {
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcatering.jpg?alt=media&token=48e8ee01-c0f8-44aa-9ea0-fc958cbfee8b")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewCaterers);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fmixoligists.jpg?alt=media&token=afa9b638-f30e-49c2-bdd1-8b01f08626df")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewMixoligists);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fflorists.jpg?alt=media&token=1d03528f-4669-419c-b7e1-b316e4554177")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewFlorists);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fpartysupplies.jpg?alt=media&token=76d77511-8694-4ffe-8fa5-e43393aba715")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewPartySupplies);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fdj.jpg?alt=media&token=0b9945d7-2dd9-4c34-bc46-4b9cc2a98540")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewDjs);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbakeries.jpg?alt=media&token=88e2324b-8f50-4c8f-bbab-d056b26d7f4c")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewBakeries);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewCaterers:
                mCategory = "Caterer";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewMixologists:
                mCategory = "Mixologist";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewFlorists:
                mCategory = "Florist";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewPartySupplies:
                mCategory = "PartySupplie";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewDjs:
                mCategory = "DJ";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewBakeries:
                mCategory = "Bakerie";
                getConsumerBudgetFragment();
                break;
            default:
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onresume", "onresume");
        if (mLayoutVendors.isShown()){
            ViewAnimator.animate(mLayout1)
                    .fadeIn()
                    .slideTop()
                    .duration(500)
                    .andAnimate(mLayout2)
                    .slideRight()
                    .fadeIn()
                    .duration(500)
                    .andAnimate(mLayout3)
                    .slideBottom()
                    .fadeIn()
                    .duration(500)
                    .descelerate()
                    .start();
            Log.i("onresume", "onresume2");

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onstart", "onstart");

    }

    @Override
    public void onStop() {
        super.onStop();
        ViewAnimator.animate(mLayout1)
                .slideLeft()
                .duration(100)
                .andAnimate(mLayout2)
                .slideLeft()
                .duration(200)
                .andAnimate(mLayout3)
                .slideLeft()
                .duration(300)
                .start();
    }

    public void getConsumerBudgetFragment() {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerBudgetFragment())
                .addToBackStack(null)
                .commit();
    }

}
