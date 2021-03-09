package com.juj27.mongmong;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerListAdapter extends RecyclerView.Adapter <RecyclerListAdapter.VH> {

    Context context;
    ArrayList<RecyclerListItem> items;

    public RecyclerListAdapter(Context context, ArrayList<RecyclerListItem> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_list_item, parent,false);
        VH vh = new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        RecyclerListItem item = items.get(position);
        String imgUrl = "http://you2021.dothome.co.kr/MongMong/"+item.img;
        Log.i("tag", imgUrl);
        Glide.with(context).load(imgUrl).into(holder.iv);

        holder.tvTitle.setText(item.title);
        holder.tvMsg.setText(item.msg);
        holder.tvPrice.setText(item.price+"원");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvTitle, tvMsg, tvPrice;
        ToggleButton tbFavor;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv_retrofit);
            tvTitle= itemView.findViewById(R.id.tv_title);
            tvMsg= itemView.findViewById(R.id.tv_message);
            tvPrice= itemView.findViewById(R.id.tv_price);
            tbFavor= itemView.findViewById(R.id.tb_btn);

            tbFavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int position = getLayoutPosition();
                    RecyclerListItem item = items.get(position);

                    item.favor = isChecked?1:0;

                    Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
                    RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                    Call<RecyclerListItem> call = retrofitService.updateData("updateFavor.php",item);
                    call.enqueue(new Callback<RecyclerListItem>() {
                        @Override
                        public void onResponse(Call<RecyclerListItem> call, Response<RecyclerListItem> response) {

                        }

                        @Override
                        public void onFailure(Call<RecyclerListItem> call, Throwable t) {

                        }
                    });
                }
            });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getAdapterPosition();
                        RecyclerListItem item = items.get(position);

                        ArrayList<String> list = new ArrayList<>();
                        list.add("의뢰하기");
                        list.add("메세지보내기");
                        CharSequence[] items = list.toArray(new String[list.size()]);

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(items[which] == items[0]){

                                    if(Login.nickName !=null){

                                        String title = item.title;
                                        String img = item.img;
                                        String message = item.msg;
                                        String price = item.price;

                                        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceScalars();
                                        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

                                        Map<String, String> dataPart = new HashMap<>();
                                        dataPart.put("nick", Login.nickName);
                                        dataPart.put("title", title  );
                                        dataPart.put("img",img);
                                        dataPart.put("msg", message);
                                        dataPart.put("price", price);

                                        Call<String> call = retrofitService.postRequestDataToServer(dataPart);
                                        call.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                String s = response.body();
                                                Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(context, RequestActivity.class);
                                                context.startActivity(intent);
                                                ((MainActivity)context).finish();
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Toast.makeText(context, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.i("LOG","error:"+t.getMessage());
                                            }
                                        });

                                    }else {

                                        Intent intent = new Intent(context, LoginActivity.class);
                                        ((MainActivity)context).startActivityForResult(intent, 300);
                                    }
                                }else {
                                    if(Login.nickName !=null){
                                        Intent intent = new Intent(context,MessageActivity.class);
                                        context.startActivity(intent);
                                    }else {
                                        Intent intent = new Intent(context, LoginActivity.class);
                                        ((MainActivity)context).startActivityForResult(intent, 400);
                                    }

                                }
                            }
                        });

                        builder.show();
                    }
                });
        }
    }

}

