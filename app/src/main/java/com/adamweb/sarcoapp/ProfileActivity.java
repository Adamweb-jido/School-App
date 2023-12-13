package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

   ImageView imageView, profile_image, mssgBtn;
   TextView userFullName, userEmail, phoneNumber, combination, admissionNumber, comment;
   Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dialog = new Dialog(this);
        imageView  = findViewById(R.id.backArrow);
        profile_image = findViewById(R.id.profile_image);
        userFullName = findViewById(R.id.fullName);
        userEmail = findViewById(R.id.user_email);
        phoneNumber = findViewById(R.id.phone_number);
        combination = findViewById(R.id.user_comb);
        admissionNumber = findViewById(R.id.adm_no);
        comment = findViewById(R.id.user_comment);


          userFullName.setText("Adamu ibrahim Ya'u");
          userEmail.setText("realadamweb@gmail.com");
          phoneNumber.setText("08160807055");
          combination.setText("Computer/chemistry");
          admissionNumber.setText("CSC/01/19/0585");
          comment.setText("Being a software engineer is a great work");
        profile_image.setImageResource(R.drawable.adamweb);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void myMssBtn(View view){
        TextView cancelArrow;
        dialog.setContentView(R.layout.message_popup_layout);

        cancelArrow = dialog.findViewById(R.id.cancelArrow);
        cancelArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}