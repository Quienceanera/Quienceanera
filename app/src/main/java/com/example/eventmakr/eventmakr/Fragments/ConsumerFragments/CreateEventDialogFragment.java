package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;

public class CreateEventDialogFragment extends DialogFragment implements View.OnClickListener{
    private Spinner mSpinner;
    private String[] Events = {"Choose an Event","Quienceanera", "Birthday", "Wedding", "Baby Shower", "Graduation"};
    private String mEventZipCode, mEventId, mEventPhoto, mUid;
    private EditText mEditTextZipCode, mEditTextEventName;
    private DatabaseReference mPushRef, mEventsRef;
    private int mImageId;
    private CalendarView mCalendarView;
    private FloatingActionButton mFabCreateEvent, mFabCancelEvent;
    private Boolean mCheckFields = false;
    public static String mEventKey, mEventAddress, mEventDate, mEventName, mEventType;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_create_event, null);
        builder.setView(view);

        mFabCancelEvent = (FloatingActionButton) view.findViewById(R.id.fabCancelEvent);
        mFabCreateEvent = (FloatingActionButton) view.findViewById(R.id.fabCreateEvent);
        mEditTextZipCode = (EditText) view.findViewById(R.id.editTextZipCode);
        mEditTextEventName = (EditText) view.findViewById(R.id.editTextInputEventName);
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        mSpinner = (Spinner) view.findViewById(R.id.spinner_occasion);
        mSpinner.setAdapter(new spinnerAdapter(getActivity(), R.layout.custom_spinner, R.id.textViewSpinner, Events));

        mFabCreateEvent.setOnClickListener(this);
        mFabCancelEvent.setOnClickListener(this);

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

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fabCancelEvent:
                dismiss();
                break;
            case R.id.fabCreateEvent:
                createEvent();
                dismiss();
                break;
            default:
        }
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
}
