package com.adamweb.sarcoapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteProfile extends AppCompatActivity {

    MaterialButton completeBtn;
    EditText department, bestFriend, bestCourse, skills;
    CircleImageView uploadPicture;
    FloatingActionButton uploadBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ProgressBar progressBar;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        completeBtn = findViewById(R.id.completeBtn);
        uploadPicture = findViewById(R.id.profile_image);
        uploadBtn = findViewById(R.id.uploadBtn);
        progressBar = findViewById(R.id.completeProgressBar);
        department = findViewById(R.id.yourDept);
        bestFriend = findViewById(R.id.bestFriend);
        bestCourse = findViewById(R.id.bestCourse);
        skills = findViewById(R.id.yourSkill);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        storageReference = FirebaseStorage.getInstance().getReference("Users Pics");


        uploadBtn.setOnClickListener( v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });

        if (imageUri != null){
            department.setEnabled(false);
            bestFriend.setEnabled(false);
            bestCourse.setEnabled(false);
            skills.setEnabled(false);
            completeBtn.setEnabled(false);
        } else {
            department.setEnabled(true);
            bestFriend.setEnabled(false);
            bestCourse.setEnabled(false);
            skills.setEnabled(false);
            completeBtn.setEnabled(false);
        }
         if (department == null){
            bestFriend.setEnabled(false);
            bestCourse.setEnabled(false);
            skills.setEnabled(false);
            completeBtn.setEnabled(false);
        } else {
             bestFriend.setEnabled(true);
             bestCourse.setEnabled(false);
             skills.setEnabled(false);
             completeBtn.setEnabled(false);
         }

          if (bestFriend == null){
            bestCourse.setEnabled(false);
            skills.setEnabled(false);
            completeBtn.setEnabled(false);
        } else {
              bestCourse.setEnabled(true);
              skills.setEnabled(false);
              completeBtn.setEnabled(false);
          }

          if (bestCourse == null){
            skills.setEnabled(false);
            completeBtn.setEnabled(false);
        } else {
              skills.setEnabled(true);
              completeBtn.setEnabled(false);
          }

          if (skills == null){
              completeBtn.setEnabled(false);
          } else {
              completeBtn.setEnabled(true);
          }


        completeBtn.setOnClickListener(v ->{
                progressBar.setVisibility(View.VISIBLE);
                uploadPicToDatabase();

        });




    }

     private void uploadPicToDatabase() {

        StorageReference fileReference = storageReference.child(firebaseUser.getUid() + getFileExtension(imageUri));
        fileReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(imageUri -> {
            UserModel userModel = new UserModel();
            userModel.setImageUri(imageUri.toString());
            databaseReference.child(firebaseUser.getUid()).updateChildren(userModel.toMap());
        }).addOnFailureListener(e -> Toast.makeText(CompleteProfile.this, "wrong", Toast.LENGTH_SHORT).show()));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data. getData();
        uploadPicture.setImageURI(imageUri);
    }

    private String getFileExtension(Uri uploadFile){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
        return typeMap.getExtensionFromMimeType(contentResolver.getType(uploadFile));
    }
}