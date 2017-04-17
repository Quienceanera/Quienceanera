package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.Activities.VendorActivity;
import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.Objects.VendorUser;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.github.channguyen.rsv.RangeSliderView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class VendorInputFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = VendorInputFragment.class.getSimpleName();
    private final static int SELECT_PHOTO = 0;
    private String mName, mOwner, mContact, mId, mAddress, mZipcode, mDescription, mPrice, mLogo, mKey, mCategory, mVendorUid;
    public static String mVendorKey;
    private CardView mButtonSave;
    private ProgressBar mProgressBar;
    private StorageReference mStorageReference, mPhotoRef;
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabaseRef, mProfileRef;
    private DatabaseReference mPushRef;
    private ImageView mImageViewLogo;
    private Uri mMediaUri;
    private View mView;
    private RangeSliderView mRangeSlider;
    private TextView m$, m$$, m$$$;
    private EditText mEditTextName, mEditTextOwner, mEditTextContact, mEditTextDescription, mEditTextCategory, mEditTextAddress, mEditTextZipcode, mEditTextPrice;
    private String REQUIRED = "Required";
    public VendorInputFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        mProfileRef = FirebaseUtil.getVendorSideProfileRef();
        mPrice = "$";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_vendor_input, container, false);
        mButtonSave = (CardView) mView.findViewById(R.id.buttonSave);
        mEditTextName = (EditText) mView.findViewById(R.id.editTextVendorInputName);
        mEditTextOwner = (EditText) mView.findViewById(R.id.editTextVendorInputOwner);
        mEditTextContact = (EditText) mView.findViewById(R.id.editTextVendorInputContact);
        mEditTextDescription = (EditText) mView.findViewById(R.id.editTextVendorInputDescription);
        mEditTextCategory = (EditText) mView.findViewById(R.id.editTextVendorInputCategory);
        mEditTextAddress = (EditText) mView.findViewById(R.id.editTextVendorInputAddress);
        mEditTextZipcode = (EditText) mView.findViewById(R.id.editTextVendorInputZipcode);
        mImageViewLogo = (ImageView) mView.findViewById(R.id.imageViewVendorInputLogo);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progressBarVendorInput);
        mVendorUid = mFirebaseAuth.getCurrentUser().getUid();
        m$ = (TextView) mView.findViewById(R.id.$1);
        m$$ = (TextView) mView.findViewById(R.id.$2);
        m$$$ = (TextView) mView.findViewById(R.id.$3);
        mRangeSlider = (RangeSliderView) mView.findViewById(R.id.rangeSliderVendorInput);
        getProfileDetails();

        mRangeSlider.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                if (index == 0) {
                    mPrice = "$";
                    m$.setVisibility(View.VISIBLE);
                    m$$.setVisibility(View.GONE);
                    m$$$.setVisibility(View.GONE);
                }
                if (index == 1){
                    mPrice = "$$";
                    m$.setVisibility(View.VISIBLE);
                    m$$.setVisibility(View.VISIBLE);
                    m$$$.setVisibility(View.GONE);

                }
                if (index == 2){
                    mPrice = "$$$";
                    m$.setVisibility(View.VISIBLE);
                    m$$.setVisibility(View.VISIBLE);
                    m$$$.setVisibility(View.VISIBLE);

                }
            }
        });

        if(mCategory != null) {
            mStorageReference = mFirebaseStorage.getReference().child("vendor").child(mCategory).child(mVendorUid);
            mDatabaseRef = FirebaseUtil.getVendorRef().child(mCategory).child(FirebaseUtil.getUid());
        }


        mButtonSave.setOnClickListener(this);
        mImageViewLogo.setOnClickListener(this);

        return mView;
    }

    void getProfileDetails(){
        mProfileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    mName = dataSnapshot.child("name").getValue().toString();
                    mOwner = dataSnapshot.child("owner").getValue().toString();
                    mContact = dataSnapshot.child("contact").getValue().toString();
                    mAddress = dataSnapshot.child("address").getValue().toString();
                    mZipcode = dataSnapshot.child("zipcode").getValue().toString();
                    mDescription = dataSnapshot.child("description").getValue().toString();
                    mPrice = dataSnapshot.child("price").getValue().toString();
                    mCategory = dataSnapshot.child("category").getValue().toString();
                    mLogo = dataSnapshot.child("logo").getValue().toString();

                    Glide.with(getActivity())
                            .load(mLogo)
                            .centerCrop()
                            .into(mImageViewLogo);
                    mEditTextName.setText(mName);
                    mEditTextOwner.setText(mOwner);
                    mEditTextContact.setText(mContact);
                    mEditTextAddress.setText(mAddress);
                    mEditTextZipcode.setText(mZipcode);
                    mEditTextDescription.setText(mDescription);
                    mEditTextCategory.setText(mCategory);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getStrings(){
        mName = mEditTextName.getText().toString();
        mOwner = mEditTextOwner.getText().toString();
        mContact = mEditTextContact.getText().toString();
        mAddress = mEditTextAddress.getText().toString();
        mZipcode = mEditTextZipcode.getText().toString();
        mDescription = mEditTextDescription.getText().toString();
        mCategory = mEditTextCategory.getText().toString();

        if (mName.isEmpty()||
                mOwner.isEmpty()||
                mContact.isEmpty()||
                mAddress.isEmpty()||
                mZipcode.isEmpty()||
                mDescription.isEmpty()||
                mPrice.isEmpty()||
                mImageViewLogo.getDrawable() == null){

            if (mImageViewLogo.getDrawable() == null){
                Toast.makeText(getActivity(), "Please upload a photo", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(mName)){
                mEditTextName.setError(REQUIRED);
            }
            if (TextUtils.isEmpty(mOwner)){
                mEditTextOwner.setError(REQUIRED);
            }
            if (TextUtils.isEmpty(mContact)){
                mEditTextContact.setError(REQUIRED);
            }
            if (TextUtils.isEmpty(mAddress)){
                mEditTextAddress.setError(REQUIRED);
            }
            if (TextUtils.isEmpty(mZipcode)){
                mEditTextZipcode.setError(REQUIRED);
            }
            if (TextUtils.isEmpty(mDescription)){
                mEditTextDescription.setError(REQUIRED);
            }
        }else{
            getKey();
        }
    }

    public void getKey () {
        mDatabaseRef = FirebaseUtil.getVendorRef().child(mCategory).child(FirebaseUtil.getUid());
        mPushRef = mDatabaseRef;
        mVendorKey = mPushRef.getKey();
        databasePush();
    }
    public void databasePush() {
        Vendor vendor = new Vendor(
                mName,
                mOwner,
                mContact,
                mVendorUid,
                mVendorKey,
                mAddress,
                mZipcode,
                mDescription,
                mPrice,
                mLogo,
                mCategory
        );

        VendorUser vendorUser = new VendorUser(
                mName,
                mOwner,
                mContact,
                mVendorUid,
                mVendorKey,
                mAddress,
                mZipcode,
                mDescription,
                mPrice,
                mLogo,
                mCategory,
                "true"
        );
        mPushRef.setValue(vendor);
        mProfileRef.setValue(vendorUser);
        startActivity(new Intent(getActivity(), VendorActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            mMediaUri = data.getData();
            mImageViewLogo.setImageURI(mMediaUri);
            mProgressBar.setVisibility(View.VISIBLE);
            mStorageReference = mFirebaseStorage.getReference().child("vendor").child(mVendorUid);
            mPhotoRef = mStorageReference.child(mMediaUri.getLastPathSegment());
            mPhotoRef.putFile(mMediaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   mProgressBar.setVisibility(View.INVISIBLE);
                    mLogo = taskSnapshot.getDownloadUrl().toString();
                    Snackbar.make(getActivity().findViewById(R.id.layoutVendorExtras), "Photo Uploaded", Snackbar.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.i(TAG, "Photo Upload Failed");
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonSave:
                getStrings();
                break;
            case R.id.imageViewVendorInputLogo:
                Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                pickPhotoIntent.setType("image/*");
                startActivityForResult(pickPhotoIntent, SELECT_PHOTO);
                break;
            default:
        }

    }

}
