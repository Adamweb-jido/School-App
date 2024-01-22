package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    RoundedImageView userAlbumCover;
    TextView albumName, email, combination, phoneNumber;
    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);
String me;
        userAlbumCover = itemView.findViewById(R.id.myImage);
        email = itemView.findViewById(R.id.email);
        albumName = itemView.findViewById(R.id.name);
        combination = itemView.findViewById(R.id.combination);
        phoneNumber = itemView.findViewById(R.id.phoneNumber);

    }
}
