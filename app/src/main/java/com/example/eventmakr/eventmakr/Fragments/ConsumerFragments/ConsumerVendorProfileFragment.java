package com.example.eventmakr.eventmakr.Fragments.ConsumerFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FirebaseUtil;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class ConsumerVendorProfileFragment extends android.app.Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback {
    private static final String TAG = "VendorProfileFragment";
    private CardView mButtonMyItems, mCardViewMap, mCardViewVendorProfile;
    private Context mContext;
    private DatabaseReference mDatabaseReference, mPlaceIdRef;
    private ImageView mImageViewVendorProfile;
    private TextView mTextViewVendorName, mTextViewVendorDescription, mTextViewVendorAddress;
    private RatingBar mRatingBar;
    private String mVendorLogo, mVendorName, mVendorDescription, mVendorAddress, mVendorCategory, mVendorUid, mPlaceId;
    private RelativeLayout mLayoutContainer;
    private FrameLayout mLayoutProductList;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;
    private LatLng mLatLng;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private boolean mapReady = false;

    public ConsumerVendorProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", TAG);
        Bundle extras = getArguments();
        if (extras == null){
            Log.i(TAG, "extras are null");

        } else {
            mVendorCategory = extras.getString("VendorCategory");
            mVendorUid = extras.getString("VendorUid");

            Log.i(TAG, mVendorCategory+" "+mVendorUid);
        }

        mContext = getActivity();
        mDatabaseReference = FirebaseUtil.getVendorRef().child(mVendorCategory).child(mVendorUid);
        mPlaceIdRef = FirebaseUtil.getConsumerSideVendorPlaceRef();
        mPlaceIdRef.keepSynced(true);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .enableAutoManage((FragmentActivity) getActivity(), this)
                .build();

        mPlaceIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPlaceId = dataSnapshot.getValue().toString();
                Log.i(mPlaceId, dataSnapshot.toString());

                Places.GeoDataApi.getPlaceById(mGoogleApiClient, mPlaceId)
                        .setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(@NonNull PlaceBuffer places) {
                                if (places.getStatus().isSuccess() && places.getCount() > 0) {
                                    final Place mPlace = places.get(0);
                                    mLatLng = mPlace.getLatLng();
                                    mRatingBar.setRating(mPlace.getRating());
                                    Log.i("Vendor Profile", mPlace.getLatLng().toString());
                                } else {
                                    Log.e("Vendor Profile", "Place not found");
                                }
                                places.release();
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_consumer_vendor_profile, container, false);
        mCardViewMap = (CardView) view.findViewById(R.id.cardViewMap);
        mCardViewVendorProfile = (CardView) view.findViewById(R.id.cardViewVendorProfile);
        mImageViewVendorProfile = (ImageView) view.findViewById(R.id.imageViewVendorProfile);
        mTextViewVendorName = (TextView) view.findViewById(R.id.textViewVendorName);
        mTextViewVendorDescription = (TextView) view.findViewById(R.id.textViewVendorDetails);
        mTextViewVendorAddress = (TextView) view.findViewById(R.id.textViewVendorAddress);
        mLayoutProductList = (FrameLayout) view.findViewById(R.id.containerRecyclerVendorProductItemList);
        mLayoutContainer = (RelativeLayout) view.findViewById(R.id.layoutConsumerVendorProfile);
        mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        getVendorInfo();
        getChildRecyclerVendorProductItems();
        mGoogleApiClient.connect();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewAnimator.animate(mLayoutContainer)
                .slideBottom()
                .duration(300)
                .descelerate()
                .start();
    }

    void getChildRecyclerVendorProductItems() {
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerRecyclerVendorProductItemList, FragmentUtil.getRecyclerVendorProfileProductItemFragment())
                .commit();
    }


    public void getVendorInfo() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mVendorLogo = (String) dataSnapshot.child("logo").getValue();
                mVendorName = (String) dataSnapshot.child("name").getValue();
                mVendorDescription = (String) dataSnapshot.child("description").getValue();
                mVendorAddress = (String) dataSnapshot.child("address").getValue();

                Glide.with(mContext)
                        .load(mVendorLogo)
                        .centerCrop()
                        .into(mImageViewVendorProfile);
                mTextViewVendorName.setText(mVendorName);
                mTextViewVendorDescription.setText(mVendorDescription);
                mTextViewVendorAddress.setText(mVendorAddress);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage((FragmentActivity) getActivity());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("Map Ready", TAG);
        mMap = googleMap;
        mapReady = true;
        MapStyleOptions styleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.map_style);
        mCameraPosition = CameraPosition.builder().target(mLatLng).zoom(14).build();
        if (mCameraPosition != null){
            mMap.addMarker(new MarkerOptions().position(mLatLng).title(mVendorName));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
            mMap.setMapStyle(styleOptions);

        }

    }

//    private void checkPermissions(){
//        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//
//    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        MapFragment mapFragment = new MapFragment();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.containerMapFragment, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
