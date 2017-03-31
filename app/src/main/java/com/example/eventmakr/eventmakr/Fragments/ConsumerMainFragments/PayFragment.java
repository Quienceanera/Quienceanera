package com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.BuildConfig;
import io.fabric.sdk.android.Fabric;

public class PayFragment extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private TextView mTextViewTotal;
    private CardView mLayoutCreditCardForm;
    private String mTokenString;
    private Token mToken;
    public static final String PUBLISHABLE_KEY = "pk_test_meDPIZOcJTWc3P854aImTlNo";
    public static final String SECRET_KEY = "sk_test_XRxN5L1wiHwLMOX3EiUYoiL9";
    private static final int LOAD_MASKED_WALLET_REQUEST_CODE = 1000;
    private static final int LOAD_FULL_WALLET_REQUEST_CODE = 1001;
    private SupportWalletFragment walletFragment;
    private GoogleApiClient mGoogleApiClient;
    private IsReadyToPayRequest isReadyToPayRequest = IsReadyToPayRequest.newBuilder().build();
    public static final int mEnvironment = WalletConstants.ENVIRONMENT_TEST;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this);
        com.stripe.Stripe.apiKey = PUBLISHABLE_KEY;

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        mTextViewTotal = (TextView) findViewById(R.id.textViewPayTotal);
        mLayoutCreditCardForm = (CardView) findViewById(R.id.layoutCreditCardForm);
        mTextViewTotal.setText("Order Total:"+" $"+CartHomeAdapter.mTotalPrice);
        Log.i("totalprice", CartHomeAdapter.mTotalPrice);
        return super.onCreateView(parent, name, context, attrs);
    }

    public void showCreditCardForm(View view){
        mLayoutCreditCardForm.setVisibility(View.VISIBLE);
    }

    void googleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wallet.API, new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .setTheme(WalletConstants.THEME_LIGHT)
                        .build())
                .build();
    }

    void isReadyToPay(){
        Wallet.Payments.isReadyToPay(mGoogleApiClient, isReadyToPayRequest).setResultCallback(
                new ResultCallback<BooleanResult>() {
                    @Override
                    public void onResult(@NonNull BooleanResult booleanResult) {
                        if (booleanResult.getStatus().isSuccess()) {
                            if (booleanResult.getValue()) {
                                showAndroidPay();
                            } else {
                                // Hide Android Pay buttons, show a message that Android Pay
                                // cannot be used yet, and display a traditional checkout button
                            }
                        } else {
                            // Error making isReadyToPay call
                            Log.i("isReadyToPay:", booleanResult.getStatus().toString());
                        }
                    }
                }
        );
    }

    public void showAndroidPay() {
        walletFragment =
                (SupportWalletFragment) getSupportFragmentManager().findFragmentById(R.id.wallet_fragment);
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
                .setEstimatedTotalPrice(CartHomeAdapter.mTotalPrice)
                .setCurrencyCode("USD")
                .build();

        // Set the parameters:
        WalletFragmentInitParams initParams = WalletFragmentInitParams.newBuilder()
                .setMaskedWalletRequest(maskedWalletRequest)
                .setMaskedWalletRequestCode(LOAD_MASKED_WALLET_REQUEST_CODE)
                .build();

        // Initialize the fragment:
        walletFragment.initialize(initParams);

    }

    public void submitCard(View view) {
        // TODO: replace with your own test key
        final String publishableApiKey = BuildConfig.DEBUG ?
                SECRET_KEY :
                PUBLISHABLE_KEY;

        TextView cardNumberField = (TextView) findViewById(R.id.cardNumber);
        TextView monthField = (TextView) findViewById(R.id.month);
        TextView yearField = (TextView) findViewById(R.id.year);
        TextView cvcField = (TextView) findViewById(R.id.cvc);

        Card card = new Card(cardNumberField.getText().toString(),
                Integer.valueOf(monthField.getText().toString()),
                Integer.valueOf(yearField.getText().toString()),
                cvcField.getText().toString());

        final Stripe stripe = new Stripe();
        stripe.createToken(
                card,
                SECRET_KEY,
                new TokenCallback() {

                    public void onSuccess(Token token) {
                        // TODO: Send Token information to your backend to initiate a charge
                        Toast.makeText(
                                getApplicationContext(),
                                "Token created: " + token.getId(),
                                Toast.LENGTH_LONG).show();
                        mToken = token;
                        mTokenString = token.getId();
                        new stripeTask().execute();
                    }

                    public void onError(Exception error) {
                        Log.d("Stripe", error.getLocalizedMessage());
                    }
                });



    }
    class stripeTask extends AsyncTask<Token, Integer, String> {

        @Override
        protected String doInBackground(Token... params) {
            Log.i("AsyncTask", mTokenString);
            // Charge the user's card:
            final Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("amount", CartHomeAdapter.mTotalPrice);
            cardParams.put("currency", "usd");
            cardParams.put("description", CartHomeAdapter.mVendorUid);
            cardParams.put("source", mTokenString);

            try {
                Charge charge = Charge.create(cardParams);
                Log.i("Charge", charge.getStatus());

            } catch (AuthenticationException e) {
                e.printStackTrace();
            } catch (InvalidRequestException e) {
                e.printStackTrace();
            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (CardException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOAD_MASKED_WALLET_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                MaskedWallet maskedWallet = data.getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET);
                FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                        .setCart(Cart.newBuilder()
                                .setCurrencyCode("USD")
                                .setTotalPrice(CartHomeAdapter.mTotalPrice)
                                .build())
                        .setGoogleTransactionId(maskedWallet.getGoogleTransactionId())
                        .build();
                Wallet.Payments.loadFullWallet(mGoogleApiClient, fullWalletRequest, LOAD_FULL_WALLET_REQUEST_CODE);
            }
        } else if (requestCode == LOAD_FULL_WALLET_REQUEST_CODE) {
            // Unique, identifying constant
            if (resultCode == Activity.RESULT_OK) {
                FullWallet fullWallet = data.getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET);
                String tokenJSON = fullWallet.getPaymentMethodToken().getToken();
                //A token will only be returned in production mode,
                //i.e. WalletConstants.ENVIRONMENT_PRODUCTION
                if (mEnvironment == WalletConstants.ENVIRONMENT_PRODUCTION) {
                    // TODO: send token to your server
                    mTokenString = tokenJSON;
                    new stripeTask().execute();

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//    void getCartDetailFragment(){
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.containerPayDetails, FragmentUtil.getCartDetailFragment())
//                .commit();
//
//    }
//
//    void getCartListFragment(){
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.containerPay, FragmentUtil.getOrderListItemFragment())
//                .commit();
//    }

    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    public void onStop() {
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
