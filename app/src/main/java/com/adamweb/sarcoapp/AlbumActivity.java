package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlbumActivity extends AppCompatActivity {


        ImageView backArrow;
        CircleImageView profileIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        profileIcon = findViewById(R.id.profileIcon);
        backArrow = findViewById(R.id.backArrow);
        RecyclerView recyclerView = findViewById(R.id.albumRecycler);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");


        List<UserModel> albumItems = new ArrayList<>();
        AlbumAdapter albumAdapter = new AlbumAdapter(getApplicationContext(), albumItems);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(albumAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserModel albumItem = dataSnapshot.getValue(UserModel.class);
                    albumItems.add(albumItem);
                }
                recyclerView.setAdapter(albumAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AlbumActivity.this, "Failed refresh the page", Toast.LENGTH_SHORT).show();
            }
        });

        assert firebaseUser != null;
        databaseReference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    Picasso.get().load(userModel.getImageUri()).into(profileIcon);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AlbumActivity.this, "Error, Try again", Toast.LENGTH_SHORT).show();
            }
        });


        profileIcon.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSwipeLeft(this);
            finish();
        });


        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}