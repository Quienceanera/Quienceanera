package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerVendorCategoryFragment extends android.app.Fragment implements View.OnClickListener {

    private static final String TAG = "ConsumerVendorCategoryFragment";
    public static String mCategory;
    private TextView mTextViewVendorCount;

    private OnFragmentInteractionListener mListener;
    private CardView mCardViewCaterers, mCardViewMixologists, mCardViewFlorists, mCardViewPartySupplies, mCardViewDjs, mCardViewBakeries;

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

        mTextViewVendorCount = (TextView) view.findViewById(R.id.textViewFoundVendors1);

        mCardViewCaterers.setOnClickListener(this);
        mCardViewMixologists.setOnClickListener(this);
        mCardViewFlorists.setOnClickListener(this);
        mCardViewPartySupplies.setOnClickListener(this);
        mCardViewDjs.setOnClickListener(this);
        mCardViewBakeries.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    public void getConsumerBudgetFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerBudgetFragment())
                .addToBackStack(null)
                .commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
