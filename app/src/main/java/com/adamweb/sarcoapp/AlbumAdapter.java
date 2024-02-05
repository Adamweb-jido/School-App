package com.adamweb.sarcoapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {


     public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView userAlbumCover;
        TextView albumName, email, combination, phoneNumber;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            userAlbumCover = itemView.findViewById(R.id.myImage);
            email = itemView.findViewById(R.id.email);
            albumName = itemView.findViewById(R.id.name);
            combination = itemView.findViewById(R.id.combination);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

        }
    }
    Context context;
    List<UserModel> albumItems;

    public AlbumAdapter(Context context, List<UserModel> albumItems) {
        this.context = context;
        this.albumItems = albumItems;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Uri profileImage = firebaseUser.getPhotoUrl();
        Picasso.get().load(profileImage).into(holder.userAlbumCover);
        holder.albumName.setText(albumItems.get(position).getUserFirstName());
        holder.email.setText(firebaseUser.getEmail());
        holder.combination.setText(albumItems.get(position).getUserCombination());
        holder.phoneNumber.setText(albumItems.get(position).getUserPhoneNo());
    }


    @Override
    public int getItemCount() {
        return albumItems.size();
    }

}
