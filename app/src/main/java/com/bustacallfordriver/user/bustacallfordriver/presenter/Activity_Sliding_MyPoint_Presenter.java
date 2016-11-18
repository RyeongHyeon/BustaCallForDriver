package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Sliding_MyPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-17.
 */
public class Activity_Sliding_MyPoint_Presenter {

    Activity_Sliding_MyPoint view;
    Dialog_Progress dialog_progress;

    public Activity_Sliding_MyPoint_Presenter(Activity_Sliding_MyPoint view) {
        this.view = view;
        dialog_progress = new Dialog_Progress(view);
    }


    /**
     * 알림 켜기, 끄기 여부 보내기
     */
    public void request_send_exchange(String nickname, String busNum, int exchangePoint) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_send_exchange(nickname, busNum, exchangePoint);
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
}
