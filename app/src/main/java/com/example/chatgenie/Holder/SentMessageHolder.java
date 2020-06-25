package com.example.chatgenie.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatgenie.Model.Chat;
import com.example.chatgenie.R;

// Holder for the messages sent by the user

public class SentMessageHolder extends RecyclerView.ViewHolder {

    public TextView messageuser;

    public SentMessageHolder(View itemview){
        super(itemview);
        messageuser = (TextView) itemview.findViewById(R.id.user_message_body);

    }

}
