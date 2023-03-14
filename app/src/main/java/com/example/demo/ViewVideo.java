package com.example.demo;

import static android.net.Uri.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class ViewVideo extends AppCompatActivity {

    VideoView viewVideo;
    TextView time;
    String filepath;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i = getIntent();
         filepath = i.getStringExtra("trimfile");
        Log.e("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFf",filepath);

        viewVideo =(VideoView)findViewById(R.id.video);
        time = (TextView)findViewById(R.id.time);

        //Creating MediaController
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(viewVideo);


        //specify the location of media file

        Uri uri= Uri.parse(filepath);

        //Setting MediaController and URI, then starting the videoView
        viewVideo.setMediaController(mediaController);
        viewVideo.setVideoPath(filepath);
        viewVideo.requestFocus();
        viewVideo.start();





    }


}