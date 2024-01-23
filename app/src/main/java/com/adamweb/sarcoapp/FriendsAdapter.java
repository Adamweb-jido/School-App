package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendListViewHolder> {


    public static class FriendListViewHolder extends RecyclerView.ViewHolder {

        TextView fName, comment;
        ImageView imageCover;

        public FriendListViewHolder(@NonNull View itemView) {
            super(itemView);

            fName = itemView.findViewById(R.id.friendName);
            comment = itemView.findViewById(R.id.friendComment);
            imageCover = itemView.findViewById(R.id.friendsImage);
        }
    }
    Context context;
    List<FriendsListItem> items;

    public FriendsAdapter(Context context, List<FriendsListItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public FriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendListViewHolder(LayoutInflater.from(context).inflate(R.layout.friend_list_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendListViewHolder holder, int position) {

        holder.fName.setText(items.get(position).getfName());
        holder.comment.setText(items.get(position).getComment());
        holder.imageCover.setImageResource(items.get(position).getImageCover());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
