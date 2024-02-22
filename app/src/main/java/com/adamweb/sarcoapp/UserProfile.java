package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    TextView headerName,userFullName, userEmail, userPhoneNumber, userAdmissionNumber, userCombination, userComment, sendEmail, sendDM, cancelArrow;
    CircleImageView userDp;
    RoundedImageView fullSizeImg;
    MaterialButton editProfileBtn;
    RelativeLayout relativeLayout;
    ImageView backArrow, addUserToContact, sendMsgToUser, callUser, sendSMSorEmailToUser;
    String userId, firstName, lastName, email, phoneNumber, admissionNumber, combination, comment, profileImg;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    LinearLayout layout;
    Dialog contactUserDialog, full_size_image_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        addUserToContact = findViewById(R.id.addUserToContact);
        sendMsgToUser = findViewById(R.id.sendMsgToUser);
        callUser = findViewById(R.id.callUser);
        sendSMSorEmailToUser = findViewById(R.id.sendSmsOrEmail);
        layout = findViewById(R.id.contactUser);
        headerName = findViewById(R.id.textView4);
        userFullName = findViewById(R.id.userFullName);
        userEmail = findViewById(R.id.user_email_address);
        userPhoneNumber = findViewById(R.id.user_phone_number);
        userAdmissionNumber = findViewById(R.id.user_admissionNo);
        userCombination = findViewById(R.id.user_combination);
        userComment = findViewById(R.id.other_user_comment);
        userDp = findViewById(R.id.user_profile_image);
        relativeLayout = findViewById(R.id.editProfileLayout);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        backArrow = findViewById(R.id.backArrow);
        userId = getIntent().getStringExtra("userId");
        Log.i(this.getClass().getName(), "userId" + userId);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");

        contactUserDialog = new Dialog(this);
        contactUserDialog.setContentView(R.layout.message_popup_layout);
        contactUserDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        contactUserDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sendEmail = contactUserDialog.findViewById(R.id.sendEmail);
        sendDM = contactUserDialog.findViewById(R.id.sendSMS);
        cancelArrow = contactUserDialog.findViewById(R.id.cancelArrow);


        full_size_image_dialog = new Dialog(this);
        full_size_image_dialog.setContentView(R.layout.full_size_img_dialog);
        full_size_image_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        full_size_image_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        fullSizeImg = full_size_image_dialog.findViewById(R.id.full_size_img);


        sendEmail.setOnClickListener(v ->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                    sendEmailMessage(email);
                } else {
                    requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS}, 1);
                }
            }
        });

        sendDM.setOnClickListener(v ->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                      sendSMS(phoneNumber);
                } else {
                    requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS}, 1);
                }
            }
        });

        cancelArrow.setOnClickListener(v ->{
            contactUserDialog.dismiss();
        });


        backArrow.setOnClickListener( v ->{
            startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
            Animatoo.INSTANCE.animateSwipeRight(UserProfile.this);
            finish();
        });


        userDp.setOnClickListener(v ->{
            full_size_image_dialog.show();
        });
        if (!Objects.equals(userId, currentUser.getUid())){
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);

        }

        fetchUserData(userId);


     sendSMSorEmailToUser.setOnClickListener(v ->{
         contactUserDialog.show();

     });

     callUser.setOnClickListener(v -> {
         makePhoneCall(phoneNumber);
     });

     addUserToContact.setOnClickListener(v ->{
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                 addToContact(userFullName, phoneNumber);
             } else {
                 requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS}, 1);
             }
         }
     });
     editProfileBtn.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), EditProfile.class));
            Animatoo.INSTANCE.animateSwipeLeft(this);
            finish();
        });
    }

    private void addToContact(TextView userFullName, String phoneNumber) {
        ArrayList<ContentProviderOperation> saveContact = new ArrayList<>();
        saveContact.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
        .build());

        saveContact.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, userFullName)
                .build());

        saveContact.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, saveContact);
        } catch (OperationApplicationException | RemoteException e) {
            e.printStackTrace();
        }
    }

    private void makePhoneCall(String phoneNumber) {

       if (ContextCompat.checkSelfPermission(UserProfile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(UserProfile.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
       } else {
           Intent intent = new Intent(Intent.ACTION_DIAL);
           intent.setData(Uri.parse("tel:" + phoneNumber));
           startActivity(intent);
       }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(phoneNumber);
            } else {
                Toast.makeText(this, "Please Allow the Permission first", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void sendEmailMessage(String email) {
        String subject, body;
        subject = "Email from Sarco Pixel";
        body = "Write the text you want to send here";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Toast.makeText(UserProfile.this, "No email App installed on this device", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendSMS(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + Uri.encode(phoneNumber)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No messaging app found.", Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchUserData(String userId) {
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    firstName = userModel.getFirstName();
                    lastName = userModel.getLastName();
                    email = userModel.getEmail();
                    phoneNumber = userModel.getPhoneNumber();
                    admissionNumber = userModel.getAdmissionNumber();
                    combination = userModel.getCombination();
                    comment = userModel.getComment();
                    profileImg = userModel.getImageUri();

                    userFullName.setText(firstName + " " + lastName);
                    userEmail.setText(email);
                    userPhoneNumber.setText(phoneNumber);
                    userAdmissionNumber.setText(admissionNumber);
                    userCombination.setText(combination);
                    userComment.setText(comment);
                    Picasso.get().load(profileImg).into(userDp);
                    Picasso.get().load(profileImg).into(fullSizeImg);

                       if (!userId.equals(currentUser.getUid())){
                           headerName.setText(firstName + " - Profile");
                       } else {
                           headerName.setText("My Profile");
                       }
                } else {
                    Toast.makeText(UserProfile.this, "Failed to load user data try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Data Loading Failed try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), AlbumActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}