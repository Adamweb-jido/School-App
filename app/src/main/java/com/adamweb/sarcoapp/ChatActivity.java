package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

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


        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        album.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
            startActivity(intent);
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