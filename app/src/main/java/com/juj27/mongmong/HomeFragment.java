package com.juj27.mongmong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<HomeRecyclerItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    HomeRecyclerAdapter recyclerAdapter;

    ArrayList<HomeRecyclerItem> items2 = new ArrayList<>();
    RecyclerView recyclerView2;
    HomeRecyclerAdapter recyclerAdapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_ma);
        recyclerAdapter = new HomeRecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView2 = view.findViewById(R.id.recycler_it);
        recyclerAdapter2 = new HomeRecyclerAdapter(getActivity(),items2);
        recyclerView2.setAdapter(recyclerAdapter2);

    }

    void loadData(){
        items.add(new HomeRecyclerItem());
        items.add(new HomeRecyclerItem());
        items.add(new HomeRecyclerItem());
        items.add(new HomeRecyclerItem());
        items.add(new HomeRecyclerItem());
        items.add(new HomeRecyclerItem());

        items2.add(new HomeRecyclerItem());
        items2.add(new HomeRecyclerItem());
        items2.add(new HomeRecyclerItem());
        items2.add(new HomeRecyclerItem());
        items2.add(new HomeRecyclerItem());
        items2.add(new HomeRecyclerItem());
    }


}
