package com.example.tattify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class MYSONG extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        updateSeek.interrupt();

    }

    ImageView PlayPause, previous, next;
    SeekBar seekBar;
    TextView songName;
    MediaPlayer mediaPlayer;
    ArrayList<File> songs;
    int position;
    String textContent;
    Handler handler;
    Thread updateSeek;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysong);
        PlayPause = findViewById(R.id.PlayPause);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekBar);
        songName = findViewById(R.id.SongName);
        songName.setSelected(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songs = (ArrayList) bundle.getParcelableArrayList("songList");
        textContent = intent.getStringExtra("currentSong");
        songName.setText(textContent);
        position = intent.getIntExtra("position", 0);
        Uri uri = Uri.parse(songs.get(position).toString());
        mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.start();
        seekBar.setMax(mediaPlayer.getDuration());




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(seekBar.getProgress());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        updateSeek = new Thread() {
            @Override
            public void run() {
                super.run();
                int currentPosition=0;
                try {
                    while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);

                        sleep(100000); // Adjust the sleep duration as needed
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();

        PlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    PlayPause.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    PlayPause.setImageResource(R.drawable.pause);
                }
            }
        });




        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.reset();
                mediaPlayer.stop();
                mediaPlayer.release();

                if(position!=0){
                    position=position-1;
                }
                else{ position=songs.size()-1;}
                Uri uri = Uri.parse(songs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                textContent=songs.get(position).getName().toString();
                PlayPause.setImageResource(R.drawable.pause);
                songName.setText(textContent) ;
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.reset();
                mediaPlayer.stop();
                mediaPlayer.release();
                if(position!=songs.size()-1){
                    position=position+1;
                }
                else{ position=0;}
                Uri uri = Uri.parse(songs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                textContent=songs.get(position).getName().toString();
                PlayPause.setImageResource(R.drawable.pause);
                songName.setText(textContent) ;
            }

        });


}}
