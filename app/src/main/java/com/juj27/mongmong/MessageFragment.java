package com.juj27.mongmong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class MessageFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayout linearLayout;

    ArrayList<String>items;
    ArrayList<Integer> serverNum;
    ChattingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.chattingRecyclerView);
        linearLayout = view.findViewById(R.id.linear_message);
        items = new ArrayList<>();
        serverNum = new ArrayList<>();
        adapter = new ChattingListAdapter(getContext(), items, serverNum);
        recyclerView.setAdapter(adapter);
        //리스트뷰의 아이템이 비어 있을때 보여지는 뷰를 설정


    }

    @Override
    public void onStart() {
        super.onStart();

        onloadChattingData();
    }
    void onloadChattingData(){
        items.clear();
        serverNum.clear();
        FirebaseDatabase.getInstance().getReference("chat").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String [] chatNames = ds.getKey().split("&&");
                    ArrayList<String> chatNamesArray = new ArrayList<>(Arrays.asList(chatNames));
                    int index = chatNamesArray.indexOf(G.myId);
                    if (index == 0){
                        items.add(chatNamesArray.get(1));
                        serverNum.add(1);
                    }else if (index == 1){
                        items.add(chatNamesArray.get(0));
                        serverNum.add(0);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
