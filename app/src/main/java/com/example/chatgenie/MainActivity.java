package com.example.chatgenie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

// Main Activity containing the button to access the chat-bot

public class MainActivity extends AppCompatActivity {
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OnCLickListener to start the chat-bot

        button = (ImageButton) findViewById(R.id.botButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmessagelist();
            }
        });
    }

    public void openmessagelist() {
        Intent intent = new Intent(this, messagelist.class);
        startActivity(intent);
    }
}