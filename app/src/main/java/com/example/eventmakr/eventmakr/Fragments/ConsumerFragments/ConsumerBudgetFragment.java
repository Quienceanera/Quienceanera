package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.channguyen.rsv.RangeSliderView;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerBudgetFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = "ConsumerBudgetFragment";
    public static String mCategory, mPriceRange;
    private TextView mTextViewVendorCategory;
    private RangeSliderView mSeekBar;
    private CardView mCardViewBudget;
    private FrameLayout mLayoutVendorRecycler;
    static final int REQUESTCODE = 1;

    private OnFragmentInteractionListener mListener;

    public ConsumerBudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        mCategory = ConsumerVendorCategoryFragment.mCategory;
        if (mCategory != null) {
        }
        VendorAdapter.mPriceRange = "$";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_consumer_budget, container, false);

        mSeekBar = (RangeSliderView) view.findViewById(R.id.seekBarBudget);
        mCardViewBudget = (CardView) view.findViewById(R.id.cardViewBudget_helper);
        mLayoutVendorRecycler = (FrameLayout) view.findViewById(R.id.containerRecyclerVendorFragment);

        mSeekBar.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int progress) {
                Log.i("seekbar", String.valueOf(progress));
                if (progress == 1) {
                    VendorAdapter.mPriceRange = "$$";
                    getUpdateChildRecyclerVendorFragment();
                } if (progress == 0) {
                    VendorAdapter.mPriceRange = "$";
                    getUpdateChildRecyclerVendorFragment();
                } if (progress == 2) {
                    VendorAdapter.mPriceRange = "$$$";
                    getUpdateChildRecyclerVendorFragment();
                }
                if (VendorAdapter.mPriceRange == "$$"){
                    progress = 1;
                } if (VendorAdapter.mPriceRange == "$$$"){
                    progress = 2;
                } if (VendorAdapter.mPriceRange == "$"){
                    progress = 0;
                }
            }
        });



        getChildRecyclerVendorFragment();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewAnimator.animate(mCardViewBudget)
                .slideBottom()
                .duration(500)
                .andAnimate(mLayoutVendorRecycler)
                .slideTop()
                .duration(500)
                .start();
    }

    void getChildRecyclerVendorFragment () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorFragment, FragmentUtil.getRecyclerVendorFragment())
                .commit();
    }

    void getUpdateChildRecyclerVendorFragment () {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.containerRecyclerVendorFragment, FragmentUtil.getRecyclerVendorFragment())
                .commit();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
