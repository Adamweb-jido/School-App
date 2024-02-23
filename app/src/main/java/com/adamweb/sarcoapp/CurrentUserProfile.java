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
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentUserProfile extends AppCompatActivity {

    ImageView backArrow;
    CircleImageView profileImage;
    RoundedImageView userProfileDp;
    ImageButton cancelBtn, uploadDpBtn;
    MaterialButton editProfileBtn, saveDpBtn;
    TextView fullName, email, phoneNumber, admNo, combination, comment, department, bestFriend, bestCourse, skills;
    FirebaseUser currentUser;
    DatabaseReference databaseReference, otherInfoRef;
    StorageReference storageReference;
    Dialog progressDialog, profileDpDialog;
    FloatingActionButton floatingActionButton;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_proflie);

        backArrow = findViewById(R.id.backArrow);
        profileImage = findViewById(R.id.profile_image);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        saveDpBtn = findViewById(R.id.saveDpBtn);
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email_address);
        phoneNumber = findViewById(R.id.phone_number);
        admNo = findViewById(R.id.admissionNo);
        combination = findViewById(R.id.combination);
        comment = findViewById(R.id.comment);
        department = findViewById(R.id.department);
        bestFriend = findViewById(R.id.urBestFriend);
        bestCourse = findViewById(R.id.urBestCourse);
        skills = findViewById(R.id.urSkills);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        profileDpDialog = new Dialog(this);
        profileDpDialog.setContentView(R.layout.current_user_profile_image_dialog);
        profileDpDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        profileDpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        userProfileDp = profileDpDialog.findViewById(R.id.currentUserDp);
        cancelBtn = profileDpDialog.findViewById(R.id.backImgBtn);
        uploadDpBtn = profileDpDialog.findViewById(R.id.editDpImgBtn);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        otherInfoRef = FirebaseDatabase.getInstance().getReference("Users More Info");
        storageReference = FirebaseStorage.getInstance().getReference("Users Pics");


        cancelBtn.setOnClickListener(v ->{
            profileDpDialog.dismiss();
        });

        uploadDpBtn.setOnClickListener(v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });
        profileImage.setOnClickListener(v ->{
            profileDpDialog.show();
        });

        backArrow.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });


        floatingActionButton.setOnClickListener(v ->{
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080, 1080).start();
        });


        saveDpBtn.setOnClickListener(v ->{
           if (imageUri == null){
               Toast.makeText(this, "You must Upload the pic before save", Toast.LENGTH_SHORT).show();
           } else {
               editProfilePic();
           }
        });

        editProfileBtn.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), EditProfile.class));
            Animatoo.INSTANCE.animateSwipeLeft(this);
            finish();
        });


        if (currentUser != null){
            fetchCurrentUserData();
            fetchOtherInfo();
        } else {
            Toast.makeText(this, "Wrong please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchOtherInfo() {
        otherInfoRef.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MoreInfo moreInfo = snapshot.getValue(MoreInfo.class);
                if (moreInfo != null){
                    String dep, friend, course, urSkills;
                    dep = moreInfo.getDepartment();
                    friend = moreInfo.getBestFriend();
                    course = moreInfo.getBestCourse();
                    urSkills = moreInfo.getSkills();

                    department.setText(dep);
                    bestFriend.setText(friend);
                    bestCourse.setText(course);
                    skills.setText(urSkills);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CurrentUserProfile.this, "Error While Fetching your data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchCurrentUserData() {
        databaseReference.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    String fName, lName, emailAddress, phoneNo, admissionNo, comb, userComment, profileDp;
                    fName = userModel.getFirstName();
                    lName = userModel.getLastName();
                    emailAddress = userModel.getEmail();
                    phoneNo = userModel.getPhoneNumber();
                    admissionNo = userModel.getAdmissionNumber();
                    comb = userModel.getCombination();
                    userComment = userModel.getComment();
                    profileDp = userModel.getImageUri();

                    fullName.setText(fName +" " +lName);
                    email.setText(emailAddress);
                    phoneNumber.setText(phoneNo);
                    admNo.setText(admissionNo);
                    combination.setText(comb);
                    comment.setText(userComment);
                    Picasso.get().load(profileDp).into(profileImage);
                    Picasso.get().load(profileDp).into(userProfileDp);

                }  else {
                    Toast.makeText(CurrentUserProfile.this, "Unable to load page please refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CurrentUserProfile.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void editProfilePic() {
        StorageReference fileReference = storageReference.child(currentUser.getUid() + getFileExtension(imageUri));
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri imageUri) {
                        UserModel userModel = new UserModel();
                        userModel.setImageUri(imageUri.toString());
                        databaseReference.child(currentUser.getUid()).updateChildren(userModel.imgMap());
                        startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CurrentUserProfile.this, "wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        imageUri = data.getData();
        profileImage.setImageURI(imageUri);
    }


    private String getFileExtension(Uri uploadFile){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap typeMap = MimeTypeMap.getSingleton();
        return typeMap.getExtensionFromMimeType(contentResolver.getType(uploadFile));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}