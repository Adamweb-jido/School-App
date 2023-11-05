package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    //Creating reference to the views
    ImageView albumImage;
    TextView name, comb, phoneNumber;
    public AlbumViewHolder(@NonNull View itemView) {
        super(itemView);

        albumImage = itemView.findViewById(R.id.albumImage);
        name = itemView.findViewById(R.id.name);
        comb = itemView.findViewById(R.id.combination);
        phoneNumber = itemView.findViewById(R.id.phoneNumber);
    }
}
