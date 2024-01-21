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

    }
}
