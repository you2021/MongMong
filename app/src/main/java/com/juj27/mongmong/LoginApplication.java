package com.juj27.mongmong;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class LoginApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Kakao SDK 초기화
        KakaoSdk.init(this, "639c23f95bfc9eab7c45542074e773a8");
    }
}
