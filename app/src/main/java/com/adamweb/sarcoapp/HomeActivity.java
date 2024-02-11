package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afdhal_fa.imageslider.ImageSlider;
import com.afdhal_fa.imageslider.model.SlideUIModel;
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


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //------------------Views declaration-------------------------
    ImageView photoAlbum, chats, profile, menu, home;
    RoundedImageView profileDp;
    TextView userName, visitCount, hiUser;
    RecyclerView leaderRecycler, allUsersRecycler;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUserName;
    DatabaseReference userReference;
    String firstName, lastName;
    ProgressBar progressBar;
    ImageSlider imageSlider;
    int counter;
    @SuppressLint("SetTextI18n")
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
        home = findViewById(R.id.homeIcon);
        hiUser = findViewById(R.id.hiUserId);
        chats = findViewById(R.id.chatIcon);
        imageSlider = findViewById(R.id.homeAutoSlider);
        progressBar = findViewById(R.id.home_progress_bar);
        userName = findViewById(R.id.homeUserName);
        visitCount = findViewById(R.id.visitCounter);
        profileDp = findViewById(R.id.profileDp);
        allUsersRecycler = findViewById(R.id.allUsersRecycler);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserName = firebaseAuth.getCurrentUser();
        userReference = FirebaseDatabase.getInstance().getReference("Registered Users");


        setImageSlider();
        home.setOnClickListener(v ->{
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        });

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
            finish();
        });
   //------------------Profile onclickListener-------------------------------
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    //------------------Leaders recyclerview-------------------------------
        //------------------Navigation drawer-------------------------------
        navigationView.setNavigationItemSelectedListener(this);

        //------------------Menu OnclickListener-------------------------------
       menu.setOnClickListener(v -> {
           navigationView.bringToFront();
           drawerLayout.openDrawer(GravityCompat.END);

       });


       profileDp.setOnClickListener(v ->{
           Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
           startActivity(intent);
           finish();
       });

    }

    private void setImageSlider() {


         ArrayList<SlideUIModel> imageList = new ArrayList<>();
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2Fsrimi.jpg?alt=media&token=98c63025-e04c-4e3c-9474-5ecda044f1bc", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FSAADATU-RIMI-COLLEGE.jpg?alt=media&token=b69b32bb-9ca8-4c50-bfdb-3d5e3298dade", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0649.jpeg?alt=media&token=0894f2c6-8a33-47b4-822d-e105f8c13291", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0624.jpeg?alt=media&token=a573e3a3-406c-42f2-adb2-22ba16481a10", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0606.jpeg?alt=media&token=094b1150-2593-48ef-bb46-2764d1c10c53", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0603.jpeg?alt=media&token=4c44ef0e-8f41-4bbc-8929-c0f5f5a1d051", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0596.jpeg?alt=media&token=a9fe974e-b9d9-40cd-821e-6a304020062b", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0588.jpeg?alt=media&token=ac43e15a-06c2-4812-984f-c7fd4abd7495", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0586.jpeg?alt=media&token=a4ab2efb-d1c9-477c-8b7d-03616cd89685", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0559.jpeg?alt=media&token=642dbca2-3e5b-4134-9b32-d0883ffe2fa0", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0554.jpeg?alt=media&token=4c9e71a8-78b9-4093-90e5-41506fed9144", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0549.jpeg?alt=media&token=e9f4f33a-9c23-4ad3-be50-723a0fc6b429", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0543.jpeg?alt=media&token=8b50d687-6014-4c33-87f2-5d055d7dc405", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2Fcollege_new.png?alt=media&token=936fe511-fd94-4da3-bda5-89b6921f501b", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2F3.jpg?alt=media&token=2d80c0c2-a618-42c0-b18b-26075afea737", ""));

        imageSlider.setImageList(imageList);

    }

    private void displayAllUsers() {

        List<UserModel> items = new ArrayList<>();
        AllUsersAdapter usersAdapter = new AllUsersAdapter(items);
        allUsersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        allUsersRecycler.setAdapter(usersAdapter);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    items.add(userModel);
                }

                allUsersRecycler.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Error, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchUserDetails(FirebaseUser currentUserName) {
        String userId = currentUserName.getUid();
        userReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel readUserDetails = snapshot.getValue(UserModel.class);
                if (readUserDetails != null){
                    firstName = readUserDetails.userFirstName;
                    lastName = readUserDetails.userLastName;

                  Picasso.get().load(readUserDetails.getUserImageUri()).into(profileDp);
                    userName.setText(firstName + " " + lastName);
                    hiUser.setText("Hi, " +firstName);
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