package com.juj27.mongmong;

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
import com.facebook.login.LoginManager;
import com.kakao.sdk.user.UserApiClient;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class InformationExpertFragment extends Fragment {

    CircleImageView circle;
    TextView tvNick;

    @Nullable
    @Override
    //화면보이지는것
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information_expert,container,false);
    }

    //xml 대한것
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout Change;

        Change = view.findViewById(R.id.liner_expert);
        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity 가 보여주고 있는 Fragment를 변경[ fragmens[3]을 show )
                MainActivity ac= (MainActivity)getActivity();
                FragmentTransaction tran = ac.fragmentManager.beginTransaction();
                tran.hide(ac.fragments[4]);
                tran.show(ac.fragments[3]);
                tran.commit();
            }
        });

        TextView registration;
        registration = view.findViewById(R.id.tv_registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListEditActivity.class);
                startActivity(intent);
            }
        });

        //로그인 시 이미지 닉네임 가져오기
        circle = view.findViewById(R.id.expert_circle);
        tvNick = view.findViewById(R.id.et_nick2);

        TextView logout;
        logout = view.findViewById(R.id.expert_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {

                            Login.nickName = null;
                            Login.profileUrl = null;

                            Glide.with(getActivity()).load(R.drawable.ic_person_24).into(circle);

                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);

                        getActivity().finish();

                        return null;
                    }
                });
                
                LoginManager.getInstance().logOut();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvNick.setText(Login.nickName);
        Glide.with(getActivity()).load(Login.profileUrl).into(circle);

    }
}
