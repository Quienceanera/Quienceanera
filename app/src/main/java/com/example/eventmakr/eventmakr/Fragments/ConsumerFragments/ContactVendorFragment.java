package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ContactVendorFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ContactVendorFragment";
    private TextView mTextViewTotal;


    public ContactVendorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact_vendor, container, false);
        mTextViewTotal = (TextView) view.findViewById(R.id.textViewTotalPrice);
        getChildRecyclerItems();
        return view;
    }


    void getChildRecyclerItems () {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerItemsFragment, FragmentUtil.getRecyclerItemsFragment())
                .commit();
    }

    @Override
    public void onClick(View view) {

    }

}
