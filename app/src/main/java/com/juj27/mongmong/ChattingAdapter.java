package com.juj27.mongmong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ChattingAdapter extends BaseAdapter {

    Context context;
    ArrayList<ChattingVO> items;

    public ChattingAdapter(Context context, ArrayList<ChattingVO> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChattingVO item = items.get(position);
        View itemView = null;

        LayoutInflater inflater = LayoutInflater.from(context);
        if (item.id.equals(Login.nickName)){
            itemView = inflater.inflate(R.layout.my_messagebox, parent, false);
        }else {
            itemView = inflater.inflate(R.layout.other_messagebox, parent, false);
        }
        return itemView;
    }
}
