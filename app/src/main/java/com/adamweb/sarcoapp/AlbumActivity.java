package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    RecyclerView albumRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        albumRecyclerView = findViewById(R.id.albumRecycler);

        //Creating the List of the album items

        List<AlbumList> albumLists = new ArrayList<AlbumList>();
        albumLists.add(new AlbumList("Adamu Ibrahim Ya'u", "csc/che", "08160807055", R.drawable.user_profile_dp));
        albumLists.add(new AlbumList("Adamu Ibrahim Ya'u", "csc/che", "08160807055", R.drawable.user_profile_dp));
        albumLists.add(new AlbumList("Adamu Ibrahim Ya'u", "csc/che", "08160807055", R.drawable.user_profile_dp));

        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumRecyclerView.setAdapter(new AlbumAdapter(getApplicationContext(), albumLists));
    }
}