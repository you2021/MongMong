package com.juj27.mongmong;

import android.content.Context;
import android.renderscript.Script;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerListAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<RecyclerListItem> items;

    public RecyclerListAdapter(Context context, ArrayList<RecyclerListItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_list_item, parent,false);
        VH vh = new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RecyclerListItem item = items.get(position);
        String imgUrl = "http://you2021.dothome.co.kr/MongMong/"+item.imgUrl;




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }




}

