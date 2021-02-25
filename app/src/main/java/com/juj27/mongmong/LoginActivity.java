package com.juj27.mongmong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonObject;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;



public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton fbLoginButton;

    String id;
    String pw;

    EditText etId, etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etId = findViewById(R.id.et_login_id);
        etPw = findViewById(R.id.et_login_pw);

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

                            setResult(RESULT_OK);//이 액티비트를 실행한 결과가 OK
                            finish();

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

                            setResult(RESULT_OK);//이 액티비트를 실행한 결과가 OK
                            finish();

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
    public void clickMember(View view) {
        Intent intent = new Intent(this, MemberActivity.class);
        startActivity(intent);
    }

    public void clickLogin(View view) {
        id=etId.getText().toString();
        pw=etPw.getText().toString();

        if (id==null||id.equals("")||pw==null||pw.equals("")){
            Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(id);
        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()==null){
                    Toast.makeText(LoginActivity.this, "존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String str = dataSnapshot.getValue(String.class );
                if (pw.equals(str)){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Login.ID = id;

                    setResult(RESULT_OK);//이 액티비트를 실행한 결과가 OK
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}