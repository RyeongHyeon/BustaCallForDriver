package com.bustacallfordriver.user.bustacallfordriver.presenter;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Fragment_Signin_Bus;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-08.
 */
public class Fragment_Signin_Bus_Presenter {
    Fragment_Signin_Bus view;

    public Fragment_Signin_Bus_Presenter(Fragment_Signin_Bus view){
        this.view = view;
    }

    public void setis_Busnum(String str){
        if(str.equals("")){
            view.setIs_busnum(false);
        }else{
            view.setIs_busnum(true);
        }
    }

    public void setis_Bustype(String str){
        if(str.equals("")){
            view.setIs_bustype(false);
        }else{
            view.setIs_bustype(true);
        }
    }


    public void setis_overlap(String str){
        if(str.equals("")){
            view.setIs_overlap(false);
        }else{
            view.setIs_overlap(true);
        }
    }

    public void setis_Buscareer(String str){
        if(str.equals("")){
            view.setIs_buscareer(false);
        }else{
            view.setIs_buscareer(true);
        }
    }
    public void setis_Busage(String str){
        if(str.equals("")){
            view.setIs_busage(false);
        }else{
            view.setIs_busage(true);
        }
    }
    public void setis_Busprofile1(String str){
        if(str.equals("")){
            view.setIs_busprofile1(false);
        }else{
            view.setIs_busprofile1(true);
        }
    }
    public void setis_Busprofile2(String str){
        if(str.equals("")){
            view.setIs_busprofile2(false);
        }else{
            view.setIs_busprofile2(true);
        }
    }

    public void request_get_overlap_confirm(String busNum) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_get_overlap_confirm(busNum);
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    int i = response.body().get("flag").getAsInt();
                    if(i == 1){
                        setis_overlap("성공함");
                        final Dialog_base dialog_base = new Dialog_base(view.getContext(), "사용할 수 있는 차량 번호 입니다.");
                        dialog_base.show();
                    }else {
                        setis_overlap("");
                        final Dialog_base dialog_base = new Dialog_base(view.getContext(), "이미 등록된 차량 번호 입니다.");
                        dialog_base.show();
                        view.set_busNumEmpty();
                    }
                }else{
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
