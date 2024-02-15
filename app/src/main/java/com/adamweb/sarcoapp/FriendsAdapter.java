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
    List<UserModel> items;

    public FriendsAdapter(Context context, List<UserModel> items) {
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
       UserModel userModel = items.get(position);
        Picasso.get().load(userModel.imageUri).into(holder.imageCover);
        holder.fName.setText(userModel.getFirstName());
        holder.comment.setText(userModel.getComment());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
