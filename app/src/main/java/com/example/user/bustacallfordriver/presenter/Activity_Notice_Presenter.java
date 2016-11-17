package com.example.user.bustacallfordriver.presenter;

import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.dialog.Dialog_Progress;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Activity_Notice;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-13.
 */

public class Activity_Notice_Presenter {
    Activity_Notice view;
    Dialog_Progress dialog_progress;

    public Activity_Notice_Presenter(Activity_Notice view) {
        this.view = view;
        dialog_progress = new Dialog_Progress(view);
    }

    /**
     * 알림 켜기, 끄기 여부 보내기
     */
    public void request_send_notice_onoff(String busNum, int notice_onoff) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_send_notice_onoff(busNum, notice_onoff);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("success", "알림 여부 서버에 보냄");
                } else {
                    Log.d("test", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Test", t.toString());
            }
        });
    }

    // 서버 통신 어떻게 되는지 확인 후 진행하자
    public void request_notice_region(String bus_num, String region) {//
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_notice_region(bus_num, region);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    JsonObject jsonObject = response.body();
                    ArrayList<String> list = new ArrayList<String>();
                    if(jsonObject.has("message_list")){
                        JsonArray jsonArray = jsonObject.getAsJsonArray("message_list");

                        for(int i=0;i<jsonArray.size();i++)
                        {
                            JsonObject jsonObject1 = (JsonObject)jsonArray.get(i);
                            list.add(jsonObject1.get("message").getAsString());
                        }
                        view.setNoticeList(list);
                        view.setlListView();
                    }else{
                        view.setNoticeList(list);
                        view.setlListView();
                    }
                } else {

                }
                dialog_progress.dismiss();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }
}
