package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListViewHolder extends RecyclerView.ViewHolder {

    TextView fName, comment;
    ImageView imageCover;

    public FriendListViewHolder(@NonNull View itemView) {
        super(itemView);

        fName = itemView.findViewById(R.id.friendName);
        comment = itemView.findViewById(R.id.friendComment);
        imageCover = itemView.findViewById(R.id.friendsImage);
    }
}
