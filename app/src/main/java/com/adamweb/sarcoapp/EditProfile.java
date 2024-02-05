package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends AppCompatActivity {

    TextView slideOne, slideTwo;
    EditText editFirstName, editLastName, editPhoneNumber, editEmailAddress, editComment;
    MaterialButton saveChangesBtn, cancelChangesBtn;
    ImageView backArrow, profilePic;
    FloatingActionButton floatingActionButton;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}