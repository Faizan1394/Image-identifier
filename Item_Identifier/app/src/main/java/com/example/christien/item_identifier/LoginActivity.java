package com.example.christien.item_identifier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView signUp;
    private ProgressDialog progressDialog;


    //Firebase AUTH
    private FirebaseAuth firebaseAuth;

    /*Usesr Data*/
    String emailText;
    String passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUp = (TextView) findViewById(R.id.signupView);
        progressDialog = new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SEND TO REGISTRATION PAGE
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login to homepage

                emailText = email.getText().toString().trim();
                passwordText = password.getText().toString().trim();

                if(checkFields() == true){

                    firebaseAuth.signInWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "User Logged In.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }
            }
        });


    }

    private Boolean checkFields(){

        Boolean valid = true;


        ///////////EMAIL FIELD////////////
        String emailField = email.getText().toString();

        // Check for empty email Field
        if (TextUtils.isEmpty(emailField)) {
            email.setError(getString(R.string.empty_email));
            valid = false;
        }



        ///////////PASSWORD FIELD////////////
        String passwordField = password.getText().toString();

        // Check for empty password Field
        if (TextUtils.isEmpty(passwordField)) {
            password.setError(getString(R.string.empty_password));
            valid = false;
        }

        return valid;

    }
}
