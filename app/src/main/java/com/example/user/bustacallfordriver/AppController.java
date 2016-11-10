package com.example.user.bustacallfordriver;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.example.user.bustacallfordriver.model.Bus;
import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;

import java.util.Iterator;

/**
 * Created by user on 2016-10-31.
 */

public class AppController extends Application {
    public final static String SERVERIP = "http://203.252.166.242:8080";//서버
    public static int HTTP_STATUSOK = 200;
    public static int HTTP_STATUSFAIL = 400;
    public static int TENDER_COUNT = 0;
    String pushToken;
    String request_num;
    Bus bus = new Bus();
    String intent_flag;//자동 로그인 확인 0이면 안되있는거 1이면 모든 정보가 저장되있는거
    Rental_List rental_list = new Rental_List();

    public Rental_List getRental_list() {
        return rental_list;
    }

    /**
     * 결재 완료된 Rental은 삭제하고 셋팅
     */
    public void setRental_list(Rental_List rental_list) {
        Iterator<Rental> itr = rental_list.getRental_list().iterator();
        while (itr.hasNext()) {
            if (itr.next().getAccount_flag() == 1)
                itr.remove();
        }
        this.rental_list = rental_list;
    }

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

    public String getPushToken() {
        String push_temp = getSavedToken("token", "null");
        return push_temp;
    }

    public String getSavedToken(String key, String dftValue) {
        SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public void setSavedToken(String token) {
        SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //id에 첫번째 값 가져와서 집어넣기.
        editor.putString("token", token);
        editor.commit();
    }

    public void setSavedId(String id) {//앱에 아이디 저장하기
        SharedPreferences pref = getSharedPreferences("saveid", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //id에 첫번째 값 가져와서 집어넣기.
        editor.putString("id", id);
        editor.commit();
    }

    @Override
    public void onCreate() {
//        pushToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d("pushTo",pushToken);

        super.onCreate();
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

}