package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class aktivnost2 extends AppCompatActivity {

    public TextView TVnaslov;
    public TextView TVopis;
    public TextView TVurl;
    public TextView TVautor;
    public TextView TVdatum;

    public ImageView imgView2;

    String author;
    String title;
    String description;
    String publishedAt;
    String url;
    String urlToImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivnost2);

        TVnaslov = findViewById(R.id.TVnaslov);
        TVopis = findViewById(R.id.TVopis);
        TVurl = findViewById(R.id.TVurl);
        TVautor = findViewById(R.id.TVautor);
        TVdatum = findViewById(R.id.TVdatum);
        imgView2 = findViewById(R.id.imgView2);

        Intent intent = getIntent();
        description = intent.getStringExtra("key_description");
        author = intent.getStringExtra("key_author");
        title = intent.getStringExtra("key_title");
        publishedAt = intent.getStringExtra("key_publishedAt");
        url = intent.getStringExtra("key_url");
        urlToImage = intent.getStringExtra("key_urlToImage");

        this.setTitle(title);

        postavi();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);}


    public void postavi(){
        TVopis.setText(description);
        TVnaslov.setText(title);
        TVurl.setText("Za više informacija, pogledajte:\n" + url);
        TVautor.setText("Autor:\n" + author);
        TVdatum.setText("Datum:\n" + publishedAt);

        Picasso.get().load(urlToImage).into(imgView2);
    }
}
