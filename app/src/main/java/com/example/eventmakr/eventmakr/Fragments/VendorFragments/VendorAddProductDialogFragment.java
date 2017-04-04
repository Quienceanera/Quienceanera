package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.eventmakr.eventmakr.Objects.Menu;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class VendorAddProductDialogFragment extends DialogFragment implements View.OnClickListener{

    private static final String TAG = VendorAddProductDialogFragment.class.getSimpleName();
    private final static int SELECT_PHOTO = 0;
    private ProgressBar mProgressBar;
    private StorageReference mStorageReference, mPhotoRef;
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mPushRef;
    private Uri mMediaUri;
    private FloatingActionButton mFabProductSave, mFabProductCancel;
    private ImageView mImageViewAddProduct;
    private EditText mEditTextProductName, mEditTextProductDescription, mEditTextProductPrice;
    private String mKey, mProductName, mProductDescription, mProductImage, mProductPrice, mVendorUid;
    public static String mVendorKey;
    private String REQUIRED = "Required";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_vendor_menu_add, null);
        builder.setView(view);
        Log.i(TAG, TAG);
        setCancelable(false);

        photoIntent();
        mImageViewAddProduct = (ImageView) view.findViewById(R.id.imageViewAddProduct);
        mFabProductCancel = (FloatingActionButton) view.findViewById(R.id.fabCancel);
        mFabProductSave = (FloatingActionButton) view.findViewById(R.id.fabAdd);
        mEditTextProductName = (EditText) view.findViewById(R.id.editTextProductName);
        mEditTextProductDescription = (EditText) view.findViewById(R.id.editTextAddProductDescription);
        mEditTextProductPrice = (EditText) view.findViewById(R.id.editTextAddProductPrice);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarAddProduct);
        mVendorUid = FirebaseUtil.getUid();
        mVendorKey = VendorInputFragment.mVendorKey;

        mStorageReference = mFirebaseStorage.getReference().child("vendor").child(mVendorUid).child("product");

        mFabProductCancel.setOnClickListener(this);
        mFabProductSave.setOnClickListener(this);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

    void getStrings(){
        mProductName = mEditTextProductName.getText().toString();
        mProductDescription = mEditTextProductDescription.getText().toString();
        mProductPrice = mEditTextProductPrice.getText().toString();
        if (mProductName.isEmpty()||
                mProductDescription.isEmpty()||
                mProductPrice.isEmpty()||
                mProductImage.isEmpty()){
            if (TextUtils.isEmpty(mProductName)){
                mEditTextProductName.setError("Can not be empty");
            }
            if (TextUtils.isEmpty(mProductDescription)){
                mEditTextProductDescription.setError("Can not be empty");
            }
            if (TextUtils.isEmpty(mProductPrice)){
                mEditTextProductPrice.setError("Can not be empty");
            }
        } else{
            getKey();
        }
    }

    void getKey () {
        mDatabaseRef = FirebaseUtil.getVendorSideVendorProductRef();
        mPushRef = mDatabaseRef.push();
        mKey = mPushRef.getKey();
        databasePush();
    }
    void databasePush () {

        Menu menu = new Menu(
                mProductName,
                mProductImage,
                mProductPrice,
                mProductDescription,
                mKey,
                mVendorUid,
                FirebaseUtil.getUserName()
        );
        mPushRef.setValue(menu);
        dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            mMediaUri = data.getData();
            mImageViewAddProduct.setImageURI(mMediaUri);
            mProgressBar.setVisibility(View.VISIBLE);
            mPhotoRef = mStorageReference.child(mMediaUri.getLastPathSegment());
            mPhotoRef.putFile(mMediaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mProductImage = taskSnapshot.getDownloadUrl().toString();
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.layoutVendorExtras), "Photo Uploaded!", Snackbar.LENGTH_SHORT);
                    View view = snackbar.getView();
                    view.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.blue));
                    snackbar.show();

                }
            });
        } else {
            dismiss();
        }
    }

    void photoIntent () {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickPhotoIntent.setType("image/*");
        startActivityForResult(pickPhotoIntent, SELECT_PHOTO);
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.tw__transparent);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabAdd:
                getStrings();
                break;
            case R.id.fabCancel:
                dismiss();
        }
    }
}
