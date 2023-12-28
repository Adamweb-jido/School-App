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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

   ImageView imageView;
    ImageView profile_image;
    TextView userFullName, userEmail, userPhoneNumber, userCombination, userAdmissionNumber, userComment;
    String firstName, lastName, email, phoneNumber, combination, admissionNumber, comment;
    Dialog dialog;
   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dialog = new Dialog(this);
        imageView  = findViewById(R.id.backArrow);
        profile_image = findViewById(R.id.profile_image);
        userFullName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.user_email);
        userPhoneNumber = findViewById(R.id.phone_number);
        userCombination = findViewById(R.id.user_comb);
        userAdmissionNumber = findViewById(R.id.adm_no);
        userComment = findViewById(R.id.user_comment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser userProfileDetails = firebaseAuth.getCurrentUser();

        if (userProfileDetails == null){
            Toast.makeText(this, "Refresh this page", Toast.LENGTH_SHORT).show();
        } else {
            fetchUserDetails(userProfileDetails);
        }



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void fetchUserDetails(FirebaseUser userProfileDetails) {
        String userId = userProfileDetails.getUid();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("Registered users");
        userReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserReadWriteData readUserDetails = snapshot.getValue(UserReadWriteData.class);
                if (readUserDetails != null){
                    firstName = readUserDetails.userFirstName;
                    email = userProfileDetails.getEmail();
                    lastName = readUserDetails.userLastName;
                    phoneNumber = readUserDetails.userAdmissionNo;
                    admissionNumber = readUserDetails.userPhoneNo;

                    userFullName.setText(firstName + " " + lastName);
                    userEmail.setText(email);
                    userPhoneNumber.setText(phoneNumber);
                    userAdmissionNumber.setText(admissionNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Failed to load user data, refresh the page.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void myMssBtn(View view){
        TextView sendEmail, cancelArrow;
        dialog.setContentView(R.layout.message_popup_layout);

        sendEmail = dialog.findViewById(R.id.sendEmail);
        cancelArrow = dialog.findViewById(R.id.cancelArrow);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        cancelArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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