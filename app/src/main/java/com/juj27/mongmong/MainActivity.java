package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.sdk.common.util.Utility;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments = new Fragment[6];
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //동적퍼미션
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, permissions, 100);
        }



        //툴바 제목줄 대체
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //제목글씨 보이지않도록
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        //알림설정 하기
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_notifications_24);

        //BattomNavigationView 설정
        fragmentManager = getSupportFragmentManager();

        //home이 기본 선택된 상태 화면에 놓기
        FragmentTransaction tran = fragmentManager.beginTransaction();
        fragments[0] = new HomeFragment();
        tran.add(R.id.container, fragments[0]);
        tran.commit();

        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //다른 fragment 로 변경
                FragmentTransaction tran = fragmentManager.beginTransaction();
                if(fragments[0] !=null) tran.hide(fragments[0]);
                if(fragments[1] !=null) tran.hide(fragments[1]);
                if(fragments[2] !=null) tran.hide(fragments[2]);
                if(fragments[3] !=null) tran.hide(fragments[3]);
                if(fragments[4] !=null) tran.hide(fragments[4]);
                if(fragments[5] !=null) tran.hide(fragments[5]);

                switch (item.getItemId()){
                    case  R.id.bnv_home:
                        tran.show(fragments[0]);
                        break;

                    case  R.id.bnv_message:
                        if(fragments[1] == null){
                            fragments[1] = new MessageFragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;

                    case  R.id.bnv_category:
                        if(fragments[2] == null){
                            fragments[2] = new CategoryFragment();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;

                    case  R.id.bnv_information:
                        if(fragments[3] == null){
                            fragments[3] = new InformationFragment();
                            tran.add(R.id.container, fragments[3]);

                            //전문가모드 추가 및 시작은 안보이도록
                            fragments[4]= new InformationExpertFragment();
                            tran.add(R.id.container, fragments[4]);
                            tran.hide(fragments[4]);

                        }
                        tran.show(fragments[3]);
                        break;
                }
                tran.commit();
                return true;
            }
        });
    }

    //OptionMenu 설정정
//   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = this.getMenuInflater();
//        inflater.inflate(R.menu.option_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.search:
//                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
//                break;
//
//            case android.R.id.home:
//
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
