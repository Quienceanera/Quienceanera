package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;

public class ConsumerVendorCategoryFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ConsumerVendorCategoryFragment";


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
        mCardViewCaterers = (CardView) view.findViewById(R.id.cardViewProducts);
        mCardViewMixologists = (CardView) view.findViewById(R.id.cardViewDocuments);
        mCardViewFlorists = (CardView) view.findViewById(R.id.cardViewInputInfo);
        mCardViewPartySupplies = (CardView) view.findViewById(R.id.cardViewPartySupplies);
        mCardViewDjs = (CardView) view.findViewById(R.id.cardViewDjs);
        mCardViewBakeries = (CardView) view.findViewById(R.id.cardViewBakeries);

        mCardViewCaterers.setOnClickListener(this);
        mCardViewMixologists.setOnClickListener(this);
        mCardViewFlorists.setOnClickListener(this);
        mCardViewPartySupplies.setOnClickListener(this);
        mCardViewDjs.setOnClickListener(this);
        mCardViewBakeries.setOnClickListener(this);
        return view;
    }



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
            case R.id.cardViewProducts:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "caterers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewDocuments:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "mixologists", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewInputInfo:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "florists", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewPartySupplies:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "party supplies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewDjs:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "djs", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardViewBakeries:
                getConsumerBudgetFragment();
                Toast.makeText(getContext(), "bakeries", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

    }

    public void getConsumerBudgetFragment() {
        getFragmentManager().beginTransaction().replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerBudgetFragment()).commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
