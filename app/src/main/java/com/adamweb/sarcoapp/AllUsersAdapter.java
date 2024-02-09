package com.adamweb.sarcoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.AllUsersViewHolder> {


    List<UserModel> items;

    public AllUsersAdapter(List<UserModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_reg_users_layout, parent, false);
        return new AllUsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUsersViewHolder holder, int position) {
        Picasso.get().load(new UserModel().getUserImageUri()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class AllUsersViewHolder extends RecyclerView.ViewHolder{

        CircleImageView userImage;
        public AllUsersViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.allUsersImgId);
        }
    }
}
