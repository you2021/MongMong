package com.juj27.mongmong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class InformationFragment extends Fragment {

    @Nullable
    @Override
    //화면보이지는것
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information_client,container,false);
    }

    //xml 대한것
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout tvChange;

        tvChange = view.findViewById(R.id.liner_client);
        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity 가 보여주고 있는 Fragment를 변경[ fragmens[4]을 show )
                MainActivity ac= (MainActivity)getActivity();
                FragmentTransaction tran = ac.fragmentManager.beginTransaction();
                tran.hide(ac.fragments[3]);
                tran.show(ac.fragments[4]);
                tran.commit();
            }
        });

        TextView tvRegistration;

        tvRegistration = view.findViewById(R.id.tv_registration);
        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), ListEditActivity.class);
               startActivity(intent);

            }
        });


    }
}
