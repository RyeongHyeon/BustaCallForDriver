package com.example.user.bustacallfordriver.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_Progress;
import com.example.user.bustacallfordriver.dialog.Dialog_base;
import com.example.user.bustacallfordriver.model.Bus;
import com.example.user.bustacallfordriver.model.Rental_List;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Activity_Login;
import com.example.user.bustacallfordriver.view.Activity_Main;
import com.example.user.bustacallfordriver.view.Activity_Splash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-01.
 */

public class Activity_Splash_Presenter {
    Activity_Splash view;
    AppController app;
    Dialog_Progress dialog_progress;

    public Activity_Splash_Presenter(Activity_Splash view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
        dialog_progress = new Dialog_Progress(view);
    }

    public void checkLogin(){//오토로그인 체크
        app.getBus().setBus_num(app.getSavedId("id","0"));
        if(app.getBus().getBus_num().equals("0"))
        {
            Intent intent = new Intent(view,Activity_Login.class);
            view.startActivity(intent);
        }else{
            request_autologin_bus();
        }
    }

    public void request_autologin_bus(){//정보 받아오는 것, bus기사에 대한 정보만 받아옴
        String bus_num = app.getSavedId("id","0");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Bus> retrofitinfo = retrofit_user.request_autologin_bus(bus_num);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Bus>() {
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
                if (response.isSuccessful()) {
                    app.setBus(response.body());
                    request_get_rental(); //통신을 한번 더, 렌탈에 대한 arraylist를 받아와

//                    Log.d("app",app.toString());
//                    Intent intent = new Intent(view, Activity_Main.class);
//                    view.startActivity(intent);
                }else{
                    Log.d("test",response.toString());
                }
            }
            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }

    // 서버 통신 어떻게 되는지 확인 후 진행하자
    public void request_get_rental(){//
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_rental();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if(response.isSuccessful()){
                    app.setRental_list(response.body());
                    Log.d("app",app.toString());
                    Intent intent = new Intent(view, Activity_Main.class);
                    view.startActivity(intent);
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
