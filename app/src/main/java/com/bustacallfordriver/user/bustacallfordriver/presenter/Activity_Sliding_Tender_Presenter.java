package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Sliding_Tender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-13.
 */

public class Activity_Sliding_Tender_Presenter {
    Activity_Sliding_Tender view;
    AppController app;
    Rental_List tenderList;
    Dialog_Progress dialog_progress;

    public Activity_Sliding_Tender_Presenter(Activity_Sliding_Tender view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
        tenderList = new Rental_List();
        dialog_progress = new Dialog_Progress(view);
    }


    /**서버에서 삭제*/
    public void request_delete_tender(String bus_num, int rental_num){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_delete_tender(bus_num, rental_num);
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if (response.isSuccessful()) {
                    //여기서 종민찡이 아무것도 없을떄도 반환해줘야됨
//                    app.setTender_list(response.body());
                    view.setTenderList(response.body());
                    view.setRenewalListView();
                    Log.d("app", app.toString());
                } else {

                }
            }
            @Override
            public void onFailure(Call<Rental_List> call, Throwable t) {
            }
        });
    }

    public void request_get_tender(String bus_num) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_tender(bus_num);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if (response.isSuccessful()) {
//                    app.setTender_list(response.body());
                    view.setTenderList(response.body());
                    view.setRenewalListView();
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
