package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

   ImageView imageView;
    ImageView profile_image;
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
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