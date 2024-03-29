package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    TextView slideOne, slideTwo;
    EditText editFirstName, editLastName, editPhoneNumber, editEmailAddress, editComment;
    MaterialButton saveChangesBtn, cancelChangesBtn;
    String firstName, lastName, phoneNumber, emailAddress, comment;
    ImageView backArrow;
    FirebaseAuth firebaseAuth;
   Animation topAnimation, sideAnimation;
   DatabaseReference databaseReference;
    FirebaseUser currentUser;
    Dialog progressDialog;


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

        progressDialog = new Dialog(this);
        progressDialog.setContentView(R.layout.progress_bar_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressDialog.setCancelable(false);


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        slideOne.setAnimation(topAnimation);
        slideTwo.setAnimation(sideAnimation);

        backArrow.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });



        if (currentUser == null){
            Toast.makeText(this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
        } else {
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
                progressDialog.show();
               editProfileData(firstName, lastName, emailAddress, phoneNumber, comment);
            }
        });

        cancelChangesBtn.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });
    }

     private void editProfileData(String firstName, String lastName, String emailAddress, String phoneNumber, String comment) {
        UserModel userModel = new UserModel();
         userModel.setFirstName(firstName);
         userModel.setLastName(lastName);
         userModel.setPhoneNumber(phoneNumber);
         userModel.setEmail(emailAddress);
         userModel.setComment(comment);


        databaseReference.child(currentUser.getUid()).updateChildren(userModel.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EditProfile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
                    finish();
                } else {
                    Toast.makeText(EditProfile.this, "Error try again", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });
    }

    private void fetchUserData() {
        String userId = currentUser.getUid();
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    firstName = userModel.getFirstName();
                    lastName = userModel.getLastName();
                    phoneNumber = userModel.getPhoneNumber();
                    emailAddress = currentUser.getEmail();
                    comment = userModel.getComment();

                    editFirstName.setText(firstName);
                    editLastName.setText(lastName);
                    editPhoneNumber.setText(phoneNumber);
                    editEmailAddress.setText(emailAddress);
                    editComment.setText(comment);


                } else {
                    Toast.makeText(EditProfile.this, "Unable to fetch the user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfile.this, "Something Went Wrong!, try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}