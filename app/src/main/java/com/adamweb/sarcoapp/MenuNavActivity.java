package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MenuNavActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nav);


        drawerLayout.addDrawerListener((DrawerLayout.DrawerListener) homeItems.menu);
    }
}