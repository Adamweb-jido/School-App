package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    ImageView chatImage;
    TextView chatName, lastMsg, time;


    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        chatName = itemView.findViewById(R.id.chatName);
        lastMsg = itemView.findViewById(R.id.lastChat);
        time = itemView.findViewById(R.id.time);
        chatImage = itemView.findViewById(R.id.chatImage);
    }
}
