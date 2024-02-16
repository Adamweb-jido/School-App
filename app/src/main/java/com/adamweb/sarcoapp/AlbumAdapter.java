package com.adamweb.sarcoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        UserModel userModel = albumItems.get(position);
        Picasso.get().load(userModel.getImageUri()).into(holder.userAlbumCover);
        holder.albumName.setText(userModel.getFirstName() +"  "+ userModel.getLastName());
        holder.combination.setText(userModel.getCombination());
        holder.phoneNumber.setText(userModel.getPhoneNumber());
        holder.email.setText(userModel.getComment());


        holder.userAlbumCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserProfile.class);
                intent.putExtra("userId", userModel.getUid());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return albumItems.size();
    }

}
