package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class CartFragment extends android.app.Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);
        getCartList();

        return view;
    }

    void getCartList(){
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerCart, FragmentUtil.getOrderListItemFragment())
                .commit();
    }
}
