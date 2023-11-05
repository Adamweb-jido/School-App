package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    Context context;
    List<AlbumList> myAlbumList;

    public AlbumAdapter(Context context, List<AlbumList> myAlbumList) {
        this.context = context;
        this.myAlbumList = myAlbumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.album_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.name.setText(myAlbumList.get(position).getName());
        holder.comb.setText(myAlbumList.get(position).getCombination());
        holder.phoneNumber.setText(myAlbumList.get(position).getPhoneNumber());
        holder.albumImage.setImageResource(myAlbumList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return myAlbumList.size();
    }
}
