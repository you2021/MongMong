package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.juj27.mongmong.R;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity {

    ArrayList<RequestItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    RequesAdapter listAdapter;

    RequestItem item = new RequestItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.toolbar_request);
        setSupportActionBar(toolbar);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayShowTitleEnabled(false);
        ac.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String img = intent.getStringExtra("img");
        String message = intent.getStringExtra("message");
        String price = intent.getStringExtra("price");

        item.title = title;
        item.img = img;
        item.message = message;
        item.price = price;

        items.add(item);

        recyclerView = findViewById(R.id.request_recycler);
        listAdapter = new RequesAdapter(this, items);
        recyclerView.setAdapter(listAdapter);



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}