package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-06.
 */

public class Activity_Main_Presenter {
    Activity_Main view;
    AppController app;
    Dialog_Progress dialog_progress;

    public Activity_Main_Presenter(Activity_Main view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
        dialog_progress = new Dialog_Progress(view);
    }

    // 서버 통신 어떻게 되는지 확인 후 진행하자
    public void request_get_rental(){//
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_rental();
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if(response.isSuccessful()){
//                    app.setRental_list(response.body());
                    view.setRental_list(response.body());
                    view.setRenewalListView();
                    Log.d("app",app.toString());
                }else{

                }
                dialog_progress.dismiss();
            }

            @Override
            public void onFailure(Call<Rental_List> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }


    // 서버 통신 어떻게 되는지 확인 후 진행하자
    public void request_get_rental_region(String region){//
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_rental_region(region);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if(response.isSuccessful()){
//                    app.setRental_list(response.body());
                    view.setRental_list(response.body());
                    view.setRenewalListView();
                    Log.d("app",app.toString());
                }else{

                }
                dialog_progress.dismiss();
            }

            @Override
            public void onFailure(Call<Rental_List> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }
}
