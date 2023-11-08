package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderViewHolder> {

    Context context;
    List<LeadersItem> items;


    public LeaderAdapter(Context context, List<LeadersItem> items) {
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

        holder.leaderName.setText(items.get(position).getLeaderName());
        holder.leaderImage.setImageResource(items.get(position).getLeaderImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
