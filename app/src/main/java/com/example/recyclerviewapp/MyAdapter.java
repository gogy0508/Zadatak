package com.example.recyclerviewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{

    private List<ListItem> listItems;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //kada kreiramo ViewHolder, odnosno, kad se stvori instanca u ViewHolder klasi
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // nakon onCreateViewHoldera, prikazuje podatke

        final ListItem listItem = listItems.get(position);

        holder.naslovTV.setText(listItem.getNaslov());
        Picasso.get().load(listItem.getUrlToImage()).into(holder.imgView);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, aktivnost2.class);

                intent.putExtra("key_title", listItem.getNaslov());
                intent.putExtra("key_description", listItem.getOpis());
                intent.putExtra("key_author", listItem.getAutor());
                intent.putExtra("key_url", listItem.getUrl());
                intent.putExtra("key_urlToImage", listItem.getUrlToImage());
                intent.putExtra("key_publishedAt", listItem.getDatum());

                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView naslovTV;

        public ImageView imgView;
        public RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            naslovTV = itemView.findViewById(R.id.naslovTV);
            imgView = itemView.findViewById(R.id.imgView);

            relativeLayout = itemView.findViewById(R.id.relativeLayoutID);

        }
    }


}
