package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //------------------Views declaration-------------------------
    ImageView photoAlbum, chats, profile, menu;
    RoundedImageView profileDp;
    TextView userName, visitCount, hiUser;
    RecyclerView leaderRecycler, allUsersRecycler;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUserName;
    String firstName, lastName, profilePic;
    int counter;
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
        profile = findViewById(R.id.profileIcon);
        hiUser = findViewById(R.id.hiUserId);
        chats = findViewById(R.id.chatIcon);
        userName = findViewById(R.id.homeUserName);
        visitCount = findViewById(R.id.visitCounter);
        profileDp = findViewById(R.id.profileDp);
        allUsersRecycler = findViewById(R.id.allUsersRecycler);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserName = firebaseAuth.getCurrentUser();
 //-------------------------------set Counter----------------------
       displayAllUsers();
        counter = CounterUtil.getVisitCount(this);
        visitCount.setText("Your Total visit: "+counter);

        if (currentUserName == null){
            Toast.makeText(this, "Please refresh the page", Toast.LENGTH_SHORT).show();
        } else {
           fetchUserDetails(currentUserName);
        }

   //------------------Album onclickListener-------------------------------
        photoAlbum.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AlbumActivity.class);
            startActivity(intent);
            finish();
        });
        //------------------Chats onclickListener-------------------------------
        chats.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
        });
   //------------------Profile onclickListener-------------------------------
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        });
    //------------------Leaders recyclerview-------------------------------
        //------------------Navigation drawer-------------------------------
        navigationView.setNavigationItemSelectedListener(this);

        //------------------Menu OnclickListener-------------------------------
       menu.setOnClickListener(v -> {
           navigationView.bringToFront();
           drawerLayout.openDrawer(GravityCompat.END);

       });

    }

    private void displayAllUsers() {

        List<AllUsersImg> items = new ArrayList<>();
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));
        items.add(new AllUsersImg(R.drawable.user_profile_dp));

        allUsersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        allUsersRecycler.setAdapter(new AllUsersAdapter(items));
    }


    private void fetchUserDetails(FirebaseUser currentUserName) {
        String userId = currentUserName.getUid();
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        userReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel readUserDetails = snapshot.getValue(UserModel.class);
                if (readUserDetails != null){
                    firstName = readUserDetails.userFirstName;
                    lastName = readUserDetails.userLastName;
                    profilePic = readUserDetails.getUserImageUri();


                    userName.setText(firstName + " " + lastName);
                    hiUser.setText("Hi, " +firstName);
                    Picasso.get().load(profilePic).into(profileDp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Failed to load user data, refresh the page.", Toast.LENGTH_SHORT).show();

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
                finish();
                break;
            case R.id.helpId:
                Intent intent1 = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.myProfileId:
                Intent intent2 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.editProfileId:
                Intent intent3 = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.logoutId:
                Intent intent4 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent4);
                finish();
                break;
        }
        return true;
    }
}