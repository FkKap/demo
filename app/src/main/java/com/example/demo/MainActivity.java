package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_GALLERY_VIDEO = 777;
    RecyclerView recyclerView;
    ArrayList<String> listofVideo = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);


        permission();




    }




    public ArrayList<String> getAllVideoList(Activity activity)
    {
        Uri uri1;
        Cursor cursor1;
        int column_index_data1;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri1 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Log.e("uri",uri1.toString());

       String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};



        cursor1 = activity.getContentResolver().query(uri1, projection, null,
                null, null);


        Log.e("cursor1", cursor1.toString());
        column_index_data1 = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);



        while (cursor1.moveToNext()) {
            absolutePathOfImage = cursor1.getString(column_index_data1);



            String[] words = absolutePathOfImage.split("\\.");
            if (!words[words.length - 1].equals("gif")) {

                listOfAllImages.add(absolutePathOfImage);
            } else {
                Log.e("remove", "done");
            }
        }
        return listOfAllImages;

    }

    public void setAdapter()
    {

        listofVideo = getAllVideoList(MainActivity.this);
        Log.e("helllllooo", String.valueOf(listofVideo.size()));
        adapter Adapter = new adapter(this, listofVideo);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(Adapter);
    }

    public void permission()
    {
        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                Log.e("permission","11111");

                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {
                    Log.e("permission","222222");
                } else
                {

                    Log.e("permission","333333");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_TAKE_GALLERY_VIDEO);

                }
            }else
            {
                setAdapter();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_TAKE_GALLERY_VIDEO)
        {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Log.e("permission","44444");
                setAdapter();
            }else
            {
                Log.e("permission","55555");
                Toast.makeText(this, "Permission Not Granted For Gallery", Toast.LENGTH_LONG).show();
            }
        }
    }



}






