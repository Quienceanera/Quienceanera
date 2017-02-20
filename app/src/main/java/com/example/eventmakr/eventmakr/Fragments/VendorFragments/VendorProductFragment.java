package com.example.eventmakr.eventmakr.Fragments.VendorFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Objects.Menu;
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

public class VendorProductFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String TAG = "VendorProductFragment";

    private OnFragmentInteractionListener mListener;
    private final static int SELECT_PHOTO = 0;
    private ProgressBar mProgressBar;
    private StorageReference mStorageReference, mPhotoRef;
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mPushRef;
    private Uri mMediaUri;
    private FloatingActionButton mFabMenuAdd, mFabMenuSave, mFabMenuCancel;
    private ImageView mImageViewAddMenuItem;
    private EditText mEditTextMenuItemName, mEditTextMenuItemDescription, mEditTextMenuItemPrice;
    private Context mContext;
    private RelativeLayout mLayoutAddMenuItem;
    private String mKey, mMenuItemName, mMenuItemDescription, mMenuItemImage, mMenuItemPrice;

    public VendorProductFragment() {
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
        final View view = inflater.inflate(R.layout.fragment_vendor_menu_add, container, false);

        mFabMenuAdd = (FloatingActionButton) view.findViewById(R.id.fabAddMenuItem);
        mFabMenuSave = (FloatingActionButton) view.findViewById(R.id.fabSaveMenuItem);
        mFabMenuCancel = (FloatingActionButton) view.findViewById(R.id.fabCancelMenuItem);
        mLayoutAddMenuItem = (RelativeLayout) view.findViewById(R.id.layoutAddMenuItem);
        mImageViewAddMenuItem = (ImageView) view.findViewById(R.id.imageViewAddMenuItem);
        mEditTextMenuItemName = (EditText) view.findViewById(R.id.editTextMenuItemName);
        mEditTextMenuItemDescription = (EditText) view.findViewById(R.id.editTextMenuAddItemDescription);
        mEditTextMenuItemPrice = (EditText) view.findViewById(R.id.editTextMenuAddItemPrice);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarAddMenuItem);

        mContext = getActivity();

        mStorageReference = mFirebaseStorage.getReference().child("vendor").child("menu");

        mFabMenuAdd.setOnClickListener(this);
        mFabMenuCancel.setOnClickListener(this);
        mFabMenuSave.setOnClickListener(this);

        return view;
    }

    void getKey () {
        mDatabaseRef = FirebaseUtil.getMenuRef();
        mPushRef = mDatabaseRef.push();
        mKey = mPushRef.getKey();
        databasePush();
    }
    void databasePush () {
        mMenuItemName = mEditTextMenuItemName.getText().toString();
        mMenuItemDescription = mEditTextMenuItemDescription.getText().toString();
        mMenuItemPrice = mEditTextMenuItemPrice.getText().toString();

        Menu menu = new Menu(
                mMenuItemName,
                mMenuItemImage,
                mMenuItemPrice,
                mMenuItemDescription,
                mKey
        );
        mPushRef.setValue(menu);
    }

    void addMenuItemLayout () {
        ViewAnimator.animate(mLayoutAddMenuItem)
                .bounceIn()
                .descelerate()
                .duration(500)
                .start();
        mLayoutAddMenuItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            mMediaUri = data.getData();
            mImageViewAddMenuItem.setImageURI(mMediaUri);
            mProgressBar.setVisibility(View.VISIBLE);
            addMenuItemLayout();
            mPhotoRef = mStorageReference.child(mMediaUri.getLastPathSegment());
            mPhotoRef.putFile(mMediaUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    mMenuItemImage = taskSnapshot.getDownloadUrl().toString();
                    Toast.makeText(mContext, "Photo Uploaded", Toast.LENGTH_SHORT).show();
                }
            });
            Toast.makeText(mContext, "if", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "else", Toast.LENGTH_SHORT).show();
        }
    }

    void photoIntent () {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickPhotoIntent.setType("image/*");
        startActivityForResult(pickPhotoIntent, SELECT_PHOTO);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
            switch (id) {
                case R.id.fabAddMenuItem:
                    photoIntent();
                    break;
                case R.id.fabSaveMenuItem:
                    getKey();
                    break;
                default:
            }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
