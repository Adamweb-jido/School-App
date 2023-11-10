package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ImageView back = findViewById(R.id.backArrow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.friendListRecycler);

        List<FriendsListItem> items = new ArrayList<>();
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));
        items.add(new FriendsListItem("Adamu Yau Ibrahim", "Allahumma Salli Ala Sayyidina....", R.drawable.adamweb));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FriendsAdapter(getApplicationContext(), items));
    }
}