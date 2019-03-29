package com.example.christien.item_identifier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseMethods {

    private final static String TAG = "FireBaseMethods";

    //firebase
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userID;
    private Boolean exists;

    private Context context;

    public FireBaseMethods(Context enteredContext){

        context = enteredContext;
        auth = FirebaseAuth.getInstance();

    }

    public void setUserID(String userID){
        this.userID = userID;
    }

    /*
    * @param fullname
    * @param email
    * @param username
    * @param password
    */
    public void registerUser(final String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            String userID = auth.getCurrentUser().getUid();
                            Toast.makeText(context, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }






}
