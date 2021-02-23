package com.juj27.mongmong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;



public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton fbLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //키 해시값
        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("KeyHash", keyHash);

        //페이스북
        callbackManager = CallbackManager.Factory.create();

        fbLoginButton = findViewById(R.id.login_button);
        fbLoginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("Log", object.toString());
                        try {
                            String name = object.getString("name");
                            JSONObject jo =object.getJSONObject("picture");
                            JSONObject data = jo.getJSONObject("data");
                            String url = data.getString("url");
                            Log.i("Log",name+":"+url);

                            Login.nickName= name;
                            Login.profileUrl = url;

                            Intent intent = new Intent(LoginActivity.this, NoticeActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this, "응답", Toast.LENGTH_SHORT).show();
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id,name,email,picture");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();



            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "로그인 취소", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "로그인 실패:"+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("LOG",error.getMessage());
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    //카카오톡
    public void clickKakao(View view) {
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                    @Override
                    public Unit invoke(User user, Throwable throwable) {
                        
                        if (oAuthToken != null){
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            long id = user.getId();

                            String nickName = user.getKakaoAccount().getProfile().getNickname();
                            String profilUrl = user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                            Login.nickName = nickName;
                            Login.profileUrl = profilUrl;

                        }else {
                            Toast.makeText(LoginActivity.this, "로그인 실패"+throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        
                        return null;
                    }
                });
                return null;
            }
        });

    }

}