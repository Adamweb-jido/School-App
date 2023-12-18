package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {


    TextInputEditText fName, lName, email, newPassword, cPassword;
    MaterialButton nextBtn;
    TextView login;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nextBtn = findViewById(R.id.next);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        newPassword = findViewById(R.id.createPassword);
        cPassword = findViewById(R.id.confirmPassword);
        progressBar = findViewById(R.id.firstSignUpBar);
        firebaseAuth = FirebaseAuth.getInstance();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String firstName, lastName, emailAddress, password, confirmPassword;
             firstName = String.valueOf(fName.getText());
             lastName = String.valueOf(lName.getText());
             emailAddress = String.valueOf(email.getText());
             password = String.valueOf(newPassword.getText());
             confirmPassword = String.valueOf(cPassword.getText());

             if (TextUtils.isEmpty(firstName)){
                 Toast.makeText(SignUpActivity.this, "First Name is empty", Toast.LENGTH_LONG).show();
                 fName.setError("Please you should fill the field");
             }
             else if(TextUtils.isEmpty(lastName)){
                    Toast.makeText(SignUpActivity.this, "Last Name is empty", Toast.LENGTH_LONG).show();
                    lName.setError("Please you should fill the field");
                }
             else if(TextUtils.isEmpty(emailAddress)){
                 Toast.makeText(SignUpActivity.this, "Email Address is empty", Toast.LENGTH_LONG).show();
                 email.setError("Please you should fill the field");
             }
             else if(TextUtils.isEmpty(password)){
                 Toast.makeText(SignUpActivity.this, "Password is empty", Toast.LENGTH_LONG).show();
                 newPassword.setError("Please you should fill the field");
                 newPassword.setTextInputLayoutFocusedRectEnabled(true);

             }
             else if(TextUtils.isEmpty(confirmPassword)){
                 Toast.makeText(SignUpActivity.this, "Please confirm the password first", Toast.LENGTH_LONG).show();
                cPassword.setError("Please you should fill the field");
             } else {
                 Toast.makeText(SignUpActivity.this, "you have successfully registered", Toast.LENGTH_LONG).show();
             }
            }
        });

        login = findViewById(R.id.loginLink);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}