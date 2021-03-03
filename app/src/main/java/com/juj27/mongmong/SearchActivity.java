package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class SearchActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    ArrayList<String> locationItems = new ArrayList<>();
    String[] locationData;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayShowTitleEnabled(false);
        ac.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list);
        editText = findViewById(R.id.et_search);

        adapter = ArrayAdapter.createFromResource(this, R.array.search, R.layout.listview_item);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listView.setFilterText(editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().length() == 0){
                    listView.clearTextFilter();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void clickSearch(View view) {
        Toast.makeText(this, "아직 적용이 되지 않았습니다. 추후 적용될 예정될 것입니다.", Toast.LENGTH_SHORT).show();
    }
}