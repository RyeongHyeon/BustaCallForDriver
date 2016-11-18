package com.bustacallfordriver.user.bustacallfordriver.service;

//import android.util.Log;
//
//import com.example.user.bustacall.AppController;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//
//import java.io.IOException;
//
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by user on 2016-10-31.
 * //
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService { //여기는 안건드려도되

    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);
        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        AppController app = (AppController)getApplicationContext();
        app.setSavedToken(token);
    }
}