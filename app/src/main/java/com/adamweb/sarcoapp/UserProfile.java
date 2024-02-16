package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    TextView clickMe, headerName,userFullName, userEmail, userPhoneNumber, userAdmissionNumber, userCombination, userComment;
    CircleImageView userDp;
    ImageView backArrow, addUserToContact, sendMsgToUser, callUser, sendSMSorEmailToUser;
    String userId;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        addUserToContact = findViewById(R.id.addUserToContact);
        sendMsgToUser = findViewById(R.id.sendMsgToUser);
        callUser = findViewById(R.id.callUser);
        sendSMSorEmailToUser = findViewById(R.id.sendSmsOrEmail);
        layout = findViewById(R.id.contactUser);
        headerName = findViewById(R.id.textView4);
        userFullName = findViewById(R.id.userFullName);
        userEmail = findViewById(R.id.user_email_address);
        userPhoneNumber = findViewById(R.id.user_phone_number);
        userAdmissionNumber = findViewById(R.id.user_admissionNo);
        userCombination = findViewById(R.id.user_combination);
        userComment = findViewById(R.id.other_user_comment);
        userDp = findViewById(R.id.user_profile_image);
        clickMe = findViewById(R.id.clickMe);
        backArrow = findViewById(R.id.backArrow);
        userId = getIntent().getStringExtra("userId");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");


        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                Animatoo.INSTANCE.animateSwipeLeft(UserProfile.this);
                finish();
            }
        });

        backArrow.setOnClickListener( v ->{
            startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            Animatoo.INSTANCE.animateSwipeRight(UserProfile.this);
            finish();
        });
        if (!Objects.equals(userId, currentUser.getUid())){
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
            clickMe.setVisibility(View.VISIBLE);
        }

        fetchUserData(userId);


     addUserToContact.setOnClickListener( v -> {

     });

     sendMsgToUser.setOnClickListener(v ->{

     });

     callUser.setOnClickListener(v ->{

     });

     sendSMSorEmailToUser.setOnClickListener(v ->{

     });

    }

    private void fetchUserData(String userId) {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    String firstName, lastName, email, phoneNumber, admissionNumber, combination, comment, profileImg;
                    firstName = userModel.getFirstName();
                    lastName = userModel.getLastName();
                    email = userModel.getEmail();
                    phoneNumber = userModel.getPhoneNumber();
                    admissionNumber = userModel.getAdmissionNumber();
                    combination = userModel.getCombination();
                    comment = userModel.getComment();
                    profileImg = userModel.getImageUri();

                    headerName.setText(firstName + " - Profile");
                    userFullName.setText(firstName + " " + lastName);
                    userEmail.setText(email);
                    userPhoneNumber.setText(phoneNumber);
                    userAdmissionNumber.setText(admissionNumber);
                    userCombination.setText(combination);
                    userComment.setText(comment);
                    Picasso.get().load(profileImg).into(userDp);
                } else {
                    Toast.makeText(UserProfile.this, "Failed to load user data try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Data Loading Failed try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}