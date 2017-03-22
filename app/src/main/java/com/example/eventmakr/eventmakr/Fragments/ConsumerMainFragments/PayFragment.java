package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.eventmakr.eventmakr.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentMode;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

public class PayFragment extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private SupportWalletFragment mWalletFragment;
    private MaskedWallet mMaskedWallet;
    private FullWallet mFullWallet;
    private GoogleApiClient mGoogleApiClient;
    private IsReadyToPayRequest isReadyToPayRequest = IsReadyToPayRequest.newBuilder().build();
    public static final int MASKED_WALLET_REQUEST_CODE = 888;
    public static final int FULL_WALLET_REQUEST_CODE = 889;
    public static final String WALLET_FRAGMENT_ID = "wallet_fragment";
    public static final String PUBLISHABLE_KEY = "sk_live_0KVEt9tZb8RBI62I7YJBg4JL";
    public static final int mEnviroment = WalletConstants.ENVIRONMENT_TEST;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        Wallet.Payments.isReadyToPay(mGoogleApiClient, isReadyToPayRequest).setResultCallback(new ResultCallback<BooleanResult>() {
//            @Override
//            public void onResult(@NonNull BooleanResult booleanResult) {
//                if (booleanResult.getStatus().isSuccess()) {
//                    if (booleanResult.getValue()) {
//                        showAndroidPay();
//                        Log.i("Is ready to pay", "true");
//                    } else {
//                        //hide android pay buttons and show traditional button
//                        Log.i("Is ready to pay", "false");
//
//                    }
//                } else {
//                    Log.e("IsReadyToPay", booleanResult.getStatus().toString());
//                }
//            }
//        });
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .enableAutoManage(this, 0, this)
                .addApi(Wallet.API, new Wallet.WalletOptions.Builder()
                        .setEnvironment(mEnviroment)
                        .setTheme(WalletConstants.THEME_LIGHT)
                        .build())
                .build();
    }

    public void showAndroidPay() {
                setContentView(R.layout.pay_fragment);

        mWalletFragment = (SupportWalletFragment) getSupportFragmentManager()
                .findFragmentByTag(WALLET_FRAGMENT_ID);

        MaskedWalletRequest maskedWalletRequest = MaskedWalletRequest.newBuilder()
                // Request credit card tokenization with Stripe by specifying tokenization parameters:
                .setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters.newBuilder()
                        .setPaymentMethodTokenizationType(PaymentMethodTokenizationType.PAYMENT_GATEWAY)
                        .addParameter("gateway", "stripe")
                        .addParameter("stripe:publishableKey", PUBLISHABLE_KEY)
                        .addParameter("stripe:version", com.stripe.android.BuildConfig.VERSION_NAME)
                        .build())
                // You want the shipping address:
                .setShippingAddressRequired(true)
                // Price set as a decimal:
                .setEstimatedTotalPrice("20.00")
                .setCurrencyCode("USD")
                .build();

        if (mWalletFragment == null) {

            //Wallet Fragment style
            WalletFragmentStyle walletFragmentStyle = new WalletFragmentStyle()
                    .setBuyButtonText(WalletFragmentStyle.BuyButtonText.LOGO_ONLY)
                    .setBuyButtonWidth(WalletFragmentStyle.Dimension.MATCH_PARENT);

            // Wallet Fragment Options
            WalletFragmentOptions walletFragmentOptions = WalletFragmentOptions.newBuilder()
                    .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                    .setFragmentStyle(walletFragmentStyle)
                    .setTheme(WalletConstants.THEME_LIGHT)
                    .setMode(WalletFragmentMode.BUY_BUTTON)
                    .build();
            mWalletFragment = SupportWalletFragment.newInstance(walletFragmentOptions);

            // Initialize the Wallet Fragment
            WalletFragmentInitParams.Builder startParamsBuilder = WalletFragmentInitParams.newBuilder()
                    .setMaskedWalletRequest(maskedWalletRequest)
                    .setMaskedWalletRequestCode(MASKED_WALLET_REQUEST_CODE)
                    .setAccountName("Quieneaneras");
            mWalletFragment.initialize(startParamsBuilder.build());

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.wallet_button_holder, mWalletFragment)
                    .commit();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MASKED_WALLET_REQUEST_CODE) { // Unique, identifying constant
            if (resultCode == Activity.RESULT_OK) {
                MaskedWallet maskedWallet = data.getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET);
                FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                        .setGoogleTransactionId(maskedWallet.getGoogleTransactionId())
                        .setCart(Cart.newBuilder()
                                .setCurrencyCode("USD")
                                .setTotalPrice("20.00")
                                .addLineItem(LineItem.newBuilder() // Identify item being purchased
                                        .setCurrencyCode("USD")
                                        .setQuantity("1")
                                        .setDescription("Premium Llama Food")
                                        .setTotalPrice("20.00")
                                        .setUnitPrice("20.00")
                                        .build())
                                .build())
                        .build();
                Wallet.Payments.loadFullWallet(mGoogleApiClient, fullWalletRequest, FULL_WALLET_REQUEST_CODE);
            }
        } else if (requestCode == FULL_WALLET_REQUEST_CODE) {
            // Unique, identifying constant
            if (resultCode == Activity.RESULT_OK) {
                mFullWallet = data.getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET);
                String tokenJSON = mFullWallet.getPaymentMethodToken().getToken();

                //A token will only be returned in production mode,
                //i.e. WalletConstants.ENVIRONMENT_PRODUCTION
//                if (mEnviroment == WalletConstants.ENVIRONMENT_PRODUCTION) {
//                    try {
//                        Token token = TokenParser.parseToken(tokenJSON);
//                        // send token to your server
//                        RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder(PUBLISHABLE_KEY)).build();
//                        Map chargeMap = new HashMap();
//                        chargeMap.put("amount", 100);
//                        chargeMap.put("currency", "usd");
//                        chargeMap.put("source", token);
////                        try {
////                             charge = Charge.create(chargeMap, requestOptions);
////                            Log.i("Request Options", chargeMap.toString());
////                        } catch (StripeException e) {
////                            e.printStackTrace();
////                        }
//                    } catch (JSONException jsonException) {
//                        Log.i("JsonException", jsonException.toString());
//                    }
//                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
