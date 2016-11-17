package com.example.user.bustacallfordriver.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.dialog.Dialog_Progress;
import com.example.user.bustacallfordriver.dialog.Dialog_base;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Activity_Sliding_Setting;
import com.example.user.bustacallfordriver.view.Activity_Splash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-17.
 */

public class Activity_Sliding_Setting_Presenter {

    Activity_Sliding_Setting view;
    Dialog_Progress dialog_progress;
    AppController app;

    public Activity_Sliding_Setting_Presenter(Activity_Sliding_Setting view) {
        this.view = view;
        app = (AppController) view.getApplicationContext();
        dialog_progress = new Dialog_Progress(view);
    }

    public void request_logout(String bus_num){
        Log.d("Test",app.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_logout(bus_num);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(view, Activity_Splash.class);
                    view.startActivity(intent);
                }else{
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view,"엥..? 로그아웃 안됨ㅋ");
                dialog_base.show();
            }
        });
    }
}