package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Objects.Feedback;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;

public class RecommendVendorDialogFragment extends DialogFragment{

    private String mFeedBack, mFeedbackKey;
    private EditText mEditTextRecommendVendor;
    private DatabaseReference mPushRef, mFeedBackRef;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_recommend_vendor, null);
        builder.setView(view);

        mEditTextRecommendVendor = (EditText) view.findViewById(R.id.editTextRecommendVendor);

        builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFeedBack = mEditTextRecommendVendor.getText().toString();

                if (TextUtils.isEmpty(mFeedBack)){
                    Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                }else{
                    sendRecommendation();
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }
    void sendRecommendation() {
        mFeedBackRef = FirebaseUtil.getFeedbackRef();
        mPushRef = mFeedBackRef.push();
        mFeedbackKey = mPushRef.getKey();
        Feedback feedback = new Feedback(
                FirebaseUtil.getUid(),
                FirebaseUtil.getUserName(),
                mFeedBack
        );
        mPushRef.setValue(feedback);
    }

}
