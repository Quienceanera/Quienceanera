package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerInputFragment extends android.app.Fragment implements View.OnClickListener {
    private static final String TAG = "ConsumerInputFragment";
    private RelativeLayout mLayoutConsumer1, mLayoutConsumer2;
    private Context mContext;
    private CardView mButtonNext, mButtonFindVendors;

    public ConsumerInputFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumer_input, container, false);
        mButtonNext = (CardView) view.findViewById(R.id.buttonNextInput);
        mButtonFindVendors = (CardView) view.findViewById(R.id.buttonFindVendors);
        mLayoutConsumer1 = (RelativeLayout) view.findViewById(R.id.layoutConsumerInput1);
        mLayoutConsumer2 = (RelativeLayout) view.findViewById(R.id.layoutConsumerInput2);
        mButtonNext.setOnClickListener(this);
        mButtonFindVendors.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonNextInput:
                ViewAnimator.animate(mLayoutConsumer1)
                        .fadeOut()
                        .duration(300)
                        .andAnimate(mLayoutConsumer2)
                        .slideRight()
                        .descelerate()
                        .duration(600)
                        .start();
                mLayoutConsumer1.setVisibility(View.GONE);
                mLayoutConsumer2.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonFindVendors:
                getFragmentManager().beginTransaction().replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorCategoryFragment()).commit();
            default:
        }

    }


}
