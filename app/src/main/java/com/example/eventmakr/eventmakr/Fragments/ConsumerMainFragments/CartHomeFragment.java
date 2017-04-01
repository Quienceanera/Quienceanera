package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;


public class CartHomeFragment extends android.app.Fragment {
    private static final String TAG = CartHomeFragment.class.getSimpleName();

    public CartHomeFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_cart_home, container, false);

        getChildCartHomeList();

        return view;
    }

    void getChildCartHomeList() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerCartHomeItemFragment, FragmentUtil.getCartHomeItemFragment())
                .commit();
    }

}
