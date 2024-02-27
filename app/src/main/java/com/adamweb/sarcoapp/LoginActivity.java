package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    MaterialButton registerBtn, loginBtn;
    TextView forgetPass;
    TextInputEditText loginEmail, loginPassword;
    Dialog progressDialog;
    FirebaseAuth userLogin;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerBtn = findViewById(R.id.myRegBtn);
        loginBtn = findViewById(R.id.myLoginBtn);
        forgetPass = findViewById(R.id.forgetPassword);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        userLogin = FirebaseAuth.getInstance();

        progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.progress_bar_dialog);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setCancelable(false);

        CounterUtil.incrementVisitCount(LoginActivity.this);

        registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
            Animatoo.INSTANCE.animateSwipeLeft(this);
        });

        forgetPass.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
            startActivity(intent);
            finish();
            Animatoo.INSTANCE.animateSwipeLeft(this);
        });


        loginBtn.setOnClickListener(view -> {
          String emailAddress, password;
          emailAddress = String.valueOf(loginEmail.getText());
          password = String.valueOf(loginPassword.getText());

          if (TextUtils.isEmpty(emailAddress)){
              Toast.makeText(LoginActivity.this, "Email Address is Empty", Toast.LENGTH_LONG).show();
              loginEmail.setError("Please fill the email");
              loginEmail.requestFocus();
          } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
              Toast.makeText(LoginActivity.this, "Email Address is Invalid", Toast.LENGTH_LONG).show();
              loginEmail.setError("Please enter valid email");
              loginEmail.requestFocus();
          } else if (TextUtils.isEmpty(password)){
              Toast.makeText(LoginActivity.this, "Password is Empty", Toast.LENGTH_LONG).show();
              loginPassword.setError("Please fill the password");
              loginPassword.requestFocus();
          } else {
              progressDialog.show();
              userLogin(emailAddress, password);
          }
        });
    }

    private void userLogin(String userEmail, String userPassword) {
        userLogin.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                   startActivity(intent);
                   finish();
                   Animatoo.INSTANCE.animateSwipeRight(LoginActivity.this);
               } else {
                   try {
                       throw task.getException();
                   } catch (FirebaseAuthInvalidUserException e){
                       loginEmail.setError("Invalid email or user does not exit, check and enter valid credentials");
                       loginEmail.requestFocus();
                   } catch (FirebaseAuthUserCollisionException e){
                       loginEmail.setError("Incorrect email or password,please check it and try again");
                       loginEmail.requestFocus();
                   } catch (Exception e){
                       Log.e(TAG, e.getMessage());
                       Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                   }

               }
               progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishActivity(1);
        System.exit(1);

    }



    @Override
    protected void onStart() {
        super.onStart();
        if (userLogin.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Please Log in", Toast.LENGTH_SHORT).show();
        }
    } 

}