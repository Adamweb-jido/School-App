package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

import kotlin.io.FileTreeWalk;

public class SignUpActivity extends AppCompatActivity {


    TextInputEditText fName, lName, email, newPassword, cPassword, admNumber, phoneNumber;
    MaterialButton nextBtn;
    TextView login;
    ProgressDialog progressDialog;
    private static final String TAG = "SignUpActivity";

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
        admNumber = findViewById(R.id.urAdmNumber);
        phoneNumber = findViewById(R.id.urPhoneNumber);

        nextBtn.setOnClickListener(view -> {
            String firstName, lastName, emailAddress, password, confirmPassword, admissionNo, phoneNo;
            firstName = String.valueOf(fName.getText());
            lastName = String.valueOf(lName.getText());
            emailAddress = String.valueOf(email.getText());
            password = String.valueOf(newPassword.getText());
            confirmPassword = String.valueOf(cPassword.getText());
            admissionNo = String.valueOf(admNumber.getText());
            phoneNo = String.valueOf(admNumber.getText());

            if (TextUtils.isEmpty(firstName)) {
                Toast.makeText(SignUpActivity.this, "First Name is empty", Toast.LENGTH_LONG).show();
                fName.setError("Please you should fill the field");
                fName.requestFocus();
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
            } else if (TextUtils.isEmpty(admissionNo)){
                Toast.makeText(SignUpActivity.this, "Admission Number is Empty", Toast.LENGTH_LONG).show();
                admNumber.setError("Please you should fill the field");
                admNumber.requestFocus();
            } else if (TextUtils.isEmpty(phoneNo)){
                Toast.makeText(SignUpActivity.this, "Phone Number is empty", Toast.LENGTH_LONG).show();
                phoneNumber.setError("Please you should fill the field");
                phoneNumber.requestFocus();
            }else if (phoneNo.length() < 11){
                Toast.makeText(this, "Invalid phone number ", Toast.LENGTH_LONG).show();
                phoneNumber.setError("Insert correct mobile number");
                phoneNumber.requestFocus();
            } else {
                progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Please wait while creating your account");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
               registerUser(firstName, lastName, emailAddress, password, admissionNo,phoneNo);
            }
        });


    }

   private void registerUser(String firstName, String lastName, String emailAddress, String password, String admissionNo, String phoneNo) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
       mAuth.createUserWithEmailAndPassword(emailAddress, password)
               .addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_LONG).show();
                       sendToHomeActivity();
                   } else {
                       try {
                           throw Objects.requireNonNull(task.getException());
                       } catch (FirebaseAuthInvalidCredentialsException e){
                           newPassword.setError("invalid email address or Already in use");
                           newPassword.requestFocus();
                       } catch (FirebaseAuthUserCollisionException e){
                           email.setError("User Already registered with this email address.");
                           email.requestFocus();
                       } catch (Exception e){
                           Log.e(TAG, e.getMessage());
                           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });

   }

    private void sendToHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        progressDialog.dismiss();
    }
}