package com.example.tattify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class listView extends AppCompatActivity {
ImageView musiclogo;
TextView songname;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        musiclogo=findViewById(R.id.musiclogo);
        songname=findViewById(R.id.songname);


    }
}