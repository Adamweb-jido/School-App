package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {


        ImageView backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        backArrow = findViewById(R.id.backArrow);
        RecyclerView recyclerView = findViewById(R.id.albumRecycler);

        List<Item> items = new ArrayList<>();

        items.add(new Item("Adamu ibrahim ya'u", "realadamweb@gmail.com","csc/che", "08160807055",  R.drawable.adamweb));
        items.add(new Item("Zahraddin Yusif", "zahraddinyusif@gmail.com","csc/che", "08064130342", R.drawable.zdeennn));
        items.add(new Item("Khalifa muhammad", "khalifamuhammad@gmail.com","csc/che", "08131830383",  R.drawable.klipa));
        items.add(new Item("aliyu sunusi", "aliyuhaidar@gmail.com","csc/che", "09063333861",  R.drawable.aliyu));
        items.add(new Item("salisu ibrahim adam", "Salisibrahim@gmail.com","csc/che", "08160569598",  R.drawable.salis));
        items.add(new Item("Hashim Abdullahi", "hashimabdullahi@gmail.com","csc/che", "08134345554",  R.drawable.monita));


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items ));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}