package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import static android.R.attr.dial;
import static android.app.Activity.RESULT_OK;

public class VendorAddProductDialogFragment extends DialogFragment{

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
        mImageViewAddProduct = (ImageView) view.findViewById(R.id.imageViewAddProduct);
        mEditTextProductName = (EditText) view.findViewById(R.id.editTextProductName);
        mEditTextProductDescription = (EditText) view.findViewById(R.id.editTextAddProductDescription);
        mEditTextProductPrice = (EditText) view.findViewById(R.id.editTextAddProductPrice);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarAddProduct);
        mVendorUid = FirebaseUtil.getUid();
        mVendorKey = VendorInputFragment.mVendorKey;

        mStorageReference = mFirebaseStorage.getReference().child("vendor").child(mVendorUid).child("product");

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getKey();
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
                mVendorUid,
                FirebaseUtil.getUserName()
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
                    Snackbar.make(getActivity().findViewById(R.id.layoutVendorExtras), "Photo Uploaded!", Snackbar.LENGTH_SHORT).show();
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

}
