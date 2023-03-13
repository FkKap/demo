package com.example.demo;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder>
{

    Context context;
    ArrayList<String> listofVideos;



    public adapter(Context context,ArrayList<String> listofVideos) {
        this.context = context;
        this.listofVideos = listofVideos;

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.iteam, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        Glide.with(context).asBitmap().load(Uri.fromFile(new File(listofVideos.get(position)))).into(viewHolder.imageView);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

        Intent intent = new Intent(view.getContext(),trimActivity.class);
        intent.putExtra("filename",listofVideos.get(position));
        context.startActivity(intent);


            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);


        }
    }

    @Override
    public int getItemCount() {
        return listofVideos.size();
    }

}