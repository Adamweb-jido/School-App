package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    Context context;
    List<AlbumItem> albumItems;

    public AlbumAdapter(Context context, List<AlbumItem> albumItems) {
        this.context = context;
        this.albumItems = albumItems;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {

        holder.textView.setText(albumItems.get(position).getName());
        holder.imageView.setImageResource(albumItems.get(position).getImage());
        holder.email.setText(albumItems.get(position).getEmail());
        holder.combination.setText(albumItems.get(position).getCombination());
        holder.phoneNumber.setText(albumItems.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return albumItems.size();
    }
}
