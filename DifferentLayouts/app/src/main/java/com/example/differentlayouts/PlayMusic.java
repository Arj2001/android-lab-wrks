package com.example.differentlayouts;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayMusic extends AppCompatActivity {

    Button playMusic;
    MediaPlayer media;
    ProgressBar progressBar;
    TextView nowTime;
    TextView finalTime;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_music);
        playMusic = findViewById(R.id.playMusic);
        progressBar = findViewById(R.id.musicProgress);
        nowTime = findViewById(R.id.nowTime);
        finalTime = findViewById(R.id.fullTime);

        playMusic.setOnClickListener(v->{
            if(media == null) {
                media = MediaPlayer.create(this, R.raw.song);
                media.start();
                finalTime.setText(getTimeInMinutes(media.getDuration()));
                progressBar.setVisibility(VISIBLE);
                progressBar.setMax(media.getDuration());
                progressBar.setProgress(0);
                updateProgress();
                playMusic.setText("Pause");
                media.setOnCompletionListener(mp -> playMusic.setText("Play"));
            }else{
                if(media.isPlaying()) {
                    media.pause();
                    playMusic.setText("Play");
                }
                else {
                    media.start();
                    updateProgress();
                    playMusic.setText("Pause");
                }
            }
        });

    }

    private void updateProgress() {
        new Thread(() -> {
            while(media.isPlaying()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(()-> {
                    progressBar.setProgress(media.getCurrentPosition(), true);
                    nowTime.setText(getTimeInMinutes(media.getCurrentPosition()));
                });
            }
        }).start();
    }
    private String getTimeInMinutes(int milli){
        return milli/60000+":"+(milli%60000)/1000;
    }
}