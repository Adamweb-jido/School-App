package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView chatRecycler;
    ImageView backArrow, profilePic;
    MaterialButton newChat, album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRecycler = findViewById(R.id.chatRecycler);
        newChat = findViewById(R.id.newChatBtn);
        album = findViewById(R.id.albumBtn);
        backArrow = findViewById(R.id.backArrow);
        profilePic = findViewById(R.id.profileIcon);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        album.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
            startActivity(intent);
        });



        assert firebaseUser != null;
        databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    Picasso.get().load(userModel.getUserImageUri()).into(profilePic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChatActivity.this, "Error, Try again", Toast.LENGTH_SHORT).show();
            }
        });


        profilePic.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
            // onBackPressed();
        });

        newChat.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
            startActivity(intent);
        });
        List<UserModel> lists = new ArrayList<>();
        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setAdapter(new ChatAdapter(getApplicationContext(), lists));
    }
}