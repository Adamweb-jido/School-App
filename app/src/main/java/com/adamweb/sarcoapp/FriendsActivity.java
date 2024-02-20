package com.adamweb.sarcoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

public class FriendsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView profileDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ImageView back = findViewById(R.id.backArrow);
        profileDp = findViewById(R.id.profileIcon);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });

        profileDp.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), CurrentUserProfile.class));
            Animatoo.INSTANCE.animateSwipeRight(this);
            finish();
        });
        recyclerView = findViewById(R.id.friendListRecycler);

        List<UserModel> items = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users");
        FriendsAdapter friendsAdapter = new FriendsAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(friendsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    items.add(userModel);
                }

                recyclerView.setAdapter(friendsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FriendsActivity.this, "Failed to fetch info try again", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                if (userModel != null){
                    Picasso.get().load(userModel.getImageUri()).into(profileDp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FriendsActivity.this, "Wrong try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), ChatActivity.class));
        Animatoo.INSTANCE.animateSwipeRight(this);
        finish();
    }
}