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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {


    public static class ChatViewHolder extends RecyclerView.ViewHolder {

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


    Context context;
    List<UserModel> lists;

    public ChatAdapter(Context context, List<UserModel> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_list_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
