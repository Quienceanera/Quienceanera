package com.example.eventmakr.eventmakr.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eventmakr.eventmakr.Adapters.CartHomeAdapter;
import com.example.eventmakr.eventmakr.Adapters.ConsumerViewPagerAdapter;
import com.example.eventmakr.eventmakr.Fragments.ConsumerFragments.ContactVendorFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.CartFragment;
import com.example.eventmakr.eventmakr.Fragments.ConsumerMainFragments.ChatFragment;
import com.example.eventmakr.eventmakr.R;
import com.example.eventmakr.eventmakr.Utils.FragmentUtil;
import com.github.florent37.viewanimator.ViewAnimator;
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
import java.util.Objects;

import io.fabric.sdk.android.BuildConfig;
import io.fabric.sdk.android.Fabric;

public class PayActivity extends AppCompatActivity {
    private static final String TAG = PayActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Token mToken;
    private String mTokenString;
    private ConsumerViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mLayoutConfirm, mLayoutChat, mLayoutCheckOut;
    private FrameLayout mLayoutPayDetails;
    private Context mContext;
    private CoordinatorLayout mLayoutPayActivity;
    private String mVendorUid, mTotalPrice, mVendorName, mVendorLogo, mReady;

    public static final String PUBLISHABLE_KEY = "sk_test_CPGPeiE1paVb2CtLwczM4sAC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG);
        Fabric.with(this);
        setContentView(R.layout.activity_pay);
        com.stripe.Stripe.apiKey = PUBLISHABLE_KEY;

        postponeEnterTransition();

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            Log.i(TAG, "extras are null");

        } else {
            mVendorUid = extras.getString("VendorUid");
            mTotalPrice = extras.getString("TotalPrice");
            mVendorName = extras.getString("VendorName");
            mVendorLogo = extras.getString("VendorLogo");
            mReady = extras.getString("Ready");
            Log.i(TAG, extras.toString());
        }
        getEnterAnimation();

        Slide slide = new Slide(Gravity.RIGHT);
        getWindow().setEnterTransition(slide);
        getWindow().setReturnTransition(slide);

        VendorActivity.mVendorMode = false;
        mToolbar = (Toolbar) findViewById(R.id.toolbarPay);
        setActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayActivity.this, ConsumerActivity.class));
            }
        });
        mLayoutChat = (LinearLayout) findViewById(R.id.layoutChatBanner);
        mLayoutCheckOut = (LinearLayout) findViewById(R.id.layoutCheckOutBanner);
        mLayoutConfirm = (LinearLayout) findViewById(R.id.layoutConfirmBanner);
        mLayoutPayDetails = (FrameLayout) findViewById(R.id.containerPayDetails);
        mLayoutPayActivity = (CoordinatorLayout) findViewById(R.id.layoutPayActivity);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayoutPay);
        mViewPager = (ViewPager) findViewById(R.id.viewpagerPay);
        mViewPagerAdapter = new ConsumerViewPagerAdapter(getFragmentManager(), this);
//        Log.i(TAG+"1", mReady);
        if (Objects.equals(mReady, "true")){
            Log.i(TAG+"2", mReady);
            mViewPagerAdapter.addFragments(new CartFragment(), "");
            mViewPagerAdapter.addFragments(new ChatFragment(),"");
            mLayoutCheckOut.setVisibility(View.VISIBLE);
        } else{
            mViewPagerAdapter.addFragments(new ContactVendorFragment(), "");
            mLayoutConfirm.setVisibility(View.VISIBLE);
        }

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0 && Objects.equals(CartHomeAdapter.mConfirm, "true")){
                    mLayoutConfirm.setVisibility(View.VISIBLE);
                    mLayoutChat.setVisibility(View.GONE);
                    mLayoutCheckOut.setVisibility(View.GONE);
                }
                if (tab.getPosition() == 0){
                    mLayoutConfirm.setVisibility(View.GONE);
                    mLayoutChat.setVisibility(View.GONE);
                    mLayoutCheckOut.setVisibility(View.VISIBLE);
                }
                if (tab.getPosition() == 1){
                    mLayoutConfirm.setVisibility(View.GONE);
                    mLayoutChat.setVisibility(View.VISIBLE);
                    mLayoutCheckOut.setVisibility(View.GONE);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getCartDetailFragment();
    }

    public void submitCard(View view) {
        // TODO: replace with your own test key
        final String publishableApiKey = BuildConfig.DEBUG ?
                "pk_test_7DGPxpaErrh1UKczUD58zGe5" :
                "pk_test_7DGPxpaErrh1UKczUD58zGe5";

        TextView cardNumberField = (TextView) findViewById(R.id.cardNumber);
        TextView monthField = (TextView) findViewById(R.id.month);
        TextView yearField = (TextView) findViewById(R.id.year);
        TextView cvcField = (TextView) findViewById(R.id.cvc);

        Card card = new Card(cardNumberField.getText().toString(),
                Integer.valueOf(monthField.getText().toString()),
                Integer.valueOf(yearField.getText().toString()),
                cvcField.getText().toString());

        Stripe stripe = new Stripe();
        stripe.createToken(card, publishableApiKey, new TokenCallback() {
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
            final Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("amount", 50);
            cardParams.put("currency", "usd");
            cardParams.put("description", CartHomeAdapter.mVendorUid);
            cardParams.put("source", mTokenString);

            try {
                Charge charge = Charge.create(cardParams);
                Log.i("Charge", charge.getStatus());

            } catch (AuthenticationException e) {
                e.printStackTrace();
            } catch (APIException e) {
                e.printStackTrace();
            } catch (InvalidRequestException e) {
                e.printStackTrace();
            } catch (CardException e) {
                e.printStackTrace();
            } catch (APIConnectionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private void getEnterAnimation() {
        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.setDuration(200);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ViewAnimator.animate(mTabLayout,mLayoutConfirm, mLayoutCheckOut, mLayoutChat)
                        .fadeIn()
                        .duration(800)
                        .start();
                transition.removeListener(this);
//                for (int i = 0; i < mLayoutPayActivity.getChildCount(); i++){
//                    View child = mLayoutPayActivity.getChildAt(i);
//                    child.animate().setStartDelay(100 + i * 500)
//                            .setInterpolator(new AccelerateInterpolator())
//                            .alpha(1)
//                            .scaleX(1)
//                            .scaleY(1);
//                }

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        scheduleStartPostponedTransition(findViewById(R.id.imageViewCartDetail));
    }

    private void scheduleStartPostponedTransition(final View sharedElement){
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                }
        );
    }

    void getCartDetailFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("VendorUid", mVendorUid);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerPayDetails, FragmentUtil.getCartDetailFragment(bundle))
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        supportFinishAfterTransition();
//        if (VendorActivity.mVendorMode && !ConsumerActivity.mConsumerMode && EventsAdapter.mEventKey == null){
//            supportFinishAfterTransition();
//            startActivity(new Intent(PayActivity.this, VendorActivity.class));
//        }else{
//            supportFinishAfterTransition();
//            startActivity(new Intent(PayActivity.this, ConsumerActivity.class));
//        }
    }
}
