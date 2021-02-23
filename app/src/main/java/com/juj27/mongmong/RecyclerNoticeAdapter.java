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

public class RecyclerNoticeAdapter extends RecyclerView.Adapter<RecyclerNoticeAdapter.VH> {

    Context context;
    ArrayList<RecyclerNoticeItem> items;

    public RecyclerNoticeAdapter(Context context, ArrayList<RecyclerNoticeItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_notice_item, parent,false);
        VH vh = new VH(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        RecyclerNoticeItem item = items.get(position);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

//        TextView tvDay, tvSay, tvTime;
//        CircleImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

//            tvDay= itemView.findViewById(R.id.tv_day);
//            tvSay= itemView.findViewById(R.id.tv_say);
//            tvTime= itemView.findViewById(R.id.tv_time);
//            iv= itemView.findViewById(R.id.circle_notice);


        }
    }


}
