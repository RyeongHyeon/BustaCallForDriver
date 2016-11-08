package com.example.user.bustacallfordriver;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.user.bustacallfordriver.model.Bus;
import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-10-31.
 */

public class AppController extends Application {
    public final static String SERVERIP="http://203.252.166.242:8080";//서버
    public static int HTTP_STATUSOK=200;
    public static int HTTP_STATUSFAIL=400;
    public static int TENDER_COUNT=0;
    String pushToken;
    String request_num;
    Bus bus = new Bus();
    String intent_flag;//자동 로그인 확인 0이면 안되있는거 1이면 모든 정보가 저장되있는거
    Rental_List rental_list = new Rental_List();

    public Rental_List getRental_list() {
        return rental_list;
    }

    public void setRental_list(Rental_List rental_list) {
        this.rental_list = rental_list;
    }

//    List<Rental> rental_list = new ArrayList<>();
//
//    public List<Rental> getRental_list() {
//        return rental_list;
//    }
//
//    public void setRental_list(List<Rental> rental_list) {
//        this.rental_list = rental_list;
//    }

    public String getIntent_flag() {
        return intent_flag;
    }

    public void setIntent_flag(String intent_flag) {
        this.intent_flag = intent_flag;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getRequest_num() {
        return request_num;
    }

    public void setRequest_num(String request_num) {
        this.request_num = request_num;
    }

    public String getSavedId(String key, String dftValue) {//앱에 저장된 아이디 가져오기
        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public void setSavedId(String id){//앱에 아이디 저장하기
        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //id에 첫번째 값 가져와서 집어넣기.
        editor.putString("id",id);
        editor.commit();
    }

    @Override
    public void onCreate() {
//        pushToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d("pushTo",pushToken);

        super.onCreate();
    }
    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

}