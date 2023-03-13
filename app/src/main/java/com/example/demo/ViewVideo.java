package com.example.demo;

import static android.net.Uri.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class ViewVideo extends AppCompatActivity {

    VideoView viewVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i = new Intent();
        String filepath = i.getStringExtra("trimfile");

        VideoView videoView =(VideoView)findViewById(R.id.video);

        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

        Log.e("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFf",filepath);
        //specify the location of media file

        Uri uri= Uri.parse(filepath);

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(filepath);
        videoView.requestFocus();
        videoView.start();


    }
}