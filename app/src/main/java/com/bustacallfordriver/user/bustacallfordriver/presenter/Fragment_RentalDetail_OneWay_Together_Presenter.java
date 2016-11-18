package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Fragment_RentalDetail_OneWay_Together;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-13.
 */
public class Fragment_RentalDetail_OneWay_Together_Presenter {
    Fragment_RentalDetail_OneWay_Together view;

    public Fragment_RentalDetail_OneWay_Together_Presenter(Fragment_RentalDetail_OneWay_Together view) {
        this.view = view;
    }

    public void request_send_bus(int rental_num, String bus_num, String money) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_send_bus(rental_num, bus_num, money, -1, -1, -1, -1, -1, -1);
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    int i = response.body().get("flag").getAsInt();
                    if (i == 0) {
                        Dialog_base dialog_base = new Dialog_base(view.getContext(), "해당 매물은 중복입니다.");
                        dialog_base.show();
                    } else if (i == 1) {
                        Dialog_base dialog_base = new Dialog_base(view.getContext(), "입찰 되었습니다.");
                        dialog_base.show();
                    }
                } else {
                    Log.d("res", response.toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("res", t.toString());
            }
        });
    }
}
