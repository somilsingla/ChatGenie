package com.example.chatgenie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatgenie.Holder.ReceivedMessageHolder;
import com.example.chatgenie.Holder.SentMessageHolder;
import com.example.chatgenie.Model.Chat;
import com.example.chatgenie.R;

import java.util.List;

// Adapter for the message list

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mcontext;
    private List<Chat> chatlist;


    public MessageListAdapter(Context context, List<Chat> messagelist){
        mcontext = context;
        chatlist= messagelist;
    }


    // Gets the no. of items in the arraylist

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    // Returns the type of item i.e. sent or received
    public int getItemViewType (int position) {
        Chat chat = (Chat) chatlist.get(position);
        if ((chat.getSender()).equals("user")) {
            return VIEW_TYPE_MESSAGE_SENT;
        }
        else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the view with the type of message
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        View view;

        if (viewtype == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent,parent,false);
            return new SentMessageHolder(view);
        }
        else if (viewtype == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received,parent,false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }

    // Binds the view with the message
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = (Chat) chatlist.get(position);

        switch (holder.getItemViewType()){
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).messageuser.setText(chat.getMessage());
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).messagebot.setText(chat.getMessage());
        }
    }

    public class SentMessageHolder extends RecyclerView.ViewHolder {

        public TextView messageuser;

        public SentMessageHolder(View itemview){
            super(itemview);
            messageuser = (TextView) itemview.findViewById(R.id.user_message_body);

        }

    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        public TextView messagebot;

        public ReceivedMessageHolder(View itemview) {
            super(itemview);
            messagebot = (TextView) itemview.findViewById(R.id.bot_message_body);

        }

    }

}
