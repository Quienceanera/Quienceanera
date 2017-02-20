package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Objects.Vendor;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class VendorInputFragment extends android.app.Fragment implements View.OnClickListener{

    private final static int SELECT_PHOTO = 0;
    private String mName, mOwner, mContact, mId, mAddress, mZipcode, mDescription, mPrice, mLogo, mKey, mCategory;
    private CardView mButtonNext, mButtonSave;
    private ProgressBar mProgressBar;
    private StorageReference mStorageReference, mPhotoRef;
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mPushRef;
    private ImageView mImageViewLogo;
    private Uri mMediaUri;
    private View mView;
    private RelativeLayout mLayout1, mLayout2;
    private Boolean mStep1, mStep2, mStep3 = false;
    private TextView mTextViewButton;
    private Context mContext;
    private EditText mEditTextName, mEditTextOwner, mEditTextContact, mEditTextDescription, mEditTextCategory, mEditTextAddress, mEditTextZipcode, mEditTextPrice;

    public VendorInputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_vendor_input, container, false);
        mButtonNext = (CardView) mView.findViewById(R.id.buttonNext);
        mButtonSave = (CardView) mView.findViewById(R.id.buttonSave);
        mTextViewButton = (TextView) mView.findViewById(R.id.textViewVendorInputButton);
        mEditTextName = (EditText) mView.findViewById(R.id.editTextVendorInputName);
        mEditTextOwner = (EditText) mView.findViewById(R.id.editTextVendorInputOwner);
        mEditTextContact = (EditText) mView.findViewById(R.id.editTextVendorInputContact);
        mEditTextDescription = (EditText) mView.findViewById(R.id.editTextVendorInputDescription);
        mEditTextCategory = (EditText) mView.findViewById(R.id.editTextVendorInputCategory);
        mEditTextAddress = (EditText) mView.findViewById(R.id.editTextVendorInputAddress);
        mEditTextZipcode = (EditText) mView.findViewById(R.id.editTextVendorInputZipcode);
        mEditTextPrice = (EditText) mView.findViewById(R.id.editTextVendorInputPrice);
        mImageViewLogo = (ImageView) mView.findViewById(R.id.imageViewVendorInputLogo);
        mLayout1 = (RelativeLayout) mView.findViewById(R.id.layoutVendorInput);
        mLayout2 = (RelativeLayout) mView.findViewById(R.id.layoutVendorInput2);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.progressBarVendorInput);

        mContext = getActivity();

        mStorageReference = mFirebaseStorage.getReference().child("vendor");
        mButtonNext.setOnClickListener(this);
        mButtonSave.setOnClickListener(this);
        mImageViewLogo.setOnClickListener(this);


        return mView;
    }

    public void getKey () {
        mDatabaseRef = FirebaseUtil.getVendorRef();
        mPushRef = mDatabaseRef.push();
        mKey = mPushRef.getKey();
        databasePush();
    }
    public void databasePush() {
        mName = mEditTextName.getText().toString();
        mOwner = mEditTextOwner.getText().toString();
        mContact = mEditTextContact.getText().toString();
        mId = mFirebaseAuth.getCurrentUser().getUid();
        mAddress = mEditTextAddress.getText().toString();
        mZipcode = mEditTextZipcode.getText().toString();
        mDescription = mEditTextDescription.getText().toString();
        mPrice = mEditTextPrice.getText().toString();
        mCategory = mEditTextCategory.getText().toString();

        Vendor vendor = new Vendor(
                mName,
                mOwner,
                mContact,
                mId,
                mAddress,
                mZipcode,
                mDescription,
                mPrice,
                mLogo,
                mCategory
        );
        mPushRef.setValue(vendor);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            mMediaUri = data.getData();
            mImageViewLogo.setImageURI(mMediaUri);
            mProgressBar.setVisibility(View.VISIBLE);
            mPhotoRef = mStorageReference.child(mMediaUri.getLastPathSegment());
            mPhotoRef.putFile(mMediaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   mProgressBar.setVisibility(View.INVISIBLE);
                    mLogo = taskSnapshot.getDownloadUrl().toString();
                    Toast.makeText(mContext, "Photo Uploaded", Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(mContext, "if", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "else", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonNext:
                ViewAnimator.animate(mLayout1)
                        .fadeOut()
                        .duration(300)
                        .andAnimate(mLayout2)
                        .slideRight()
                        .descelerate()
                        .duration(600)
                        .start();
                mLayout1.setVisibility(View.GONE);
                mLayout2.setVisibility(View.VISIBLE);
//                Toast.makeText(view.getContext(), "clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonSave:
                getKey();
                break;
            case R.id.imageViewVendorInputLogo:
                Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                pickPhotoIntent.setType("image/*");
                startActivityForResult(pickPhotoIntent, SELECT_PHOTO);
                break;
            default:
        }

    }


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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
