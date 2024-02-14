package com.adamweb.sarcoapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class ForgotPassActivity extends AppCompatActivity {

    TextView login;
    MaterialButton submit, gotoEmailBtn, exitAppBtn;
    TextInputEditText forgotField;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        login = findViewById(R.id.loginLink);
        submit = findViewById(R.id.submit);
        forgotField = findViewById(R.id.forgotField);
        progressBar = findViewById(R.id.forgotProgressBar);

        dialog = new Dialog(ForgotPassActivity.this);
        dialog.setContentView(R.layout.code_sent_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        gotoEmailBtn = dialog.findViewById(R.id.emailBtn);
        exitAppBtn = dialog.findViewById(R.id.exitBtn);

        gotoEmailBtn.setOnClickListener(v -> {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
            if (intent != null){
                startActivity(intent);
            } else {
                Toast.makeText(ForgotPassActivity.this, "App not Installed On your Phone", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    exitAppBtn.setOnClickListener(v ->{
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    });

        submit.setOnClickListener(view -> {

            String emailAddress;
            emailAddress = String.valueOf(forgotField.getText());

            if (TextUtils.isEmpty(emailAddress)){
                Toast.makeText(this, "Please Enter your Email Address", Toast.LENGTH_SHORT).show();
                forgotField.setError("Enter your Email Address First.");
                forgotField.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                forgotField.setError("Invalid EMail Address");
                forgotField.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                sendCodeToEmail(emailAddress);
            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSwipeLeft(this);
        });
    }

    private void sendCodeToEmail(String emailAddress) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(ForgotPassActivity.this, "Code Is Successfully sent.", Toast.LENGTH_SHORT).show();
                dialog.show();
            } else {
                try {
                    throw task.getException();
                } catch(FirebaseAuthInvalidUserException e){
                    forgotField.setError("This Email Address doe not exist, please register");
                    forgotField.requestFocus();
                } catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(ForgotPassActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            progressBar.setVisibility(View.INVISIBLE);
        });
    }
}