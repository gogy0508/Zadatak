package com.example.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String phpURL = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>(); // svi podaci koji ce se prikazati spremaju se u listItems

        spajanje();

        //Timer za refresh svakih 5 min
        final Handler handler = new Handler();
        final int delay = 300000; //milisekundi (5 min)

        handler.postDelayed(new Runnable(){
            public void run(){
                spajanje();
                handler.postDelayed(this, delay);
            }
        }, delay);

        }

        private void spajanje(){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Učitavanje...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, phpURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray array = jsonObject.getJSONArray("articles");

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject o = array.getJSONObject(i);
                            ListItem item = new ListItem (
                                    o.getString("title"),
                                    o.getString("description"),
                                    o.getString("author"),
                                    o.getString("publishedAt"),
                                    o.getString("url"),
                                    o.getString("urlToImage")
                            );
                            listItems.add(item);

                        }
                        progressDialog.dismiss();


                        adapter = new MyAdapter(listItems, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                            builder1.setTitle("Greška");
                            builder1.setMessage("Ups, došlo je do pogreške. Pogreška: " + error.getMessage());
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "Ponovi",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            spajanje();
                                            dialog.dismiss();
                                        }
                                    });

                            builder1.setNegativeButton(
                                    "Izađi",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.exit(0);
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

    }
}
