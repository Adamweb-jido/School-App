package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CurrentUserProfile extends AppCompatActivity {

    ImageView backArrow;
    CircleImageView profileImage;
    MaterialButton editProfileBtn;
    TextView fullName, email, phoneNumber, admNo, combination, comment;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_proflie);

        backArrow = findViewById(R.id.backArrow);
        profileImage = findViewById(R.id.profile_image);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email_address);
        phoneNumber = findViewById(R.id.phone_number);
        admNo = findViewById(R.id.admissionNo);
        combination = findViewById(R.id.combination);
        comment = findViewById(R.id.comment);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");

        backArrow.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });

        if (currentUser != null){
            fetchCurrentUserData();
        } else {
            Toast.makeText(this, "Wrong please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchCurrentUserData() {
        databaseReference.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class;
                if (userModel != null){
                    String fName, lName, emailAddress, phoneNo, admissionNo, comb, userComment, profileDp;
                    fName = userModel.getFirstName();
                    lName = userModel.getLastName();
                    emailAddress = userModel.getEmail();
                    phoneNo = userModel.getPhoneNumber();
                    admissionNo = userModel.getAdmissionNumber();
                    comb = userModel.getCombination();
                    userComment = userModel.getComment();
                    profileDp = userModel.getImageUri();

                    fullName.setText(fName +" " +lName);
                    email.setText(emailAddress);
                    phoneNumber.setText(phoneNo);
                    admNo.setText(admissionNo);
                    combination.setText(comb);
                    comment.setText(userComment);
                    Picasso.get().load(profileDp).into(profileImage);

                }  else {
                    Toast.makeText(CurrentUserProfile.this, "Unable to load page please refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CurrentUserProfile.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}