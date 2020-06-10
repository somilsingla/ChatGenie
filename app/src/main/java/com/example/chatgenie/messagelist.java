package com.example.chatgenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.chatgenie.Adapters.MessageListAdapter;
import com.example.chatgenie.Model.Chat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class messagelist extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    private List<Chat> chatList = new ArrayList<Chat>();
    private Chat chat;
    ImageButton imageButton;
    String message;
    String botmessage;
    JSONObject query = new JSONObject();
    JSONObject reply = new JSONObject();

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
                try {
                    query.put("query",temp1.getMessage().toString());
                } catch (JSONException e) {
                    Log.d("OKHTTP3","JSON Exception");
                    e.printStackTrace();
                }

                OkHttpClient okHttpClient = new OkHttpClient();
                String url = "";

                MediaType JSON = MediaType.parse("application/json");
                RequestBody requestBody = RequestBody.create(JSON, query.toString() );
                Request request = new Request.Builder()
                                    .url(url)
                                    .post(requestBody).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.isSuccessful()) {
                        reply = new JSONObject(response.toString());
                        botmessage = reply.getString("response");
                    }
                } catch (IOException | JSONException e) {
                    Log.d("OKHTTP","Exception while requesting");
                    e.printStackTrace();
                }

                Chat temp2 = new Chat();
                temp2.setMessage(botmessage);
                temp2.setSender("bot");
                chatList.add(temp2);
                mMessageAdapter = new MessageListAdapter(getApplicationContext(), chatList);
                mMessageRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mMessageRecycler.setAdapter(mMessageAdapter);

            }
        });

    }

    public void usermessage() {
        EditText edText1 = (EditText) findViewById(R.id.edittext_chatbox);
        message = edText1.getText().toString();
    }



}