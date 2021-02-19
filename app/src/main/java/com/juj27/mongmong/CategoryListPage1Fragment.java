package com.juj27.mongmong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryListPage1Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<RecyclerListItem> items = new ArrayList<>();
    RecyclerListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //데이터 불러오기
        items.add(new RecyclerListItem(1,"ㅇㅇ","","dd","ㅁㅁ","22",0,"233"));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_page1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new RecyclerListAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);
    }

    void loadData(){

            Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
            RetrofitService retrofitService = retrofit.create(RetrofitService.class);
            Call<ArrayList<RecyclerListItem>> call = retrofitService.loadDataFromServer();
            call.enqueue(new Callback<ArrayList<RecyclerListItem>>() {
                @Override
                public void onResponse(Call<ArrayList<RecyclerListItem>> call, Response<ArrayList<RecyclerListItem>> response) {
//                    // 기존데이터들 모두제거
//                    Items.clear();
//                    listAdapter.notifyDataSetChanged();;
//
//                    //결과로 받아온 item에 추가
//                    ArrayList<RecyclerListItem> list =  response.body();
//                    for(RecyclerListItem item : list){
//                        listItems.add(0,item);
//                        listAdapter.notifyItemInserted(0);
//                    }
                }

                @Override
                public void onFailure(Call<ArrayList<RecyclerListItem>> call, Throwable t) {
                    Toast.makeText(getActivity(), "실패"+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
    }

}