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

// Chat-bot activity

public class messagelist extends AppCompatActivity {

    // Making the variables for the chat items and the recycler view and it's adapter

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

        // Hard coded greeting by the chat-bot is displayed in the view

        chat.setMessage("Hello! How may I help you?");
        chat.setSender("bot");
        chatList.add(chat);

        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, chatList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);

        // User sending query

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

                // Putting the user query as a JSON object
                try {
                    query.put("messages", temp1.getMessage().toString());
                } catch (JSONException e) {
                    Log.d("OKHTTP3","JSON Exception");
                    e.printStackTrace();
                }

                // Setting up a HTTP Client to post request to backend

                OkHttpClient okHttpClient = new OkHttpClient();
                String url = "http://104.215.195.198/api/messages/";

                // Posting request to the backend server
                MediaType JSON = MediaType.parse("application/json");
                RequestBody requestBody = RequestBody.create(query.toString(),JSON);
                Request request = new Request.Builder()
                                    .url(url)
                                    .addHeader("Content-Type","application/json")
                                    .addHeader("Accept","*/*")
                                    .addHeader("Host","104.215.195.198")
                                    .post(requestBody).build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        botmessage = "Failure";
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        messagelist.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    // Receiving the response from the server and displaying

                                    if (response.isSuccessful()) {

                                        assert response.body() != null;
                                        reply = new JSONObject(response.body().string());
                                        botmessage = reply.getString("message");

                                    } else {
                                        Log.d("Fail","Response not succesful");
                                        botmessage = "Response is not succesful";
                                    }

                                    Chat temp2 = new Chat();
                                    temp2.setMessage(botmessage);
                                    temp2.setSender("bot");
                                    chatList.add(temp2);
                                    mMessageAdapter = new MessageListAdapter(getApplicationContext(), chatList);
                                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    mMessageRecycler.setAdapter(mMessageAdapter);


                                }catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });

                    }
                });



            }
        });

    }

    //Getting the query entered by the user

    public void usermessage() {
        EditText edText1 = (EditText) findViewById(R.id.edittext_chatbox);
        message = edText1.getText().toString();
    }

}