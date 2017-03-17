package com.example.eventmakr.eventmakr.Fragments.ConsumerNavBarFragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Adapters.EventsAdapter;
import com.example.eventmakr.eventmakr.R;

import static com.example.eventmakr.eventmakr.Utils.FragmentUtil.getUserFragment;


public class NavBar extends android.app.Fragment implements View.OnClickListener {
    private ImageView mButtonHome, mButtonChat, mButtonCart, mButtonUser;
    private FragmentManager mFragmentManager;


    public NavBar() {
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
        final View view = inflater.inflate(R.layout.fragment_consumer_nav_bar, container, false);
        mButtonHome = (ImageView) view.findViewById(R.id.navButton_home);
        mButtonChat = (ImageView) view.findViewById(R.id.navButton_chat);
        mButtonCart = (ImageView) view.findViewById(R.id.navButton_cart);
        mButtonUser = (ImageView) view.findViewById(R.id.navButton_user);
        mButtonHome.setOnClickListener(this);
        mButtonChat.setOnClickListener(this);
        mButtonCart.setOnClickListener(this);
        mButtonUser.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.navButton_home:
//                if (getActivity().findViewById(R.id.navConsumerActivityLayout).isShown()){
//                    getActivity().findViewById(R.id.consumerActivityLayout).setVisibility(View.VISIBLE);
//                    getActivity().findViewById(R.id.navConsumerActivityLayout).setVisibility(View.GONE);
//                    getActivity().findViewById(R.id.containerEventsList).setVisibility(View.VISIBLE);
//
//                }
//                else {
//                    Intent intent = new Intent(getActivity(), ConsumerActivity.class);
//                    startActivity(intent);
//                }
//                if (getActivity().findViewById(R.id.consumerActivityLayout).isShown()){
//                    getActivity().findViewById(R.id.containerEventsList).setVisibility(View.GONE);
//
//                }

                break;
            case R.id.navButton_chat:
                if (EventsAdapter.mEventKey != null){
//                    getChatFragment();
                }else {
                    Toast.makeText(getActivity(), "Choose an event or create a new one", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.navButton_cart:
                if (EventsAdapter.mEventKey != null){
//                    getCartFragment();
                }else {
                    Toast.makeText(getActivity(), "Choose an event or create a new one", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navButton_user:
                getUserFragment();
                break;
            default:
        }
    }

//    public void getUserFragment () {
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getUserFragment())
//                .addToBackStack(null)
//                .commit();
//        getActivity().findViewById(R.id.consumerActivityLayout).setVisibility(View.GONE);
////        getActivity().findViewById(R.id.containerEventsList).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navConsumerActivityLayout).setVisibility(View.VISIBLE);
//
//    }

//    public void getChatFragment () {
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getChatHomeFragment())
//                .addToBackStack(null)
//                .commit();
//        getActivity().findViewById(R.id.consumerActivityLayout).setVisibility(View.GONE);
////        getActivity().findViewById(R.id.containerEventsList).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navConsumerActivityLayout).setVisibility(View.VISIBLE);
//    }

//    public void getCartFragment () {
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.navConsumerActivityLayout, FragmentUtil.getCartFragment())
//                .addToBackStack(null)
//                .commit();
//        getActivity().findViewById(R.id.consumerActivityLayout).setVisibility(View.GONE);
////        getActivity().findViewById(R.id.containerEventsList).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.navConsumerActivityLayout).setVisibility(View.VISIBLE);
//    }

//    // TODO: Rename method, update argument and hook method into UI event
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

}
