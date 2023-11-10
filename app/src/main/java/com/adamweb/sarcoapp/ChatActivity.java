package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
                startActivity(intent);
            }
        });
        List<ChatList> lists = new ArrayList<>();
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Hashim Abdullahi", "10:46 PM", "Girmanka ne Monita", R.drawable.monita));
        lists.add(new ChatList("Khalifa Muhammad", "11:20 AM", "ka gama aikin kuwa?", R.drawable.klipa));
        lists.add(new ChatList("Nura Ahmad Sulaiman", "7:15 PM", "m nura ya kasuwa", R.drawable.user_dp));
        lists.add(new ChatList("Aliyu Sunusi", "8:45 PM", "Eh Hakane Kam", R.drawable.aliyu));
        lists.add(new ChatList("Salisu Ibrahim Adam", "8:59 PM", "Alhmdlh mlm ya kasuwa", R.drawable.salis));
        lists.add(new ChatList("Abdullahi Mustapha", "9:07 PM", "Zanzo gobe", R.drawable.user_profile_dp));
        lists.add(new ChatList("Adam Sulaiman Adam", "9:28 PM", "sai mun samu lokaci", R.drawable.user_dp));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));


        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setAdapter(new ChatAdapter(getApplicationContext(), lists));
    }
}