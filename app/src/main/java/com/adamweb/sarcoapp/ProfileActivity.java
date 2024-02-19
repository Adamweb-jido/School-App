package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
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

   ImageView backArrow;
   CircleImageView profile_image;

    TextView userFullName, userEmail, userPhoneNumber, userCombination, userAdmissionNumber, userComment;
    String firstName, lastName, email, phoneNumber, combination, admissionNumber, comment;
   FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        backArrow  = findViewById(R.id.backArrow);
        profile_image = findViewById(R.id.profile_image);
        userFullName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.user_email);
        userPhoneNumber = findViewById(R.id.phone_number);
        userCombination = findViewById(R.id.user_comb);
        userComment = findViewById(R.id.user_comment);
        userAdmissionNumber = findViewById(R.id.admissionNo);
        editProfileBtn = findViewById(R.id.editProfileBtn);


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
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });

    }

    private void getUserDetails(FirebaseUser userDetails) {
         String userId = userDetails.getUid();
         DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
         databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 UserModel userData = snapshot.getValue(UserModel.class);
                 if (userData != null){
                     firstName = userData.getFirstName();
                     lastName = userData.getLastName();
                     email = userData.getEmail();
                     phoneNumber = userData.getPhoneNumber();
                     admissionNumber = userData.getAdmissionNumber();
                     combination = userData.getCombination();
                     comment = userData.getComment();

                     userFullName.setText(firstName + " " + lastName);
                     userEmail.setText(email);
                     userPhoneNumber.setText(phoneNumber);
                     userAdmissionNumber.setText(admissionNumber);
                     userCombination.setText(combination);
                     userComment.setText(comment);
                     Picasso.get().load(userData.getImageUri()).into(profile_image);


                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(ProfileActivity.this, "Failed to load your data", Toast.LENGTH_SHORT).show();
             }
         });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}