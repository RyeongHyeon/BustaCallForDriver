package com.example.user.bustacallfordriver.presenter;

import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.dialog.Dialog_base;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Fragment_RentalDetail_OneWay;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-11.
 */
public class Fragment_RentalDetail_OneWay_Presenter {
    Fragment_RentalDetail_OneWay view;
    public Fragment_RentalDetail_OneWay_Presenter(Fragment_RentalDetail_OneWay view){
        this.view = view;
    }

    public void request_send_bus(int rental_num,String bus_num,String money,ArrayList<Integer> money_list){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_send_bus(rental_num,bus_num,money,money_list.get(0),money_list.get(1),money_list.get(2)
                ,money_list.get(3),money_list.get(4),money_list.get(5));
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Dialog_base dialog_base = new Dialog_base(view.getContext(),"해당 매물에 예약하셨습니다.");
                    dialog_base.show();
                }else{
                    Log.d("res",response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("res",t.toString());
            }
        });
    }
}
