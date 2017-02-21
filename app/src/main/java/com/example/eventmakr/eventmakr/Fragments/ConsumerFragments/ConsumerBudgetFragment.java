package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerBudgetFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = "ConsumerBudgetFragment";


    private OnFragmentInteractionListener mListener;

    public ConsumerBudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_consumer_budget, container, false);

        getChildRecyclerVendorFragment();

        return view;
    }

    void getChildRecyclerVendorFragment () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorFragment, FragmentUtil.getRecyclerVendorFragment())
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
