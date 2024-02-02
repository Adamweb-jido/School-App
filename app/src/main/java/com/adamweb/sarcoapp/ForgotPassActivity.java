package com.adamweb.sarcoapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class ForgotPassActivity extends AppCompatActivity {

    TextView login;
    MaterialButton submit;
    TextInputEditText forgotField;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        login = findViewById(R.id.loginLink);
        submit = findViewById(R.id.submit);
        forgotField = findViewById(R.id.forgotField);
        progressBar = findViewById(R.id.forgotProgressBar);


        submit.setOnClickListener(view -> {

            String emailAddress;
            emailAddress = String.valueOf(forgotField.getText());

            if (TextUtils.isEmpty(emailAddress)){
                Toast.makeText(this, "Please Enter your Email Address", Toast.LENGTH_SHORT).show();
                forgotField.setError("Enter your Email Address First.");
                forgotField.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                submit.setError("Invalid EMail Address");
                submit.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                sendCodeToEmail(emailAddress);
            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void sendCodeToEmail(String emailAddress) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(ForgotPassActivity.this, "Code Is Successfully sent.", Toast.LENGTH_SHORT).show();
                gotoNextPage();
            } else {
                try {
                    throw task.getException();
                } catch(FirebaseAuthInvalidUserException e){
                    forgotField.setError("This Email Address doe not exist");
                    forgotField.requestFocus();
                } catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(ForgotPassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void gotoNextPage() {
        startActivity(new Intent(getApplicationContext(), NextForgotPassActivity.class));
        finish();
    }
}