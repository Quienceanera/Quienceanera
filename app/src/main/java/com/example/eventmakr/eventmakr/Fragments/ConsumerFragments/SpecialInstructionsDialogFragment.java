package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;
import com.google.firebase.database.DatabaseReference;

public class SpecialInstructionsDialogFragment extends DialogFragment{
    private static final String TAG = SpecialInstructionsDialogFragment.class.getSimpleName();
    public static String mInstructions;
    private EditText mEditTextSpecialInstructions;
    private DatabaseReference mPushRef, mInstructionsRef;

    public interface DialogListener {
        void onFinish(String mInstructions);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_special_instructions, null);
        builder.setView(view);
        Log.i(TAG, TAG);
        mEditTextSpecialInstructions = (EditText) view.findViewById(R.id.editTextInstructions);

        builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mInstructions = mEditTextSpecialInstructions.getText().toString();

                if (TextUtils.isEmpty(mInstructions)){
                    Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                }else{
                    sendBackResult();
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

    public void sendBackResult(){
        DialogListener listener = (DialogListener) getTargetFragment();
        listener.onFinish(mEditTextSpecialInstructions.getText().toString());
        dismiss();
    }
//    void sendRecommendation() {
//        mInstructionsRef = FirebaseUtil.getFeedbackRef();
//        mPushRef = mInstructionsRef.push();
//        mInstructionsKey = mPushRef.getKey();
//        Feedback feedback = new Feedback(
//                FirebaseUtil.getUid(),
//                FirebaseUtil.getUserName(),
//                mInstructions
//        );
//        mPushRef.setValue(feedback);
//    }

}
