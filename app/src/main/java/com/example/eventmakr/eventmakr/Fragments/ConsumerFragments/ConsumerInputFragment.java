package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.firebase.database.DatabaseReference;

public class ConsumerInputFragment extends android.app.Fragment implements View.OnClickListener{
    private static final String TAG = "ConsumerInputFragment";
    private RelativeLayout mLayoutConsumer1, mLayoutConsumer2;
    private Context mContext;
    private CardView mButtonNext, mButtonFindVendors;
    private Spinner mSpinner;
    private String[] Events = {"Choose an Event","Quienceanera", "Birthday", "Wedding", "Baby Shower", "Graduation"};
    private String mEventZipCode, mEventId, mEventPhoto, mUid;
    private EditText mEditTextZipCode, mEditTextEventName;
    private DatabaseReference mPushRef, mEventsRef;
    private int mImageId;
    private CalendarView mCalendarView;
    private Boolean mCheckFields = false;
    public static String mEventKey, mEventAddress, mEventDate, mEventName, mEventType;

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
        mEditTextZipCode = (EditText) view.findViewById(R.id.editTextZipCode);
        mEditTextEventName = (EditText) view.findViewById(R.id.editTextInputEventName);
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        mSpinner = (Spinner) view.findViewById(R.id.spinner_occasion);
        mSpinner.setAdapter(new spinnerAdapter(getActivity(), R.layout.custom_spinner, R.id.textViewSpinner, Events));

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mEventType = parent.getItemAtPosition(position).toString();
                mImageId = (int) parent.getItemIdAtPosition(position);
                Log.i("Event Type", mEventType + " " + mImageId);
                mCheckFields = true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mEventDate = String.valueOf(month +"-"+ dayOfMonth +"-"+ year);
                Log.i("Event Date", mEventDate);
            }
        });

        mLayoutConsumer1 = (RelativeLayout) view.findViewById(R.id.layoutConsumerInput1);
        mLayoutConsumer2 = (RelativeLayout) view.findViewById(R.id.layoutConsumerInput2);
        mButtonNext.setOnClickListener(this);
        mButtonFindVendors.setOnClickListener(this);
        return view;
    }

    void createEvent () {
        mEventZipCode = mEditTextZipCode.getText().toString();
        mEventName= mEditTextEventName.getText().toString();
        mEventPhoto = ((String.valueOf(mImageId)));
        mEventsRef = FirebaseUtil.getEventsRef();
        mPushRef = mEventsRef.push();
        mEventId = mPushRef.getKey();
        mUid = FirebaseUtil.getUid();
        com.example.eventmakr.eventmakr.Objects.Events events = new Events(
                mEventId,
                mEventName,
                mEventDate,
                mEventType,
                mEventPhoto,
                mEventZipCode,
                mUid
        );
        mPushRef.setValue(events);
        mEventKey = mEventId;
    }

    public class spinnerAdapter extends ArrayAdapter {
        public spinnerAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int text, @NonNull Object[] objects) {
            super(context, resource, text, objects);
        }
        public View getCustomView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView mTextViewSpinnner = (TextView) layout.findViewById(R.id.textViewSpinner);
            mTextViewSpinnner.setText(Events[position]);
            return layout;
        }
        public View getDropDown(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonNextInput:
//                if (mEventZipCode != null || !mCheckFields) {
                    ViewAnimator.animate(mLayoutConsumer1)
                            .slideLeft()
                            .duration(300)
                            .andAnimate(mLayoutConsumer2)
                            .slideRight()
                            .descelerate()
                            .duration(600)
                            .start();
                    mLayoutConsumer1.setVisibility(View.GONE);
                    mLayoutConsumer2.setVisibility(View.VISIBLE);
                    Log.i("Event press", mEventDate +" "+ mEventZipCode +" "+ mCheckFields);
//                }
                Log.i("Event press", mEventDate +" "+ mEventZipCode +" "+ mCheckFields);
//                Toast.makeText(mContext, "Please choose an Occasion and enter your zip code", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonFindVendors:
                createEvent();
                getFragmentManager().beginTransaction().replace(R.id.consumerActivityLayout, FragmentUtil.getConsumerVendorCategoryFragment()).commit();
            default:
        }
    }
}
