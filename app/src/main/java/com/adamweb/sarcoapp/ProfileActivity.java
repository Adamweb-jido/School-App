package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

   ImageView imageView, profile_image;
   TextView userFullName, userEmail, phoneNumber, combination, admissionNumber, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView  = findViewById(R.id.backArrow);
        profile_image = findViewById(R.id.profile_image);
        userFullName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.user_email);
        phoneNumber = findViewById(R.id.phone_number);
        combination = findViewById(R.id.user_comb);
        admissionNumber = findViewById(R.id.adm_no);
        comment = findViewById(R.id.user_comment);



        profile_image.setImageResource(R.drawable.adamweb);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}