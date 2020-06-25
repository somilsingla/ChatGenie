package com.example.chatgenie.Holder;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatgenie.Model.Chat;
import com.example.chatgenie.R;

import java.util.List;

// Holder for the messages sent by the bot

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

    public TextView messagebot;

     public ReceivedMessageHolder(View itemview){
        super(itemview);
        messagebot = (TextView) itemview.findViewById(R.id.bot_message_body);

    }

    public void bind(Chat chat){
        messagebot.setText(chat.getMessage());
    }
}
