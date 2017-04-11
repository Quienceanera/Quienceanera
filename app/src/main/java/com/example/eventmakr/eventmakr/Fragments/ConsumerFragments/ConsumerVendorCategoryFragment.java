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
import com.google.android.gms.ads.AdView;

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

        return view;
    }

    void loadImages () {
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fcaterers.png?alt=media&token=726a98cc-307f-4cd2-9c1b-35370a2b8e36")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewCaterers);


        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fbakeries.jpg?alt=media&token=544ed26b-6112-4d6a-b3c7-3ecafa3adfa4")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewBakeries);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Ffoodtruck.jpg?alt=media&token=e2b974af-ba24-4238-81de-3821ef5b7194")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewFoodTrucks);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Forganic.jpg?alt=media&token=8c438984-60e1-4c45-ab75-059664439386")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewOrganic);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fdj.jpg?alt=media&token=0b9945d7-2dd9-4c34-bc46-4b9cc2a98540")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewDjs);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Flive.jpg?alt=media&token=239bb3cf-1bd1-4a60-9ae4-fced84d0485b")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewLive);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Finstrument.jpg?alt=media&token=33ec7c16-74d0-4168-b5ec-5729373a3829")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewInstrument);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fvocal.jpg?alt=media&token=4d00d426-0f27-4b35-82fc-f27c44e7ad39")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewVocal);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fmixologists.jpg?alt=media&token=fde6dbba-c7af-41a5-b17a-2859bccd4f27")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewMixologists);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fkeg.jpg?alt=media&token=5248798c-0401-46d4-adb7-d652a7a967b9")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewKegs);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fwholesale.jpg?alt=media&token=c77218f3-9ba4-4908-9ba8-5ef4216f2c43")
                .centerCrop()
                .crossFade()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewWholesale);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/eventmakr-q.appspot.com/o/default%2Fwine.jpg?alt=media&token=f89b9f45-7be4-4d44-a6b5-7bc97ab4f3dc")
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cardViewCaterers:
                mCategory = "Caterer";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewFoodTrucks:
                mCategory = "FoodTrucks";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewBakeries:
                mCategory = "Bakeries";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewOrganic:
                mCategory = "Organic";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewMixologists:
                mCategory = "Mixologists";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewKegs:
                mCategory = "Kegs";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewWholesale:
                mCategory = "Wholesale";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewWine:
                mCategory = "Wine";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewDjs:
                mCategory = "Djs";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewLive:
                mCategory = "Live";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewInstrument:
                mCategory = "Instruments";
                getConsumerBudgetFragment();
                break;
            case R.id.cardViewVocal:
                mCategory = "Vocal";
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
