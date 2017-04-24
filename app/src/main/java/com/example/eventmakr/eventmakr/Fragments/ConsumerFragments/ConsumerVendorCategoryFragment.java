package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerVendorCategoryFragment extends android.app.Fragment implements View.OnClickListener {

    private static final String TAG = "ConsumerVendorCategoryFragment";
    public static String mCategory;
    private TextView mTextViewVendorCount;
    private LinearLayout mLayout1, mLayout2, mLayout3;
    private RelativeLayout mLayoutVendors;
    private CardView mCardViewDjs, mCardViewLive, mCardViewInstrument, mCardViewVocal;
    private CardView mCardViewCaterers, mCardViewBakeries, mCardViewFoodTrucks, mCardViewOrganic;
    private CardView mCardViewMixologists, mCardViewKegs, mCardViewWholesale, mCardViewWine;

    private ImageView mImageViewDjs, mImageViewLive, mImageViewInstrument, mImageViewVocal;
    private ImageView mImageViewCaterers, mImageViewBakeries, mImageViewFoodTrucks, mImageViewOrganic;
    private ImageView mImageViewMixologists, mImageViewKegs, mImageViewWholesale, mImageViewWine;


    public ConsumerVendorCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumer_vendor, container, false);
        //Setting Buttons
        mCardViewCaterers = (CardView) view.findViewById(R.id.cardViewCaterers);
        mCardViewBakeries = (CardView) view.findViewById(R.id.cardViewBakeries);
        mCardViewFoodTrucks = (CardView) view.findViewById(R.id.cardViewFoodTrucks);
        mCardViewOrganic = (CardView) view.findViewById(R.id.cardViewOrganic);

        mImageViewCaterers = (ImageView) view.findViewById(R.id.imageViewCaterers);
        mImageViewBakeries = (ImageView) view.findViewById(R.id.imageViewBakeries);
        mImageViewFoodTrucks = (ImageView) view.findViewById(R.id.imageViewFoodTrucks);
        mImageViewOrganic = (ImageView) view.findViewById(R.id.imageViewOrganic);

        mCardViewDjs = (CardView) view.findViewById(R.id.cardViewDjs);
        mCardViewLive = (CardView) view.findViewById(R.id.cardViewLive);
        mCardViewInstrument = (CardView) view.findViewById(R.id.cardViewInstrument);
        mCardViewVocal = (CardView) view.findViewById(R.id.cardViewVocal);

        mImageViewDjs = (ImageView) view.findViewById(R.id.imageViewDjs);
        mImageViewLive = (ImageView) view.findViewById(R.id.imageViewlive);
        mImageViewInstrument = (ImageView) view.findViewById(R.id.imageViewInstrument);
        mImageViewVocal = (ImageView) view.findViewById(R.id.imageViewVocal);

        mCardViewMixologists = (CardView) view.findViewById(R.id.cardViewMixologists);
        mCardViewKegs = (CardView) view.findViewById(R.id.cardViewKegs);
        mCardViewWholesale = (CardView) view.findViewById(R.id.cardViewWholesale);
        mCardViewWine = (CardView) view.findViewById(R.id.cardViewWine);

        mImageViewMixologists = (ImageView) view.findViewById(R.id.imageViewMixologists);
        mImageViewKegs = (ImageView) view.findViewById(R.id.imageViewKegs);
        mImageViewWholesale = (ImageView) view.findViewById(R.id.imageViewWholesale);
        mImageViewWine = (ImageView) view.findViewById(R.id.imageViewWine);

        mLayout1 = (LinearLayout) view.findViewById(R.id.layoutVendorMenu1);
        mLayout2 = (LinearLayout) view.findViewById(R.id.layoutVendor2);
        mLayout3 = (LinearLayout) view.findViewById(R.id.layoutVendor3);
        mLayoutVendors = (RelativeLayout) view.findViewById(R.id.layoutVendors);

        loadImages();

        mCardViewCaterers.setOnClickListener(this);
        mCardViewBakeries.setOnClickListener(this);
        mCardViewFoodTrucks.setOnClickListener(this);
        mCardViewOrganic.setOnClickListener(this);

        mCardViewDjs.setOnClickListener(this);
        mCardViewLive.setOnClickListener(this);
        mCardViewInstrument.setOnClickListener(this);
        mCardViewVocal.setOnClickListener(this);

        mCardViewMixologists.setOnClickListener(this);
        mCardViewKegs.setOnClickListener(this);
        mCardViewWholesale.setOnClickListener(this);
        mCardViewWine.setOnClickListener(this);

