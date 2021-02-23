package com.juj27.mongmong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    ArrayList<RecyclerNoticeItem> items = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerNoticeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        load();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.circle_notice);
        adapter = new RecyclerNoticeAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = view.findViewById(R.id.toolbar_notice);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    void load(){
        items.add(new RecyclerNoticeItem());
        items.add(new RecyclerNoticeItem());
        items.add(new RecyclerNoticeItem());
        items.add(new RecyclerNoticeItem());
    }
}
