package com.adamweb.sarcoapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteProfile extends AppCompatActivity {

    MaterialButton completeBtn;
    EditText urDepartment, urBestFriend, urBestCourse, urSkills;
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
        urDepartment = findViewById(R.id.yourDept);
        urBestFriend = findViewById(R.id.bestFriend);
        urBestCourse = findViewById(R.id.bestCourse);
        urSkills = findViewById(R.id.yourSkill);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        storageReference = FirebaseStorage.getInstance().getReference("Users Pics");


        uploadBtn.setOnClickListener( v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });


        completeBtn.setOnClickListener(v ->{

            String department, bestFriend, bestCourse, skills;
            department = String.valueOf(urDepartment.getText());
            bestFriend = String.valueOf(urBestFriend.getText());
            bestCourse = String.valueOf(urBestCourse.getText());
            skills = String.valueOf(urSkills.getText());

            if(imageUri == null){
                Toast.makeText(this, "You Must Upload Your picture first", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(department)){
                urDepartment.setError("Please Enter your Department");
                urDepartment.requestFocus();
            } else if (TextUtils.isEmpty(bestFriend)){
                urBestFriend.setError("Enter your friend name");
                urBestFriend.requestFocus();
            } else if (TextUtils.isEmpty(bestCourse)){
                urBestCourse.setError("Please write your best course");
                urBestCourse.requestFocus();
            } else if (TextUtils.isEmpty(skills)){
                urSkills.setError("Please Write your skill(s)");
                urSkills.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                uploadPicToDatabase(imageUri);
                addMoreInfo(department, bestFriend, bestCourse, skills);
            }

        });

    }

    private void uploadPicToDatabase(Uri imageUri) {
        StorageReference fileReference = storageReference.child(firebaseUser.getUid() + getFileExtension(imageUri));
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri imageUri) {
                        UserModel userModel = new UserModel();
                        userModel.setImageUri(imageUri.toString());
                        databaseReference.child(firebaseUser.getUid()).updateChildren(userModel.toMap());
                        Toast.makeText(CompleteProfile.this, "Your Image is Successfully uploaded", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CompleteProfile.this, "wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void addMoreInfo(String department, String bestFriend, String bestCourse, String skills) {
        MoreInfo moreInfo = new MoreInfo(department, bestFriend, bestCourse, skills);
        DatabaseReference moreUsersInfo = FirebaseDatabase.getInstance().getReference("Users More Info");
        moreUsersInfo.child(firebaseUser.getUid()).setValue(moreInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CompleteProfile.this, "Info Submitted", Toast.LENGTH_SHORT).show();

                }  else {
                    Toast.makeText(CompleteProfile.this, "Unable to process your request", Toast.LENGTH_SHORT).show();
                }
            }
        });
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