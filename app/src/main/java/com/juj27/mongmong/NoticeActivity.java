package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    ArrayList<RecyclerNoticeItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerNoticeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recyclerView = findViewById(R.id.notice_recycler);
        adapter = new RecyclerNoticeAdapter(this, items);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar_notice);
        setSupportActionBar(toolbar);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayShowTitleEnabled(false);
        ac.setDisplayHomeAsUpEnabled(true);

        load();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void load(){
        items.add(new RecyclerNoticeItem());

    }

}
