package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderViewHolder extends RecyclerView.ViewHolder {

    //accessing the views
    TextView leaderName;
    ImageView leaderImage;

    public LeaderViewHolder(@NonNull View itemView) {
        super(itemView);

        leaderImage = itemView.findViewById(R.id.leaderImg);
        leaderName = itemView.findViewById(R.id.leaderName);
    }
}
