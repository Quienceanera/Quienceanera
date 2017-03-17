package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_fragment_vendor_menu_add, null);
        builder.setView(view);

        photoIntent();
        mFabProductSave = (FloatingActionButton) view.findViewById(R.id.fabSaveProduct);
        mFabProductCancel = (FloatingActionButton) view.findViewById(R.id.fabCancelProduct);
        mImageViewAddProduct = (ImageView) view.findViewById(R.id.imageViewAddProduct);
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

    void getKey () {
        mDatabaseRef = FirebaseUtil.getVendorSideVendorProductRef();
        mPushRef = mDatabaseRef.push();
        mKey = mPushRef.getKey();
        databasePush();
    }
    void databasePush () {
        mProductName = mEditTextProductName.getText().toString();
        mProductDescription = mEditTextProductDescription.getText().toString();
        mProductPrice = mEditTextProductPrice.getText().toString();

        Menu menu = new Menu(
                mProductName,
                mProductImage,
                mProductPrice,
                mProductDescription,
                mKey,
                mVendorUid
        );
        mPushRef.setValue(menu);
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
                    Toast.makeText(getActivity(), "Photo Uploaded", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
        }
    }

    void photoIntent () {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickPhotoIntent.setType("image/*");
        startActivityForResult(pickPhotoIntent, SELECT_PHOTO);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fabCancelProduct:
                dismiss();
                break;
            case R.id.fabSaveProduct:
                getKey();
                dismiss();
                break;
            default:
        }
    }
}
