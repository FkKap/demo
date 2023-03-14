package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;

import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

public class trimActivity extends AppCompatActivity {

    K4LVideoTrimmer trimmer;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trim);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Intent i = getIntent();
        String filename = i.getStringExtra("filename");
        trimmer = findViewById(R.id.trimmer);


           if (trimmer != null)
           {

               trimmer.setMaxDuration(30);
               trimmer.setDestinationPath(getCacheDir().getAbsolutePath() + "/trim/");
               trimmer.setVideoInformationVisibility(true);
               trimmer.setVideoURI(Uri.parse(filename));
               trimmer.setMaxDuration(30);
           }

           trimmer.setOnTrimVideoListener(new OnTrimVideoListener() {
               @Override
               public void onTrimStarted() {

               }

               @Override
               public void getResult(Uri uri)
               {
                   Log.e("Trimmmmmmmmmmmmmmmmm",uri.toString());
                   callactive(uri);
               }

               @Override
               public void cancelAction() {

               }

               @Override
               public void onError(String message) {

               }
           });


    }

    public void callactive(Uri uri)
    {
        Log.e("trimmmmmmmmmmmmmmmmmmmmmmmmmmmmm",uri.toString());
        File file = new File(uri.getPath());
        Intent i = new Intent(this,ViewVideo.class);
        i.putExtra("trimfile",file.getPath());
        Log.e("ffffffffffffffffffffffffffffffff",file.getPath().toString());
        startActivity(i);

    }
}