package com.example.differentlayouts;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PlayMusic extends AppCompatActivity {

    Button playMusic;
    MediaPlayer musicMedia;
    ProgressBar progressBar;
    TextView nowTime;
    TextView finalTime;
    VideoView videoView;
    Button playVideo;

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
        videoView = findViewById(R.id.videoView);
        playVideo = findViewById(R.id.playVideo);
        videoView.setVideoPath(String.valueOf(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video)));

        playMusic.setOnClickListener(v->{
            if(musicMedia == null) {
                musicMedia = MediaPlayer.create(this, R.raw.song);
                musicMedia.start();
                finalTime.setText(getTimeInMinutes(musicMedia.getDuration()));
                progressBar.setVisibility(VISIBLE);
                progressBar.setMax(musicMedia.getDuration());
                progressBar.setProgress(0);
                updateProgress();
                playMusic.setText("Pause");
                musicMedia.setOnCompletionListener(mp -> playMusic.setText("Play"));
            }else{
                if(musicMedia.isPlaying()) {
                    musicMedia.pause();
                    playMusic.setText("Play");
                }
                else {
                    musicMedia.start();
                    updateProgress();
                    playMusic.setText("Pause");
                }
            }
        });

        playVideo.setOnClickListener(v->{
            if(videoView.isPlaying()){
                videoView.setVisibility(VISIBLE);
                videoView.pause();
            }else{
                videoView.start();
            }

        });


    }

    private void updateProgress() {
        new Thread(() -> {
            while(musicMedia.isPlaying()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(()-> {
                    progressBar.setProgress(musicMedia.getCurrentPosition(), true);
                    nowTime.setText(getTimeInMinutes(musicMedia.getCurrentPosition()));
                });
            }
        }).start();
    }
    private String getTimeInMinutes(int milli){
        return milli/60000+":"+(milli%60000)/1000;
    }
}