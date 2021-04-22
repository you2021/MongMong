package com.juj27.mongmong;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    ArrayList<Recycler_adItem> items0 = new ArrayList<>();
    RecyclerView recyclerView0;
    Recycler_adAdapter recyclerAdapter0;

    ArrayList<HomeRecyclerItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    HomeRecyclerAdapter recyclerAdapter;

    ArrayList<HomeRecyclerItem> items2 = new ArrayList<>();
    RecyclerView recyclerView2;
    HomeRecyclerAdapter recyclerAdapter2;

    ArrayList<RecyclerListItem> listItems = new ArrayList<>();
    RecyclerView recyclerView3;
    RecyclerListAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
        setHasOptionsMenu(true);
    }

    //이 액티비티가 화면에 보여질 때 리사이클러가 보여줄 아이템들을 서버DB에서 불러오기
    @Override
    public void onResume() {
        super.onResume();
        retrofitLoadData();
    }

    //option메뉴
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_main, menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
            case R.id.search:
                //Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent2);

                break;

            case android.R.id.home:
               // Toast.makeText(getActivity(), "bbb", Toast.LENGTH_SHORT).show();

                if (Login.nickName !=null ){
                    Intent intent = new Intent(getActivity(), NoticeActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public  static final int REQUEST_CODE = 11;

    //Intent 결과
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE  && resultCode == Activity.RESULT_OK ){
            Toast.makeText(getActivity(), "결과OK", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), NoticeActivity.class);
            startActivity(intent);
        }
    }

    //서버에서 데이커를 불러오는 기능메소드
    void  retrofitLoadData(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<RecyclerListItem>> call = retrofitService.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<RecyclerListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<RecyclerListItem>> call, Response<ArrayList<RecyclerListItem>> response) {
                // 기존데이터들 모두제거
                listItems.clear();
                listAdapter.notifyDataSetChanged();;

                //결과로 받아온 item에 추가
//                ArrayList<RecyclerListItem> list =  response.body();
//                for(RecyclerListItem item : list){
//                    listItems.add(0,item);
//                    listAdapter.notifyItemInserted(0);
//                }
            }
            @Override
            public void onFailure(Call<ArrayList<RecyclerListItem>> call, Throwable t) {
                Toast.makeText(getActivity(), "실패"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_notifications_24);

        recyclerView0 = view.findViewById(R.id.recycler_ad);
        recyclerAdapter0 = new Recycler_adAdapter(getActivity(),items0);
        recyclerView0.setAdapter(recyclerAdapter0);

//        recyclerView = view.findViewById(R.id.recycler_ma);
//        recyclerAdapter = new HomeRecyclerAdapter(getActivity(),items);
//        recyclerView.setAdapter(recyclerAdapter);
//
//        recyclerView2 = view.findViewById(R.id.recycler_it);
//        recyclerAdapter2 = new HomeRecyclerAdapter(getActivity(),items2);
//        recyclerView2.setAdapter(recyclerAdapter2);

        recyclerView3 = view.findViewById(R.id.recycler_se);
        listAdapter = new RecyclerListAdapter(getActivity(), listItems);
        recyclerView3.setAdapter(listAdapter);

    }

    void loadData(){



        items0.add(new Recycler_adItem(R.drawable.ad_1));
        items0.add(new Recycler_adItem(R.drawable.ad_2));


//        items.add(new HomeRecyclerItem());
//        items.add(new HomeRecyclerItem());
//        items.add(new HomeRecyclerItem());
//        items.add(new HomeRecyclerItem());
//        items.add(new HomeRecyclerItem());
//        items.add(new HomeRecyclerItem());
//
//        items2.add(new HomeRecyclerItem());
//        items2.add(new HomeRecyclerItem());
//        items2.add(new HomeRecyclerItem());
//        items2.add(new HomeRecyclerItem());
//        items2.add(new HomeRecyclerItem());
//        items2.add(new HomeRecyclerItem());
    }
}
