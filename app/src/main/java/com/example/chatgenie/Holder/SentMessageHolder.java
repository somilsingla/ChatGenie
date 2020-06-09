package com.example.chatgenie.Holder;

import android.provider.Telephony;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chatgenie.Model.Chat;
import com.example.chatgenie.R;

public class SentMessageHolder extends RecyclerView.ViewHolder {

    TextView messageuser;

    SentMessageHolder(View itemview){
        super(itemview);
        messageuser = (TextView) itemview.findViewById(R.id.user_message_body);

    }

    public void bind (Chat chat){
        messageuser.setText(chat.getMessage());
    }
}
