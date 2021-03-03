package com.juj27.mongmong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RequesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<RequestItem> items;

    public RequesAdapter(Context context, ArrayList<RequestItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_list_item, parent,false);
        VH vh = new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        VH vh = (VH)holder;

        RequestItem item = items.get(position);
        vh.tvTitle.setText(item.title);
        vh.tvMsg.setText(item.message);
        vh.tvPrice.setText(item.price);

        Glide.with(context).load(item.img).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvTitle;
        TextView tvMsg;
        TextView tvPrice;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv_retrofit);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvMsg = itemView.findViewById(R.id.tv_message);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}

