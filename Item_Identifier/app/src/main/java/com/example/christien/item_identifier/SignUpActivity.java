package com.example.christien.item_identifier;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {


    private final static String TAG = "SignUpScreen";


    /*XML variables*/
    private EditText sEmail;
    private EditText sPassword;
    private Button sSignUpButton;

    /*FireBase*/
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FireBaseMethods fireBaseMethods;
    private FirebaseUser user;



    /*Usesr Data*/
    String email;
    String password;


    /*Regex expressions
     * Email: basic email format ~  ______@____.com || _____@__.ca || ______@awefaw.co.uk || etc...
     * password: at least 1 lowerCase, 1 upperCase, 1 Numeric, 1 special Character, at least 8 chars long
     * */
    Pattern pEmail = Pattern.compile("^((\"[\\w-\\s]+\")|([\\w-]+(?:\\.[\\w-]+)*)|(\"[\\w-\\s]+\")([\\w-]+(?:\\.[\\w-]+)*))(@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$)|(@\\[?((25[0-5]\\.|2[0-4][0-9]\\.|1[0-9]{2}\\.|[0-9]{1,2}\\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\\]?$)");
    Pattern pPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        //Initiate XML variables
        sEmail = (EditText) findViewById(R.id.Email);
        sPassword = (EditText) findViewById(R.id.Password);
        sSignUpButton = (Button) findViewById(R.id.SignUpButton);

        fireBaseMethods = new FireBaseMethods(SignUpActivity.this);



        sSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = sEmail.getText().toString().trim();
                password = sPassword.getText().toString().trim();

                //If all fields for signup are valid
                if(checkFields() == true){

                    if(sEmail.getError() == null && sPassword.getError() == null ){
                        fireBaseMethods.registerUser(email, password);
                        Log.d(TAG, "checkFields: account created");
                        Toast.makeText(SignUpActivity.this, "Success: Account created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }


                }


            }
        });

    }



    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Return to Login Screen?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Ends activity
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        // Closes application
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }




    private Boolean checkFields(){

        Boolean valid = true;


        ///////////EMAIL FIELD////////////
        String emailField = sEmail.getText().toString();

        // Check for empty email Field
        if (TextUtils.isEmpty(emailField)) {
            sEmail.setError(getString(R.string.empty_email));
            valid = false;
        }

        //check if username follows correct format
        if (!TextUtils.isEmpty(emailField)) {
            Matcher mEmail= pEmail.matcher(emailField);
            if(!mEmail.find()) {
                sEmail.setError(getString(R.string.invalid_email));
                valid = false;
            }
        }

        ///////////PASSWORD FIELD////////////
        String passwordField = sPassword.getText().toString();

        // Check for empty password Field
        if (TextUtils.isEmpty(passwordField)) {
            sPassword.setError(getString(R.string.empty_password));
            valid = false;
        }
        //check if username follows correct format
        if (!TextUtils.isEmpty(passwordField)) {
            Matcher mPassword= pPassword.matcher(passwordField);
            if(!mPassword.find()) {
                sPassword.setError(getString(R.string.invalid_password));
                valid = false;
            }
        }

        return valid;

    }
}
