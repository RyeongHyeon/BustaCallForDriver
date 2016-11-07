package com.example.user.bustacallfordriver;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.user.bustacallfordriver.model.Bus;

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
        super.onCreate();
    }
}