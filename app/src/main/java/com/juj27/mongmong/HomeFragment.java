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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView0 = view.findViewById(R.id.recycler_ad);
        recyclerAdapter0 = new Recycler_adAdapter(getActivity(),items0);
        recyclerView0.setAdapter(recyclerAdapter0);

        recyclerView = view.findViewById(R.id.recycler_ma);
        recyclerAdapter = new HomeRecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView2 = view.findViewById(R.id.recycler_it);
        recyclerAdapter2 = new HomeRecyclerAdapter(getActivity(),items2);
        recyclerView2.setAdapter(recyclerAdapter2);

        recyclerView3 = view.findViewById(R.id.recycler_se);
        listAdapter = new RecyclerListAdapter(getActivity(), listItems);
        recyclerView3.setAdapter(listAdapter);

    }

    void loadData(){

        items0.add(new Recycler_adItem());
        items0.add(new Recycler_adItem());
        items0.add(new Recycler_adItem());
        items0.add(new Recycler_adItem());
        items0.add(new Recycler_adItem());
        items0.add(new Recycler_adItem());

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

        listItems.add(new RecyclerListItem());
        listItems.add(new RecyclerListItem());
        listItems.add(new RecyclerListItem());
        listItems.add(new RecyclerListItem());
        listItems.add(new RecyclerListItem());
        listItems.add(new RecyclerListItem());
    }


}
