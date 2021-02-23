package com.juj27.mongmong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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

        //전문가 모드로 화면 변경 버튼
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

        //의뢰내역 화면 넘어가기 버튼
        TextView tvRegistration;
        tvRegistration = view.findViewById(R.id.tv_registration);

        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), ListEditActivity.class);
               startActivity(intent);

            }
        });

        //로그인 시 이미지 닉네임 가져오기
        CircleImageView circle;
        TextView tvNick;
        circle = view.findViewById(R.id.circle);
        tvNick = view.findViewById(R.id.et_nick);

        tvNick.setText(Login.nickName);
        Glide.with(getActivity()).load(Login.profileUrl).into(circle);

        //로그아웃
        TextView logout;
        logout = view.findViewById(R.id.client_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {

                            Login.nickName = null;
                            Login.profileUrl = null;

                            tvNick.setText(Login.nickName);
                            Glide.with(getActivity()).load(Login.profileUrl).into(circle);
                            Glide.with(getActivity()).load(R.drawable.ic_person_24).into(circle);


                        return null;
                    }
                });
            }
        });







    }
}
