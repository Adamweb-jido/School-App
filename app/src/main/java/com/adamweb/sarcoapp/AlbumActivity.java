package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);


        RecyclerView recyclerView = findViewById(R.id.albumRecycler);
        int numberOfColumns = 2;

        List<Item> items = new ArrayList<>();

        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));
        items.add(new Item("Adamweb Jido", "realadamweb@gmail.com", R.drawable.official));



        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items ));
    }
}