package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

   ImageView saveUserToContact, messageUser, callUser, sendSmsToUser, backArrow;
   CircleImageView profile_image;
   LinearLayout contactUserLayout;
   MaterialButton editProfileBtn;
    TextView userFullName, userEmail, userPhoneNumber, userCombination, userAdmissionNumber, userComment;
    String firstName, lastName, email, phoneNumber, combination, admissionNumber, comment;
    Dialog dialog;
   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dialog = new Dialog(this);
        backArrow  = findViewById(R.id.backArrow);
        profile_image = findViewById(R.id.profile_image);
        userFullName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.user_email);
        userPhoneNumber = findViewById(R.id.phone_number);
        userCombination = findViewById(R.id.user_comb);
        userComment = findViewById(R.id.user_comment);
        userAdmissionNumber = findViewById(R.id.admissionNo);
        saveUserToContact = findViewById(R.id.addUserToContact);
        messageUser = findViewById(R.id.m )

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser userDetails = firebaseAuth.getCurrentUser();
        if (userDetails == null){
            Toast.makeText(this, "Failed! please refresh the page", Toast.LENGTH_LONG).show();
        }else {
            getUserDetails(userDetails);
        }




        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void getUserDetails(FirebaseUser userDetails) {
         String userId = userDetails.getUid();
         DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
         databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 UserReadWriteData userReadWriteData = snapshot.getValue(UserReadWriteData.class);
                 if (userReadWriteData != null){
                     email = userDetails.getEmail();
                     firstName = userReadWriteData.userFirstName;
                     lastName = userReadWriteData.userLastName;
                     phoneNumber = userReadWriteData.userPhoneNo;
                     admissionNumber = userReadWriteData.userAdmissionNo;
                     combination = userReadWriteData.userCombination;
                     comment = userReadWriteData.userComment;
                     Uri profilePic = userDetails.getPhotoUrl();

                     userFullName.setText(firstName + " " + lastName);
                     userEmail.setText(email);
                     userPhoneNumber.setText(phoneNumber);
                     userAdmissionNumber.setText(admissionNumber);
                     userCombination.setText(combination);
                     userComment.setText(comment);
                     Picasso.get().load(profilePic).into(profile_image);

                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(ProfileActivity.this, "Failed to load your data", Toast.LENGTH_SHORT).show();
             }
         });
    }


    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"realadamweb@example.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email");


        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}