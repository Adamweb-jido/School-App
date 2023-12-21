package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {


    TextInputEditText fName, lName, email, newPassword, cPassword;
    TextInputLayout firstNameLayout;
    MaterialButton nextBtn;
    TextView login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nextBtn = findViewById(R.id.next);
        login = findViewById(R.id.loginLink);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailAddress);
        newPassword = findViewById(R.id.createPassword);
        cPassword = findViewById(R.id.confirmPassword);
        firstNameLayout = findViewById(R.id.fistNameLayout);
        //firebaseAuth = FirebaseAuth.getInstance();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName, lastName, emailAddress, password, confirmPassword;
                firstName = String.valueOf(fName.getText());
                lastName = String.valueOf(lName.getText());
                emailAddress = String.valueOf(email.getText());
                password = String.valueOf(newPassword.getText());
                confirmPassword = String.valueOf(cPassword.getText());

                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(SignUpActivity.this, "First Name is empty", Toast.LENGTH_LONG).show();
                    firstNameLayout.setError("Please write your name");
                    return;
                } else if (TextUtils.isEmpty(lastName)) {
                    Toast.makeText(SignUpActivity.this, "Last Name is empty", Toast.LENGTH_LONG).show();
                    lName.setError("Please you should fill the field");
                    lName.requestFocus();
                } else if (TextUtils.isEmpty(emailAddress)) {
                    Toast.makeText(SignUpActivity.this, "Email Address is empty", Toast.LENGTH_LONG).show();
                    email.setError("Please input email address");
                    email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                    Toast.makeText(SignUpActivity.this, "Invalid Email Address", Toast.LENGTH_LONG).show();
                    email.setError("Please enter valid email");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Password is empty", Toast.LENGTH_LONG).show();
                    newPassword.setError("Please you should fill the field");
                    newPassword.setTextInputLayoutFocusedRectEnabled(true);
                    newPassword.requestFocus();
                } else if (password.length() < 6) {
                    Toast.makeText(SignUpActivity.this, "Password is less than 6 digit", Toast.LENGTH_LONG).show();
                    newPassword.setError("set required password");
                    newPassword.setTextInputLayoutFocusedRectEnabled(true);
                    newPassword.requestFocus();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Password miss match!", Toast.LENGTH_LONG).show();
                    cPassword.setError("fill the password correctly");
                } else {
                    Toast.makeText(getApplicationContext(), "Registration is ongoing......", Toast.LENGTH_LONG).show();
                    progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.setTitle("Registration");
                    progressDialog.setMessage("Please wait while creating your account");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    //myFirebaseAuthFunction();
                }
            }
        });


    }
}