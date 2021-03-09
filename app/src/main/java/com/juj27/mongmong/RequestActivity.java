package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestActivity extends AppCompatActivity {

    ArrayList<RequestItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.toolbar_request);
        setSupportActionBar(toolbar);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayShowTitleEnabled(false);
        ac.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.request_recycler);
        adapter = new RequestAdapter(this, items);
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<RequestItem>> call = retrofitService.loadRequestDataFromServer();
        call.enqueue(new Callback<ArrayList<RequestItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RequestItem>> call, Response<ArrayList<RequestItem>> response) {
                items.clear();
                adapter.notifyDataSetChanged();

                ArrayList<RequestItem> list = response.body();
                for (RequestItem item : list){
                    items.add(0,item);
                    adapter.notifyItemInserted(0);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RequestItem>> call, Throwable t) {
                Toast.makeText(RequestActivity.this, "실패"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}