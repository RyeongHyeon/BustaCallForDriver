package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Sliding_Schedule;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-15.
 */

public class Activity_Sliding_Schedule_Presenter {
    Activity_Sliding_Schedule view;
    AppController app;
    Rental_List scheduleList;
    Dialog_Progress dialog_progress;

    public Activity_Sliding_Schedule_Presenter(Activity_Sliding_Schedule view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
        scheduleList = new Rental_List();
        dialog_progress = new Dialog_Progress(view);
    }

    public void request_get_schedule(String bus_num) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_schedule(bus_num);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if (response.isSuccessful()) {
                    view.setScheduleList(response.body());
                    view.setlListView();
                    Log.d("app", app.toString());
                } else {

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

