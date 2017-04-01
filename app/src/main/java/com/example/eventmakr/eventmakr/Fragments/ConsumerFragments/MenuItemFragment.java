package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;


public class MenuItemFragment extends android.app.Fragment implements  View.OnClickListener{
    private static final String TAG = "MenuItemFragment";
    private OnFragmentInteractionListener mListener;

    private CardView buttonSelect;

    public MenuItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_item, container, false);
        buttonSelect = (CardView) view.findViewById(R.id.buttonProductItemSelect);
        buttonSelect.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonProductItemSelect:
//                getVendorProfileFragment();
                Toast.makeText(getActivity(), "menu item selected", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
//    public void getVendorProfileFragment() {
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorProfileFragment())
//                .addToBackStack(null)
//                .commit();
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
