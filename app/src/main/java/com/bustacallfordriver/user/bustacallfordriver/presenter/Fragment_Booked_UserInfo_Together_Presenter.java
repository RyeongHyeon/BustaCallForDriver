package com.bustacallfordriver.user.bustacallfordriver.presenter;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_Progress;
import com.bustacallfordriver.user.bustacallfordriver.model.Retrofit_User;
import com.bustacallfordriver.user.bustacallfordriver.view.Fragment_Booked_UserInfo_Together;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-16.
 */

public class Fragment_Booked_UserInfo_Together_Presenter {

    Fragment_Booked_UserInfo_Together view;
    Dialog_Progress dialog_progress;

    public Fragment_Booked_UserInfo_Together_Presenter(Fragment_Booked_UserInfo_Together view){
        this.view = view;
        dialog_progress = new Dialog_Progress(view.getContext());
    }

    /**손님 닉네임으로 번호 가져오기*/
    public void request_get_booked_userinfo(String nickname) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<JsonObject> retrofitinfo = retrofit_user.request_get_booked_userinfo(nickname);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    String phoneNum = response.body().get("phone_num").getAsString();
                    view.set_Info(phoneNum);
                } else {

                }
                dialog_progress.dismiss();
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }
}
