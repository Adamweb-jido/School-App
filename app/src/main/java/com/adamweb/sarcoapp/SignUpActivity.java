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


        nextBtn.setOnClickListener(view -> {
            String firstName, lastName, emailAddress, password, confirmPassword, phoneNumber, admissionNumber, combination, comment;
            firstName = String.valueOf(fName.getText());
            lastName = String.valueOf(lName.getText());
            emailAddress = String.valueOf(email.getText());
            password = String.valueOf(newPassword.getText());
            confirmPassword = String.valueOf(cPassword.getText());
            phoneNumber = String.valueOf(urPhoneNumber.getText());
            admissionNumber = String.valueOf(urAdmissionNumber.getText());
            combination = String.valueOf(urCombination.getText());
            comment = String.valueOf(urComment.getText());


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
                newPassword.requestFocus();
            } else if (password.length() < 6) {
                Toast.makeText(SignUpActivity.this, "Password is less than 6 digit", Toast.LENGTH_LONG).show();
                newPassword.setError("set required password");
                newPassword.requestFocus();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Password miss match!", Toast.LENGTH_LONG).show();
                cPassword.setError("fill the password correctly");
            } else if (comment.length() < 1){
                Toast.makeText(this, "You Must Upload Your Pic", Toast.LENGTH_LONG).show();
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
                Toast.makeText(this, "Your Combination can't be your comment", Toast.LENGTH_SHORT).show();
            }  else if (comment.equals(phoneNumber)){
                Toast.makeText(this, "Your Phone Number can't be your comment", Toast.LENGTH_SHORT).show();
            }  else if (comment.equals(admissionNumber)){
                Toast.makeText(this, "Your Admission Number can't be your comment", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(comment)){
                Toast.makeText(this, "Comment is Empty", Toast.LENGTH_SHORT).show();
                urComment.setError("Please enter your comment");
                urComment.requestFocus();
            } else {
                progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setMessage("Please wait while creating your account");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
               registerUser(firstName, lastName, emailAddress, password);
            }
        });


        login.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

   private void registerUser(String userFirstName, String userLastName, String emailAddress, String password) {
        FirebaseAuth userAuth = FirebaseAuth.getInstance();
       userAuth.createUserWithEmailAndPassword(emailAddress, password)
               .addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       FirebaseUser cUser = userAuth.getCurrentUser();
                       assert cUser != null;
                       UserModel userModel = new UserModel();
                       userModel.setUserFirstName(userFirstName);
                       userModel.setUserLastName(userLastName);
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
                           throw Objects.requireNonNull(task.getException());
                       } catch (FirebaseAuthInvalidCredentialsException e){
                           Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
                       } catch (FirebaseAuthUserCollisionException e){
                           email.setError("User Already registered with this email address.");
                           email.requestFocus();
                       } catch (Exception e){
                           Log.e(TAG, e.getMessage());
                           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                           progressDialog.dismiss();
                       }
                   }
               });


   }

    private void sendToCompleteProfileActivity() {
        Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        progressDialog.dismiss();
    }
}