package com.adamweb.sarcoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.net.URI;

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

            if (admissionNumber.length() != 14){
                Toast.makeText(this, "You have entered invalid Adm. No", Toast.LENGTH_SHORT).show();
                urAdmNo.setError("Invalid Admission Number");
                urAdmNo.requestFocus();
            } else if (TextUtils.isEmpty(admissionNumber)){
                urAdmNo.setError("Fill this Field");
                urAdmNo.requestFocus();
            } else if (!combination.equals(combChoice)){
                Toast.makeText(this, "Hint: Only one comb is allowed", Toast.LENGTH_SHORT).show();
                urCombination.setError("your comb is not allowed yet!");
                urCombination.requestFocus();
            }
            else if (TextUtils.isEmpty(combination)){
                urCombination.setError("Fill this Field");
                urCombination.requestFocus();
            } else if (comment.equals(admissionNumber) || comment.equals(combination)){
                Toast.makeText(this, "Your Admission Number or comment can not be your comment",
                        Toast.LENGTH_SHORT).show();
                urComment.setError("Please write a valid comment");
                urComment.requestFocus();
            } else if (TextUtils.isEmpty(comment)){
                urComment.setError("Fill this Field");
                urComment.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                completeUserProfile();
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