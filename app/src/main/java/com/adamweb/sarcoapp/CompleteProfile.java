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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class CompleteProfile extends AppCompatActivity {

    TextInputEditText urPhoneNumber, urAdmissionNumber, urCombination, urComment;
    MaterialButton completeBtn;
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

        urPhoneNumber = findViewById(R.id.urPhoneNumber);
        urAdmissionNumber = findViewById(R.id.admissionNumber);
        urCombination = findViewById(R.id.combination);
        urComment = findViewById(R.id.comment);
        completeBtn = findViewById(R.id.completeBtn);
        uploadPicture = findViewById(R.id.profile_image);
        uploadBtn = findViewById(R.id.uploadBtn);
        progressBar = findViewById(R.id.completeProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        storageReference = FirebaseStorage.getInstance().getReference("Users Pics");


        uploadBtn.setOnClickListener(v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });

        completeBtn.setOnClickListener(v ->{
            String phoneNumber, admissionNumber, combination, comment;
            phoneNumber = String.valueOf(urPhoneNumber.getText());
            admissionNumber = String.valueOf(urAdmissionNumber.getText());
            combination = String.valueOf(urCombination.getText());
            comment = String.valueOf(urComment.getText());

            if (comment.length() < 1){
                Toast.makeText(this, "You Must Upload Your Pic", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(this, "Phone Number is Empty", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("Please Enter Your Phone Number");
                urPhoneNumber.requestFocus();
            } else if (phoneNumber.length() != 11){
                Toast.makeText(this, "Phone Number is not 11 digits", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("Invalid Enter 11 digits");
                urPhoneNumber.requestFocus();
            } else if (!phoneNumber.startsWith("0")){
                Toast.makeText(this, "Phone Number Must start with 0", Toast.LENGTH_SHORT).show();
                urPhoneNumber.setError("first digit must be 0");
                urPhoneNumber.requestFocus();
            } else if (TextUtils.isEmpty(admissionNumber)){
                Toast.makeText(this, "Admission Number is Empty", Toast.LENGTH_SHORT).show();
                urAdmissionNumber.setError("Admission Number is required");
                urAdmissionNumber.requestFocus();
            } else if (admissionNumber.length() != 14){
                Toast.makeText(this, "Enter Valid Admission Number", Toast.LENGTH_SHORT).show();
                urAdmissionNumber.setError("Invalid Admission Number");
            } else if (TextUtils.isEmpty(combination)){
                Toast.makeText(this, "can't leave empty", Toast.LENGTH_LONG).show();
            } else if (comment.equals(combination)){
                Toast.makeText(this, "Your Combination can't be your comment", Toast.LENGTH_SHORT).show();
            }  else if (comment.equals(phoneNumber)){
                Toast.makeText(this, "Your Phone Number can't be your comment", Toast.LENGTH_SHORT).show();
            }  else if (comment.equals(admissionNumber)){
                Toast.makeText(this, "Your Admission Number can't be your comment", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(comment)){
                Toast.makeText(this, "Comment is Empty", Toast.LENGTH_SHORT).show();
                urComment.setError("Please enter your comment");
                urComment.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);
                //uploadPicToDatabase();
                addToUserData(phoneNumber, admissionNumber, combination, comment);
            }
        });




    }

    private void addToUserData(String phoneNumber, String admissionNumber, String combination, String comment) {
        UserModel userModel = new UserModel();
        userModel.setUserPhoneNo(phoneNumber);
        userModel.setUserAdmissionNo(admissionNumber);
        userModel.setUserCombination(combination);
        userModel.setUserComment(comment);

        databaseReference.child(firebaseUser.getUid()).updateChildren(userModel.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CompleteProfile.this, "Your Profile is Completed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(CompleteProfile.this, "Unable to complete please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /* private void uploadPicToDatabase() {

        StorageReference fileReference = storageReference.child(firebaseUser.getUid() + getFileExtension(imageUri));
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri imageUri) {
                        UserModel userModel = new UserModel();
                        userModel.setUserImageUri(imageUri.toString());
                        databaseReference.child(firebaseUser.getUid()).updateChildren(userModel.toMap());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CompleteProfile.this, "wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    } */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData();
        uploadPicture.setImageURI(imageUri);
    }

    private String getFileExtension(Uri uploadFile){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
        return typeMap.getExtensionFromMimeType(contentResolver.getType(uploadFile));
    }
}