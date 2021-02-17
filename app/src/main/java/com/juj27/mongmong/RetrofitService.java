package com.juj27.mongmong;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitService {

    //서버로 보내기
    @Multipart
    @POST("/MongMong/insertDB.php")
    Call<String> postDataToServer(@PartMap Map<String, String> dataPart, @Part MultipartBody.Part filePaet);

    //서버에서 데이터를 json으로 파싱하여 가져오는 추상메소드
    @GET("/MongMong/loadDB.php")
    Call<ArrayList<RecyclerListItem>> loadDataFromServer();

    //"좋아요" 클릭으로 데이터의 변경을 시키는 작업을 해주는 php를 실행시키기
    @PUT("/MongMong/{fileName}")
    Call<RecyclerListItem> updateData(@Part("fileName") String fileName, @Body RecyclerListItem item);
}
