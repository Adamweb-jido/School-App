package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView chatRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRecycler = findViewById(R.id.chatRecycler);

        List<ChatList> lists = new ArrayList<>();
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));
        lists.add(new ChatList("Zahraddin Yusif", "9:35 AM", "yaushe zamu shiga", R.drawable.zdeennn));


        chatRecycler.setLayoutManager(new LinearLayoutManager(this));
        chatRecycler.setAdapter(new ChatAdapter(getApplicationContext(), lists));
    }
}