package com.juj27.mongmong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerNoticeAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<RecyclerNoticeItem> items;

    public RecyclerNoticeAdapter(Context context, ArrayList<RecyclerNoticeItem> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_notice_item,parent,false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        RecyclerNoticeItem item = items.get(position);

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
