package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ImageView photoAlbum;
    RecyclerView leaderRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acivity);

        photoAlbum = findViewById(R.id.albumIcon);
        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
                startActivity(intent);
            }
        });

        leaderRecycler = findViewById(R.id.leadersRecycler);

        List<LeadersItem> items = new ArrayList<>();
        items.add(new LeadersItem("provost", R.drawable.user_dp));
        items.add(new LeadersItem("Dean Science", R.drawable.user_profile_dp));
        items.add(new LeadersItem("H. O. D", R.drawable.user_profile_dp));
        items.add(new LeadersItem("Level Co-ordinator", R.drawable.user_dp));

        //recyclerView
        leaderRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        leaderRecycler.setAdapter(new LeaderAdapter(getApplicationContext(), items));
    }
}