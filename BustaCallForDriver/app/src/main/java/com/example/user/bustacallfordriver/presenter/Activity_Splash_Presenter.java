package com.example.user.bustacallfordriver.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.model.User;
import com.example.user.bustacallfordriver.view.Activity_Main;
import com.example.user.bustacallfordriver.view.Activity_Signin;
import com.example.user.bustacallfordriver.view.Activity_Splash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-01.
 */

public class Activity_Splash_Presenter {
    Activity_Splash view;
    AppController app;

    public Activity_Splash_Presenter(Activity_Splash view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    /**로그인 체크*/
    public void checkLogin() {
        app.getUser().setUser_PhoneNum(app.getSavedId("id","0"));
        if(app.getUser().getUser_PhoneNum().equals("0"))
        {
            Intent intent = new Intent(view, Activity_Signin.class);
            view.startActivity(intent);
        }else{
            request_mainlogin();
        }
    }

    // 서버 통신 어떻게 되는지 확인 후 진행하자

    public void request_mainlogin(){
        String phonenum = app.getSavedId("id","0");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<User> retrofitinfo = retrofit_user.request_mainlogin(phonenum);
        retrofitinfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    app.setUser(response.body());
                    app.TENDER_COUNT = response.body().getTenderList().size(); //처음에 전체 사이즈 받아오기
                    Intent intent = new Intent(view, Activity_Main.class);
                    view.startActivity(intent);
                }else{
                    Log.d("test",response.toString());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }
}
