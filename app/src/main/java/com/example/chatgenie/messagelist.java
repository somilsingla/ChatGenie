package com.example.chatgenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.chatgenie.Adapters.MessageListAdapter;
import com.example.chatgenie.Model.Chat;

import java.util.ArrayList;
import java.util.List;

public class messagelist extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    private List<Chat> chatList = new ArrayList<Chat>();
    private Chat chat;
    ImageButton imageButton;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagelist);
        chat = new Chat();

        chat.setMessage("Hello! How may I help you?");
        chat.setSender("bot");
        chatList.add(chat);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, chatList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);


        imageButton = (ImageButton) findViewById(R.id.button_chatbox_send);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usermessage();
                Chat temp1 = new Chat();
                temp1.setMessage(message);
                temp1.setSender("user");
                chatList.add(temp1);
                mMessageAdapter = new MessageListAdapter(getApplicationContext(), chatList);
                mMessageRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mMessageRecycler.setAdapter(mMessageAdapter);
                EditText editText = (EditText) findViewById(R.id.edittext_chatbox);
                editText.setText("");


            }
        });

    }

    public void usermessage() {
        EditText edText1 = (EditText) findViewById(R.id.edittext_chatbox);
        message = edText1.getText().toString();
    }



}