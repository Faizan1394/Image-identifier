package com.example.christien.item_identifier;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "Loading";

    //Constant Integer variables
    private static final int loadingTimer = 3000;

    //firebase values
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        firebaseSetup();
    }

    private void firebaseSetup(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    Log.d(TAG, "Auth State Listener:signed Out ");
                    /*Send our user to the Login Screen*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, loadingTimer);
                }else{
                    Log.d(TAG, "Auth State Listener:signed In: " + user.getUid());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent homePageIntent = new Intent(LoadingActivity.this, HomeActivity.class);
                            homePageIntent.putExtra("userID", user.getUid());
                            startActivity(homePageIntent);
                            finish();
                        }
                    }, loadingTimer);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }

}
