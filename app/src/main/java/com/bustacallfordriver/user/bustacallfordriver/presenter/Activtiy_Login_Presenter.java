package com.bustacallfordriver.user.bustacallfordriver.presenter;

import android.util.Log;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-02.
 */

public class Activtiy_Login_Presenter {
    Activity_Login view;
    AppController app;
    public Activtiy_Login_Presenter(Activity_Login view){
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }

    public void getis_phonenum(String str){
        if(str.equals(""))
        {
            view.setIs_phonenum(false);
        }else{
            view.setIs_phonenum(true);
            view.setEnableTextView(view.getTv_btnPhoneRequest(),true);
        }
    }

    public void getis_requestnum(String str){
        if(str.equals(""))
        {
            view.setIs_requestnum(false);
        }else{
            view.setIs_requestnum(true);
        }
    }
    public void request_requestNum(final String phonenum) {
        //TODO 통신 통해서 인증 번호 입력
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_phonerequest(phonenum);
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    view.setIs_phonenumbutton(true);
                    app.getBus().setPhone_num(phonenum);
                    Dialog_base dialog_base = new Dialog_base(view,"인증번호를 요청하였습니다.");
                    dialog_base.show();
                } else {
                    final Dialog_base dialog_base = new Dialog_base(view, "잘못된 번호입니다.");
                    dialog_base.show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("실패", t.toString());
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }

    public void request_login(String phonenum,String certificationnum){
        Log.d("Test",app.toString());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_login(phonenum,certificationnum,app.getPushToken());
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    view.nextPage();

                }else{
                    final Dialog_base dialog_base = new Dialog_base(view, "인증번호가 틀립니다.");
                    dialog_base.show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                final Dialog_base dialog_base = new Dialog_base(view, view.getString(R.string.requestfail));
                dialog_base.show();
            }
        });
    }
}
