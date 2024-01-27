package com.adamweb.sarcoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardViewHolder> {

    List<OnBoardingItem> items;

    public OnBoardingAdapter(List<OnBoardingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.onboarding_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position) {
            holder.title.setText(items.get(position).getTitle());
            holder.description.setText(items.get(position).getDescription());
            holder.photo.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OnBoardViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;
        ImageView photo;

        public OnBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.onboardTitle);
            description = itemView.findViewById(R.id.onboardText);
            photo = itemView.findViewById(R.id.onboardImage);
        }
    }


}