//        ViewGroup rootContainer = (ViewGroup) view.findViewById(R.id.layoutVendors);
//        Scene scene = Scene.getSceneForLayout(mLayoutVendors, R.layout.layout_vendor_menu_1, getActivity());
//        Scene scene2 = Scene.getSceneForLayout(mLayoutVendors, R.layout.layout_vendor_menu_2, getActivity());
//        Scene scene3 = Scene.getSceneForLayout(mLayoutVendors, R.layout.layout_vendor_menu_3, getActivity());
//
//        scene.enter();
//        scene2.enter();
//        scene3.enter();

        return view;
    }

    void loadImages () {
        Glide.with(this)
                .load(getString(R.string.caterers_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewCaterers);


        Glide.with(this)
                .load(getString(R.string.bakeries_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewBakeries);

        Glide.with(this)
                .load(getString(R.string.foodtrucks_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewFoodTrucks);

        Glide.with(this)
                .load(getString(R.string.organic_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewOrganic);

        Glide.with(this)
                .load(getString(R.string.djs_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewDjs);

        Glide.with(this)
                .load(getString(R.string.live_music_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewLive);

        Glide.with(this)
                .load(getString(R.string.instrument_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewInstrument);
        Glide.with(this)
                .load(getString(R.string.vocal_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewVocal);
        Glide.with(this)
                .load(getString(R.string.mixologists_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewMixologists);

        Glide.with(this)
                .load(getString(R.string.kegs_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewKegs);

        Glide.with(this)
                .load(getString(R.string.wholesale_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewWholesale);

        Glide.with(this)
                .load(getString(R.string.wine_url))
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewWine);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // TODO: 4/21/2017 fix strings
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewCaterers:
                mCategory = "Caterer";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewFoodTrucks:
                mCategory = "Food Trucks";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewBakeries:
                mCategory = getString(R.string.bakeries);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewOrganic:
                mCategory = getString(R.string.organic);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewMixologists:
                mCategory = getString(R.string.mixologists);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewKegs:
                mCategory = getString(R.string.kegs);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewWholesale:
                mCategory = getString(R.string.wholesale);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewWine:
                mCategory = getString(R.string.wine);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewDjs:
                mCategory = getString(R.string.djs);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewLive:
                mCategory = getString(R.string.live);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewInstrument:
                mCategory = getString(R.string.instruments);
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewVocal:
                mCategory = getString(R.string.vocal);
                getConsumerBudgetFragment();
                break;
            default:
        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mLayoutVendors.isShown()){
//            ViewAnimator.animate(mLayout1)
//                    .fadeIn()
//                    .slideTop()
//                    .duration(500)
//                    .andAnimate(mLayout2)
//                    .slideRight()
//                    .fadeIn()
//                    .duration(500)
//                    .andAnimate(mLayout3)
//                    .slideBottom()
//                    .fadeIn()
//                    .duration(500)
//                    .descelerate()
//                    .start();
//        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
//        ViewAnimator.animate(mLayout1)
//                .slideLeft()
//                .duration(100)
//                .andAnimate(mLayout2)
//                .slideLeft()
//                .duration(200)
//                .andAnimate(mLayout3)
//                .slideLeft()
//                .duration(300)
//                .start();
    }

    public void getConsumerBudgetFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerEventActivity, FragmentUtil.getConsumerBudgetFragment())
                .addToBackStack(null)
                .commit();
    }

}
