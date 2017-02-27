package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Adapters.VendorAdapter;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerBudgetFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = "ConsumerBudgetFragment";
    public static String mCategory, mPriceRange;
    private TextView mTextViewVendorCategory;
    private SeekBar mSeekBar;
    static final int REQUESTCODE = 1;


    private OnFragmentInteractionListener mListener;

    public ConsumerBudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = ConsumerVendorCategoryFragment.mCategory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_consumer_budget, container, false);

        mTextViewVendorCategory = (TextView) view.findViewById(R.id.textViewVendorCategory);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekBarBudget);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 30 && progress < 70) {
                    VendorAdapter.mPriceRange = "$$";
                    getUpdateChildRecyclerVendorFragment();
//                    Toast.makeText(getActivity(), "$$", Toast.LENGTH_SHORT).show();
                } if (progress < 30) {
                    VendorAdapter.mPriceRange = "$";
                    getUpdateChildRecyclerVendorFragment();
//                    Toast.makeText(getActivity(), "$", Toast.LENGTH_SHORT).show();
                } if (progress > 70) {
                    VendorAdapter.mPriceRange = "$$$";
                    getUpdateChildRecyclerVendorFragment();
//                    Toast.makeText(getActivity(), "$$$", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        if (mCategory != null) {
            mTextViewVendorCategory.setText(mCategory + "s");
        }

        getChildRecyclerVendorFragment();

        return view;
    }

    void getChildRecyclerVendorFragment () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorFragment, FragmentUtil.getRecyclerVendorFragment())
                .commit();
    }

    void getUpdateChildRecyclerVendorFragment () {
//        updatePrice();
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.containerRecyclerVendorFragment, FragmentUtil.getRecyclerVendorFragment())
                .commit();

    }
    private void updatePrice () {

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
