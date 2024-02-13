package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder> {


    public static class LeaderViewHolder extends RecyclerView.ViewHolder {

        //accessing the views
        TextView leaderName;
        ImageView leaderImage;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            leaderImage = itemView.findViewById(R.id.leaderImg);
            leaderName = itemView.findViewById(R.id.leaderName);
        }
    }
    Context context;
    List<LeaderItem> items;

    public LeaderAdapter(Context context, List<LeaderItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderViewHolder(LayoutInflater.from(context).inflate(R.layout.leader_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        LeaderItem leaderItem = items.get(position);
        Picasso.get().load(leaderItem.getImageUri()).into(holder.leaderImage);
        holder.leaderName.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
