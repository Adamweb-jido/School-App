package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //------------------Views declaration-------------------------
    ImageView photoAlbum, chats, profile, menu;
    RecyclerView leaderRecycler;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acivity);


   //------------------Accessing views. Hooks---------------------
        menu = findViewById(R.id.menuIcon);
        navigationView = findViewById(R.id.menu_nav_view_id);
        photoAlbum = findViewById(R.id.albumIcon);
        leaderRecycler = findViewById(R.id.leadersRecycler);
        drawerLayout = findViewById(R.id.menu_drawer_layout);
        profile = findViewById(R.id.userProfileIcon);
        chats = findViewById(R.id.chatIcon);

   //------------------Album onclickListener-------------------------------
        photoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //------------------Chats onclickListener-------------------------------
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
   //------------------Profile onclickListener-------------------------------
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    //------------------Leaders recyclerview-------------------------------
        List<LeadersItem> items = new ArrayList<>();
        items.add(new LeadersItem("Provost", R.drawable.user_profile_dp));
        items.add(new LeadersItem("Registerer", R.drawable.user_profile_dp));
        items.add(new LeadersItem("H. O. D", R.drawable.user_profile_dp));
        items.add(new LeadersItem("Level Coordinator", R.drawable.user_profile_dp));

        leaderRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        leaderRecycler.setAdapter(new LeaderAdapter(getApplicationContext(), items));

        //------------------Navigation drawer-------------------------------
        navigationView.setNavigationItemSelectedListener(this);

        //------------------Menu OnclickListener-------------------------------
       menu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               navigationView.bringToFront();
               drawerLayout.openDrawer(GravityCompat.END);

           }
       });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.darkMode:
                break;
            case R.id.photoAlbumId:
                Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
                startActivity(intent);
                break;
            case R.id.helpId:
                Intent intent1 = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent1);
                break;
            case R.id.myProfileId:
                Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent2);
                break;

        }
        return true;
    }
}