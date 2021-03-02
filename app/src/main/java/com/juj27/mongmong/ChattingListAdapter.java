package com.juj27.mongmong;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListAdapter.VH> {

    Context context;
    ArrayList<String> items;
    ArrayList<Integer> serverNum;

    public ChattingListAdapter(Context context, ArrayList<String> items, ArrayList<Integer> serverNum) {
        this.context = context;
        this.items = items;
        this.serverNum = serverNum;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.chatting_list_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvChattingListID.setText(items.get(position) + "님 과의 대화방");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView tvChattingListID;

        public VH(@NonNull View itemView){
            super(itemView);

            tvChattingListID = itemView.findViewById(R.id.tv_chatting_list_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MessageActivity.class);
                    int pos = getAdapterPosition();

                    if (serverNum.get(pos) == 1){
                        intent.putExtra("server",Login.nickName+"&&"+ items.get(pos));
                    }else{
                        intent.putExtra("server", items.get(pos) + "&&" + G.myId);
                    }
                    context.startActivity(intent);
                }
            });

        }
    }
}
