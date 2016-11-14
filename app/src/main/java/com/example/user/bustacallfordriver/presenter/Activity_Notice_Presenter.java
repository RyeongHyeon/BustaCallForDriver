package com.example.user.bustacallfordriver.presenter;

import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Activity_Notice;

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

    public Activity_Notice_Presenter(Activity_Notice view) {
        this.view = view;
    }

    /**알림 켜기, 끄기 여부 보내기*/
    public void request_send_notice_onoff(int notice_onoff){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_send_notice_onoff(notice_onoff);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("success", "알림 여부 서버에 보냄");
                }else{
                    Log.d("test",response.toString());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Test",t.toString());
            }
        });
    }
}
