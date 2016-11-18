package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.content.Intent;
import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Splash;
import com.bustacallfordriver.user.bustacallfordriver.view.SlidingMenuFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-13.
 */

public class SlidingMenuFragment_Presenter {

    SlidingMenuFragment view;
    AppController app;
    Dialog_Progress dialog_progress;

    public SlidingMenuFragment_Presenter(SlidingMenuFragment view) {
        this.view = view;
        app = (AppController) view.getActivity().getApplicationContext();
        dialog_progress = new Dialog_Progress(view.getContext());
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
                    Intent intent = new Intent(view.getActivity(), Activity_Splash.class);
                    view.startActivity(intent);
                }else{
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view.getContext() ,"엥..? 로그아웃 안됨ㅋ");
                dialog_base.show();
            }
        });
    }
}
