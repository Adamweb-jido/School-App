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

        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 2 == 0) {
                    // Every item spans two columns
                    return 2;
                } else {
                    // Other items span one column
                    return 1;
                }
            }
        });


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items ));
    }
}