package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventmakr.eventmakr.Activities.PayFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class CartFragment extends android.app.Fragment {
    private static final String TAG = CartFragment.class.getSimpleName();
    private CardView mButtonCheckout;

    public CartFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);
        mButtonCheckout = (CardView) view.findViewById(R.id.buttonCheckout);

        mButtonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPayFragment();
            }
        });
        getCartList();

        return view;
    }

    void goToPayFragment(){
        startActivity(new Intent(getActivity(), PayFragment.class));
    }

    void getCartList(){
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerCart, FragmentUtil.getOrderListItemFragment())
                .commit();
    }

}
