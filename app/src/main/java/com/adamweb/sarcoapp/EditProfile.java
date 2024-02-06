package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends AppCompatActivity {

    TextView slideOne, slideTwo;
    EditText editFirstName, editLastName, editPhoneNumber, editEmailAddress, editComment;
    MaterialButton saveChangesBtn, cancelChangesBtn;
    ImageView backArrow, profilePic;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
Animation topAnimation, sideAnimation;
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

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        slideOne.setAnimation(topAnimation);
        slideTwo.setAnimation(sideAnimation);

        backArrow.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            finish();
        });


    }
}