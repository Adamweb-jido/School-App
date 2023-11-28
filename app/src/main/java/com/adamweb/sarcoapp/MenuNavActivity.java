package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MenuNavActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    HomeActivity homeItems = new HomeActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nav);

        drawerLayout = findViewById(R.id.menu_drawer_layout);
        navigationView = findViewById(R.id.menu_nav_view_id);
        homeItems.menu = findViewById(R.id.menuIcon);

        drawerLayout.addDrawerListener((DrawerLayout.DrawerListener) homeItems.menu);
    }
}