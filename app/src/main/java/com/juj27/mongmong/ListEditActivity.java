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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        etName = findViewById(R.id.et_name);
        etTitle = findViewById(R.id.et_title);
        etMsg = findViewById(R.id.et_msg);
        etPrice = findViewById(R.id.et_price);
        iv = findViewById(R.id.iv_list);

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

                new AlertDialog.Builder(this).setMessage(imgPath).show();
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

        Call<String> call = retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(ListEditActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ListEditActivity.this, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        finish();
    }
}