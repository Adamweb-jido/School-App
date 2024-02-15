package com.adamweb.sarcoapp;


import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {


    TextInputEditText fName, lName, email, newPassword, cPassword, urPhoneNumber, urAdmissionNumber, urCombination, urComment;
    MaterialButton nextBtn;
    TextView login;
    ProgressDialog progressDialog;
    FirebaseAuth userAuth;
    FirebaseUser cUser;
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
        urPhoneNumber = findViewById(R.id.urPhoneNumber);
        urAdmissionNumber = findViewById(R.id.admissionNumber);
        urCombination = findViewById(R.id.combination);
        urComment = findViewById(R.id.comment);

        userAuth = FirebaseAuth.getInstance();
         cUser = userAuth.getCurrentUser();




        nextBtn.setOnClickListener(view -> {
            String firstName, lastName, emailAddress, password, confirmPassword, phoneNumber, admissionNumber, combination, comment, imageUri = "", uid;
            firstName = String.valueOf(fName.getText());
            lastName = String.valueOf(lName.getText());
            emailAddress = String.valueOf(email.getText());
            password = String.valueOf(newPassword.getText());
            confirmPassword = String.valueOf(cPassword.getText());
            phoneNumber = String.valueOf(urPhoneNumber.getText());
            admissionNumber = String.valueOf(urAdmissionNumber.getText());
            combination = String.valueOf(urCombination.getText());
            comment = String.valueOf(urComment.getText());
            uid = cUser.getUid();


            if (TextUtils.isEmpty(firstName)) {
                Toast.makeText(SignUpActivity.this, "First Name is empty", Toast.LENGTH_LONG).show();
                fName.setError("Please you should fill the field");
                fName.requestFocus();
            } else if(firstName.length() < 4){
                fName.setError("First Name is too short");
                fName.requestFocus();
            } else if (lastName.length() < 3){
                lName.setError("Last Name is too short");
                lName.requestFocus();
            } else if(lastName.equals(firstName)){
                lName.setError("Your last name must be different with first name");
                lName.requestFocus();
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
                newPassword.requestFocus();
            } else if (password.length() < 6) {
                Toast.makeText(SignUpActivity.this, "Password is less than 6 digit", Toast.LENGTH_LONG).show();
                newPassword.setError("set required password");
                newPassword.requestFocus();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Password miss match!", Toast.LENGTH_LONG).show();
                cPassword.setError("fill the password correctly");
            } else if (TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(this, "Phone Number is Empty", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("Please Enter Your Phone Number");
                urPhoneNumber.requestFocus();
            } else if (phoneNumber.length() != 11){
                Toast.makeText(this, "Phone Number is not 11 digits", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("Invalid Enter 11 digits");
                urPhoneNumber.requestFocus();
            } else if (!phoneNumber.startsWith("0")){
                Toast.makeText(this, "Phone Number Must start with 0", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("first digit must be 0");
                urPhoneNumber.requestFocus();
            } else if (TextUtils.isEmpty(admissionNumber)){
                Toast.makeText(this, "Admission Number is Empty", Toast.LENGTH_SHORT).show();
                urAdmissionNumber.setError("Admission Number is required");
                urAdmissionNumber.requestFocus();
            } else if (admissionNumber.length() != 14){
                Toast.makeText(this, "Enter Valid Admission Number", Toast.LENGTH_SHORT).show();
                urAdmissionNumber.setError("Invalid Admission Number");
            } else if (TextUtils.isEmpty(combination)){
                Toast.makeText(this, "can't leave empty", Toast.LENGTH_LONG).show();
            } else if (comment.equals(combination)){
                urComment.setError("your Combination can't be your comment");
                urComment.requestFocus();
            }  else if (comment.equals(phoneNumber)){
                urComment.setError("your Phone Number can't be your comment");
                urComment.requestFocus();
            }  else if (comment.equals(admissionNumber)){
                urComment.setError("your Admission Number can't be your comment");
                urComment.requestFocus();
            } else if (TextUtils.isEmpty(comment)){
                Toast.makeText(this, "Comment is Empty", Toast.LENGTH_SHORT).show();
                urComment.setError("Please enter your comment");
                urComment.requestFocus();
            } else if (comment.length() < 15){
                urComment.setError("your comment is too short");
                urComment.requestFocus();
            } else {
                progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setTitle("Registration.....");
                progressDialog.setMessage("Please wait while creating your account");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
               registerUser(firstName, lastName, emailAddress, password, phoneNumber, admissionNumber, combination, comment, imageUri, uid);
            }
        });


        login.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            Animatoo.INSTANCE.animateSwipeLeft(this);

        });
    }

    private void registerUser(String firstName, String lastName, String emailAddress, String password, String phoneNumber, String admissionNumber, String combination, String comment, String imageUri, String uid) {

        userAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        assert cUser != null;
                        UserModel userModel = new UserModel(firstName, lastName, phoneNumber, admissionNumber, combination, comment, imageUri, emailAddress, uid);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
                        databaseReference.child(cUser.getUid()).setValue(userModel).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "You have successfully registered", Toast.LENGTH_LONG).show();
                                sendToCompleteProfileActivity();
                            } else {
                                Toast.makeText(getApplicationContext(), "Your registration failed, please try again.", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        });

                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidCredentialsException e){
                            email.setError("Invalid Email Address or does't exist");
                            email.requestFocus();
                            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthUserCollisionException e){
                            email.setError("User Already registered with this email address.");
                            email.requestFocus();
                            urPhoneNumber.setError("User with the same Number already registered");
                            urPhoneNumber.requestFocus();
                            urAdmissionNumber.setError("Admission Number Already taken by another user");
                        } catch (Exception e){
                            Log.e(TAG, e.getMessage());
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }


    private void sendToCompleteProfileActivity() {
        Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
        startActivity(intent);
        finish();
        Animatoo.INSTANCE.animateSlideUp(SignUpActivity.this);
        progressDialog.dismiss();
    }
}