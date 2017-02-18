package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;

public class ConsumerInputFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ConsumerInputFragment";
    private OnFragmentInteractionListener mListener;
    private RelativeLayout mLayoutConsumer1, mLayoutConsumer2;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private CardView mButtonNext, mButtonFindVendors;

    public ConsumerInputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ConsumerInputFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static ConsumerInputFragment newInstance(String param1, String param2) {
//        ConsumerInputFragment fragment = new ConsumerInputFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mFragmentManager = getActivity().getSupportFragmentManager();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
                mFragmentManager.beginTransaction().replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorCategoryFragment()).commit();
                Toast.makeText(mContext, "find vendors", Toast.LENGTH_SHORT).show();
            default:
        }

//        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
//        mFragmentManager.beginTransaction().replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerCalendarFragment()).commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
