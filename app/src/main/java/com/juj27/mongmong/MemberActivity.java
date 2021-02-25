package com.juj27.mongmong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class MemberActivity extends AppCompatActivity {

    EditText etID, etPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        etID = findViewById(R.id.et_id);
        etPW = findViewById(R.id.et_pw);

    }
        //데이터 저장
    public void clickJoin(View view) {

        String id = etID.getText().toString();
        String pw = etPW.getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        databaseReference.child(id).setValue(pw).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MemberActivity.this, "회원가입이 되었습니다.", Toast.LENGTH_SHORT).show();

                Login.ID = id;
                Intent intent = new Intent(MemberActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });





    }
}