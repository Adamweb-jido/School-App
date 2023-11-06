package com.adamweb.sarcoapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

public class myViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    RoundedImageView imageView;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.name);
        imageView = itemView.findViewById(R.id.image);
    }
}
