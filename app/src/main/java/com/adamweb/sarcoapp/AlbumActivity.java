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

        List<Item> items = new ArrayList<>();
        items.add(new Item("Adamweb", R.drawable.camera));
        items.add(new Item("Adamweb", R.drawable.camera));
        items.add(new Item("Adamweb", R.drawable.camera));
        items.add(new Item("Adamweb", R.drawable.camera));

        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumRecyclerView.setAdapter(new myAdapter(getApplicationContext(), items));

    }
}