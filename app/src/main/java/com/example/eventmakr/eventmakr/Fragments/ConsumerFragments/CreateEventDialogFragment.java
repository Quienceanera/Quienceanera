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
import android.text.TextUtils;
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
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Objects.Events;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.firebase.database.DatabaseReference;

public class CreateEventDialogFragment extends DialogFragment implements View.OnClickListener{
    private static final String TAG = CreateEventDialogFragment.class.getSimpleName();
    private Spinner mSpinner;
    private String[] Events = {"Choose an Event","Quienceanera", "Birthday", "Wedding", "Baby Shower", "Graduation"};
    private String mEventZipCode, mEventId, mEventPhoto, mUid;
    private EditText mEditTextZipCode, mEditTextEventName;
    private DatabaseReference mPushRef, mEventsRef;
    private FloatingActionButton mFabCreate, mFabCancel;
    private int mImageId;
    private CalendarView mCalendarView;
    private Boolean mCheckFields = false;
    public static String mEventKey, mEventAddress, mEventDate, mEventName, mEventType;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_create_event, null);
        builder.setView(view);
        Log.i(TAG, TAG);
        getHelpPrompt();

        mEditTextZipCode = (EditText) view.findViewById(R.id.editTextZipCode);
        mEditTextEventName = (EditText) view.findViewById(R.id.editTextInputEventName);
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        mFabCancel = (FloatingActionButton) view.findViewById(R.id.fabCancel);
        mFabCreate = (FloatingActionButton) view.findViewById(R.id.fabCreate);
        mSpinner = (Spinner) view.findViewById(R.id.spinner_occasion);
        mSpinner.setAdapter(new spinnerAdapter(getActivity(), R.layout.custom_spinner, R.id.textViewSpinner, Events));

        mFabCreate.setOnClickListener(this);
        mFabCancel.setOnClickListener(this);

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
                mEventDate = String.valueOf(month +"/"+ dayOfMonth +"/"+ year);
                Log.i("Event Date", mEventDate);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    void getHelpPrompt(){
        final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                .setImageRecourse(R.drawable.help_circle)
                .setTextTitle("Create Event!")
                .setTitleColor(R.color.blue)
                .setTextSubTitle("Choose A Date\n&\nEnter Your Details")
                .setPositiveButtonText("Okay!")
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        alert.show();
    }

    void checkFields(){
        mEventName = mEditTextEventName.getText().toString();
        mEventAddress = mEditTextZipCode.getText().toString();
        if (mEventDate == null){
            mEventDate = "";
        }

        mEventType = mSpinner.toString();

        if (mEventName.isEmpty() ||
                mEventAddress.isEmpty() ||
                mEventDate.isEmpty()||
                mEventType.isEmpty()){
            if (TextUtils.isEmpty(mEventName)){
                mEditTextEventName.setError("Can not be empty");
            }
            if (TextUtils.isEmpty(mEventAddress)){
                mEditTextZipCode.setError("Can not be empty");
            }
            if (TextUtils.isEmpty(mEventType)){
                Toast.makeText(getActivity(), "Choose A Date", Toast.LENGTH_SHORT).show();
            }
            Log.i("is empty", "True");
        } else{
            Log.i("is empty", "false");
            createEvent();
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
        dismiss();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabCreate:
                checkFields();
                break;
            case R.id.fabCancel:
                dismiss();
                break;
        }
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
    public void onResume() {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.tw__transparent);
        super.onResume();
    }
}
