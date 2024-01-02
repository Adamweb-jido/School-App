package com.adamweb.sarcoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteProfile extends AppCompatActivity {

    CircleImageView imageView;
    FloatingActionButton floatingActionButton;
    TextInputEditText urAdmNo, urComment, urCombination;
    MaterialButton completeBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        imageView = findViewById(R.id.circleImageView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        urAdmNo = findViewById(R.id.admNumber);
        urCombination = findViewById(R.id.user_combination);
        urComment = findViewById(R.id.comment);
        completeBtn = findViewById(R.id.completeBtn);
        progressBar = findViewById(R.id.userProfileProgressBar);

        completeBtn.setOnClickListener(v ->{
            String admissionNumber, combination, comment;
                  String combChoice = "Computer/Chemistry";
            admissionNumber = String.valueOf(urAdmNo.getText());
            combination = String.valueOf(urCombination.getText());
            comment = String.valueOf(urComment.getText());


            }

        });



        floatingActionButton.setOnClickListener(v ->
                ImagePicker.with(CompleteProfile.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start());

    }

    private void completeUserProfile() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }
}