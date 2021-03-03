package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;

    ArrayList<ChattingVO> items;
    ChattingAdapter adapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        listView = findViewById(R.id.chatting_listview);
        editText = findViewById(R.id.et_chat);

        items = new ArrayList<>();

        adapter = new ChattingAdapter(this, items);
        listView.setAdapter(adapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("chat").child(G.currentItem.masterID+"&&"+Login.nickName);
        if (getIntent().getStringExtra("server") != null){
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    ChattingVO item = snapshot.getValue(ChattingVO.class);
                    items.add(item);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(items.size()-1);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void clickSend(View view) {

        String data = editText.getText().toString();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String visualTime = new SimpleDateFormat("HH : mm").format(new Date());

        if (data.equals("")) return;

        databaseReference.child(time).setValue(new ChattingVO(Login.nickName, data, visualTime)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MessageActivity.this, "아직 적용이 되지 않았습니다. 추후 적용될 예정될 것입니다.", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });
    }
}