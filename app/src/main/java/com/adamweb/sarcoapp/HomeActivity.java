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
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afdhal_fa.imageslider.ImageSlider;
import com.afdhal_fa.imageslider.model.SlideUIModel;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
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
    ImageView photoAlbum, chats, profile, menu, home, quoteImg;
    RoundedImageView profileDp;
    TextView userName, visitCount, hiUser, quoteText, quoteName;
    RecyclerView leaderRecycler, allUsersRecycler;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUserName;
    DatabaseReference userReference;
    String firstName, lastName;
    ProgressBar progressBar;
    ImageSlider imageSlider;
     String[]  texts = {"\"Friendship is the hardest thing in the world to explain. It's not something you learn in school. But if you haven't learned the meaning of friendship, you really haven't learned anything.\"",
             "\"Friendship is born at that moment when one person says to another, 'What! You too? I thought I was the only one.\"",
             "\"Friendship is the shadow of the evening, which increases with the setting sun of life.\"",
             "\"My mission in life is not merely to survive, but to thrive; and to do so with some passion, some compassion, some humor, and some style.\"",
             "\"The function of education is to teach one to think intensively and to think critically. Intelligence plus character - that is the goal of true education.\""};
     String  [] names = {"-Muhammad Ali", "-C.S Lewis", " - Jean de La Fontaine", "- Maya Angelou", "- Martin Luther King Jr."};
     int [] imgs = {R.drawable.mali,
                    R.drawable.lewis,
                    R.drawable.jean,
                    R.drawable.maya,
                    R.drawable.martin};
     int counter, currentIndex = 0, imgCounterIndex = 0;
    private Handler handler;

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
        quoteName = findViewById(R.id.quoteName);
        chats = findViewById(R.id.chatIcon);
        quoteText = findViewById(R.id.quoteTextContainerId);
        quoteImg = findViewById(R.id.quoteImageId);
        imageSlider = findViewById(R.id.homeAutoSlider);
        progressBar = findViewById(R.id.home_progress_bar);
        userName = findViewById(R.id.homeUserName);
        visitCount = findViewById(R.id.visitCounter);
        profileDp = findViewById(R.id.profileDp);
        allUsersRecycler = findViewById(R.id.allUsersRecycler);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserName = firebaseAuth.getCurrentUser();
        userReference = FirebaseDatabase.getInstance().getReference("Registered Users");


        handler = new Handler();
        handler.postDelayed(textSliderRunnable, 0);


        setImageSlider();
        setLeadersItems();
        displayAllUsers();

        home.setOnClickListener(v ->{
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        });

        //-------------------------------set Counter----------------------
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
            Animatoo.INSTANCE.animateSwipeLeft(this);
        });
        //------------------Chats onclickListener-------------------------------
        chats.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
            finish();
            Animatoo.INSTANCE.animateSwipeLeft(this);
        });
   //------------------Profile onclickListener-------------------------------
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
            Animatoo.INSTANCE.animateSwipeLeft(this);
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
           Animatoo.INSTANCE.animateSwipeLeft(this);
       });

    }


    private final Runnable textSliderRunnable = new Runnable() {
        @Override
        public void run() {
            // Update text and increment index
            quoteText.setText(texts[currentIndex]);
            quoteName.setText(names[currentIndex]);
            quoteImg.setImageResource(imgs[imgCounterIndex]);
            currentIndex = (currentIndex + 1) % texts.length;
            currentIndex = (currentIndex + 1) % names.length;
            imgCounterIndex = (imgCounterIndex + 1) % imgs.length;
            handler.postDelayed(this, 20000);
        }
    };



    private void setLeadersItems() {
        List<LeaderItem> items = new ArrayList<>();
        items.add(new LeaderItem("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/Leaders%2Fprovost.jpg?alt=media&token=59946c96-ba1c-4b87-87c9-e0fddcb780eb", "Provost"));
        items.add(new LeaderItem("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/Leaders%2Fregistrer.png?alt=media&token=45266c8c-77e6-4e45-826d-2d09cf672b20", "Registerer"));
        items.add(new LeaderItem("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/Leaders%2FWhatsApp%20Image%202024-02-14%20at%2016.58.36_24a0da1b.jpg?alt=media&token=220d7817-abb6-4a11-9094-69810ccdc68d", "H.O.D"));
        items.add(new LeaderItem("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/Leaders%2Fuser_profile_dp.jpeg?alt=media&token=da7be77e-8be0-4a4e-a5f1-297abf3927d8", "Level Coordinator"));
        leaderRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        leaderRecycler.setAdapter(new LeaderAdapter(this, items));

    }

    private void setImageSlider() {


         ArrayList<SlideUIModel> imageList = new ArrayList<>();
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2Fsrimi.jpg?alt=media&token=98c63025-e04c-4e3c-9474-5ecda044f1bc", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FSAADATU-RIMI-COLLEGE.jpg?alt=media&token=b69b32bb-9ca8-4c50-bfdb-3d5e3298dade", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0649.jpeg?alt=media&token=0894f2c6-8a33-47b4-822d-e105f8c13291", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0624.jpeg?alt=media&token=a573e3a3-406c-42f2-adb2-22ba16481a10", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0606.jpeg?alt=media&token=094b1150-2593-48ef-bb46-2764d1c10c53", ""));
        imageList.add(new SlideUIModel("https://firebasestorage.googleapis.com/v0/b/sarco-pixel.appspot.com/o/ImageSliders%2FIMG_0586.jpeg?alt=media&token=a4ab2efb-d1c9-477c-8b7d-03616cd89685", ""));
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
                    firstName = readUserDetails.getFirstName();
                    lastName = readUserDetails.getLastName();

                  Picasso.get().load(readUserDetails.getImageUri()).into(profileDp);
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
        moveTaskToBack(true);
       android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
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
                Animatoo.INSTANCE.animateSwipeRight(this);
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
                Animatoo.INSTANCE.animateSwipeRight(this);
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