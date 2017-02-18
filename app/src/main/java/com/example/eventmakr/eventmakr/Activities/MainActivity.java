package com.example.eventmakr.eventmakr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventmakr.eventmakr.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private CardView mButtonPlanning, mButtonLogIn;
    private TextView mTextViewVendor;

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        mButtonPlanning = (CardView) findViewById(R.id.buttonPlanning);
        mButtonLogIn = (CardView) findViewById(R.id.buttonLogIn);
        mTextViewVendor = (TextView)  findViewById(R.id.textView_imAVendor);

        mButtonPlanning.setOnClickListener(this);
        mButtonLogIn.setOnClickListener(this);
        mTextViewVendor.setOnClickListener(this);

        if (mFirebaseUser == null) {
            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show();
        } else {
            mButtonLogIn.setVisibility(View.GONE);
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonPlanning:
                Intent consumerIntent = new Intent(MainActivity.this, ConsumerActivity.class);
                startActivity(consumerIntent);
                break;
            case R.id.buttonLogIn:
                Intent signInIntent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signInIntent);
                break;
            case R.id.textView_imAVendor:
                Intent vendorIntent = new Intent(MainActivity.this, VendorActivity.class);
                startActivity(vendorIntent);
                break;
            default:
        }
    }
}
