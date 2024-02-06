package com.adamweb.sarcoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfile extends AppCompatActivity {

    TextView slideOne, slideTwo;
    EditText editFirstName, editLastName, editPhoneNumber, editEmailAddress, editComment;
    MaterialButton saveChangesBtn, cancelChangesBtn;
    ImageView backArrow, profilePic;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
Animation topAnimation, sideAnimation;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        slideOne = findViewById(R.id.textView4);
        slideTwo = findViewById(R.id.slideTwo);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editEmailAddress = findViewById(R.id.editEmailAddress);
        editComment = findViewById(R.id.editComment);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        cancelChangesBtn = findViewById(R.id.cancelBtn);
        backArrow = findViewById(R.id.backArrow);
        profilePic = findViewById(R.id.profile_image);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        progressBar = findViewById(R.id.editProgressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        slideOne.setAnimation(topAnimation);
        slideTwo.setAnimation(sideAnimation);

        backArrow.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        });

        floatingActionButton.setOnClickListener(v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });

        if (currentUser == null){
            Toast.makeText(this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            fetchUserData();
        }
        saveChangesBtn.setOnClickListener(v ->{
            String firstName, lastName, phoneNumber, emailAddress, comment;
            firstName = String.valueOf(editFirstName.getText());
            lastName = String.valueOf(editLastName.getText());
            phoneNumber = String.valueOf(editPhoneNumber.getText());
            emailAddress = String.valueOf(editEmailAddress.getText());
            comment = String.valueOf(editComment.getText());

            if (TextUtils.isEmpty(firstName)){
                Toast.makeText(this, "First Name is Empty", Toast.LENGTH_SHORT).show();
                editFirstName.setError("This field can't be empty");
                editFirstName.requestFocus();
            } else if (TextUtils.isEmpty(lastName)){
                Toast.makeText(this, "Last Name is Empty", Toast.LENGTH_SHORT).show();
                editLastName.setError("This field can't be empty");
                editLastName.requestFocus();
            } else if (TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(this, "Phone Number is Empty", Toast.LENGTH_SHORT).show();
                editPhoneNumber.setError("This field can't be empty");
                editPhoneNumber.requestFocus();
            } else if (phoneNumber.length() != 11){
                Toast.makeText(this, "incorrect Phone Number", Toast.LENGTH_SHORT).show();
                editPhoneNumber.setError("check the entered digits");
                editPhoneNumber.requestFocus();
            } else if (!phoneNumber.startsWith("0")){
                Toast.makeText(this, "Phone Number Must Start with 0", Toast.LENGTH_SHORT).show();
                editPhoneNumber.setError("first digit must be 0");
                editPhoneNumber.requestFocus();
            } else if (TextUtils.isEmpty(emailAddress)){
                Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show();
                editEmailAddress.setError("This field can't be empty");
                editEmailAddress.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                editEmailAddress.setError("Invalid Email");
                editEmailAddress.requestFocus();
            } else if (TextUtils.isEmpty(comment)){
                Toast.makeText(this, "Comment is Empty", Toast.LENGTH_SHORT).show();
                editComment.setError("This field can't be empty");
                editComment.requestFocus();
            } else {
                editProfile();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        cancelChangesBtn.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        });
    }

    private void fetchUserData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profilePic.setImageURI(uri);
    }

    private void editProfile() {
        
    }
}