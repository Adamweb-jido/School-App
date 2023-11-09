package com.adamweb.sarcoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    Context context;
    List<ChatList> lists;

    public ChatAdapter(Context context, List<ChatList> lists) {
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

        holder.chatName.setText(lists.get(position).getName());
        holder.lastMsg.setText(lists.get(position).getLastMessage());
        holder.time.setText(lists.get(position).getTime());
        holder.chatImage.setImageResource(lists.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
