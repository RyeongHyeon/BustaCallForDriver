package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.content.Intent;
import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Sliding_Setting;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Splash;
import com.google.gson.JsonObject;

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
        Call<JsonObject> retrofitinfo = retrofit_user.request_logout(bus_num);
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    int i = response.body().get("flag").getAsInt();
                    if(i == 1){
                        app.setSavedId("0");
                        final Dialog_base dialog_base = new Dialog_base(view,"회원탈퇴 됐습니다.");
                        dialog_base.show();
                        Intent intent = new Intent(view, Activity_Splash.class);
                        view.startActivity(intent);
                    }else {
                        final Dialog_base dialog_base = new Dialog_base(view,"현재 스케줄이 있어서 탈퇴되지 않습니다.");
                        dialog_base.show();
                    }
                }else{
                    System.out.println( response.body().get("flag").getAsInt());
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view,"엥..? 로그아웃 안됨ㅋ");
                dialog_base.show();
            }
        });
    }

    public void request_set_setting(String bus_num, String selItem, String accoutNum) {
        Log.d("Test",app.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_set_setting(bus_num, selItem, accoutNum);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Dialog_base dialog = new Dialog_base(view, "설정 완료됐습니다.");
                    dialog.show();
                    view.set_accountNumEmpty();
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
