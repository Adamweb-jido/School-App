package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ImageView back = findViewById(R.id.backArrow);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            Animatoo.INSTANCE.animateSwipeRight(this);
            startActivity(intent);
            finish();
        });

        recyclerView = findViewById(R.id.friendListRecycler);

        List<UserModel> items = new ArrayList<>();

        FriendsAdapter friendsAdapter = new FriendsAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(friendsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), ChatActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}