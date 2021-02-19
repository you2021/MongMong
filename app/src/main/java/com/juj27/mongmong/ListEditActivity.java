package com.juj27.mongmong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class ListEditActivity extends AppCompatActivity {

    EditText etName,etTitle,etMsg,etPrice;
    ImageView iv;

    String imgPath;

    Spinner spinner, spinner2;
    String[][] subCategories = new String[][]{
            {"선택"},
        {"aa","bb","aff","aafd","asda"},
        {"a4546a","b123b","af1231f","53aafd","a3sda"},
        {"4546a","b123b","af1231f","53aafd","a3sda"},
        {"a446a","b123b","af1231f","53aafd","a3sda"},
        {"a","b123b","af1231f","53aafd","a3sda"},
        {"46a","b123b","af1231f","53aafd","a3sda"},
        {"6a","b123b","af1231f","53aafd","a3sda"},
        {"546a","b123b","af1231f","53aafd"},
        {"a4546a","b123b","af1231f"},
        {"a4546a","b123b","af1231f","53aafd","a3sda"},
        {"a4546a","b123b","53aafd","a3sda"},
        {"af1231f","53aafd","a3sda"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        etName = findViewById(R.id.et_name);
        etTitle = findViewById(R.id.et_title);
        etMsg = findViewById(R.id.et_msg);
        etPrice = findViewById(R.id.et_price);
        iv = findViewById(R.id.iv_list);

        spinner = findViewById(R.id.spinner);
        spinner2= findViewById(R.id.spinner2);
        ArrayAdapter adapter = new ArrayAdapter(ListEditActivity.this, R.layout.spinner_selected,subCategories[0]);
        spinner2.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                ArrayAdapter adapter = new ArrayAdapter(ListEditActivity.this, R.layout.spinner_selected,subCategories[position]);
                spinner2.setAdapter(adapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void clickSelect(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10 && resultCode==RESULT_OK){
            Uri uri = data.getData();
            if(uri!=null) {
                Glide.with(this).load(uri).into(iv);

                imgPath = getRealPathFromUri(uri);

//                new AlertDialog.Builder(this).setMessage(imgPath).show();
            }
        }
    }

    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }



    public void clickComplete(View view) {

        String name = etName.getText().toString();
        String title = etTitle.getText().toString();
        String msg = etMsg.getText().toString();
        String price = etPrice.getText().toString();

        String category=spinner.getSelectedItem().toString();
        String subcategory = spinner2.getSelectedItem().toString();


        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService retrofitService = retrofit.create((RetrofitService.class));

        MultipartBody.Part filePart = null;
        if(imgPath !=null){
            File file = new File(imgPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            filePart = MultipartBody.Part.createFormData("img", file.getName(),requestBody);

        }

        Map<String, String> dataPart = new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("title", title);
        dataPart.put("msg", msg);
        dataPart.put("price", price);
        dataPart.put("category",category);
        dataPart.put("subcategory",subcategory);

        Call<String> call = retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(ListEditActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                Log.i("tag",""+s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ListEditActivity.this, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("tag","aa:"+t.getMessage());

            }
        });

        finish();
    }
}