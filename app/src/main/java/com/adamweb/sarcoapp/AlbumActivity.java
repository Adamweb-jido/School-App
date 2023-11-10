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

public class AlbumActivity extends AppCompatActivity {


        ImageView backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        backArrow = findViewById(R.id.backArrow);
        RecyclerView recyclerView = findViewById(R.id.albumRecycler);

        List<AlbumItem> albumItems = new ArrayList<>();

        albumItems.add(new AlbumItem("Adamu ibrahim ya'u", "realadamweb@gmail.com","csc/che", "08160807055", R.drawable.adamweb));
        albumItems.add(new AlbumItem("Zahraddin Yusif", "zahraddinyusif@gmail.com","csc/che", "08064130342", R.drawable.zdeen));
        albumItems.add(new AlbumItem("Khalifa muhammad", "khalifamuhammad@gmail.com","csc/che", "08131830383", R.drawable.klipa));
        albumItems.add(new AlbumItem("aliyu sunusi", "aliyuhaidar@gmail.com","csc/che", "09063333861", R.drawable.aliyu));
        albumItems.add(new AlbumItem("salisu ibrahim adam", "Salisibrahim@gmail.com","csc/che", "08160569598", R.drawable.salis));
        albumItems.add(new AlbumItem("Hashim Abdullahi", "hashimabdullahi@gmail.com","csc/che", "08134345554", R.drawable.monita));


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new AlbumAdapter(getApplicationContext(), albumItems));

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}