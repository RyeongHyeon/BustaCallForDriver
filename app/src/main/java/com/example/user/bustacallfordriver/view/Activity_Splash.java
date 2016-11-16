package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.presenter.Activity_Splash_Presenter;

/**
 * Created by user on 2016-10-31.
 */

public class Activity_Splash extends BaseActivity implements View.OnClickListener{
    Button bt_login;
    AppController app;
    Activity_Splash_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        app = (AppController)getApplicationContext();
        init();
        set_Values();
    }

    public void set_Values(){
        presenter = new Activity_Splash_Presenter(this);
    }

    private void init(){
        bt_login = (Button)findViewById(R.id.activity_splash_bt_login);
    }

    @Override
    public void onClick(View v) { //통신이 필요하네. 로그인할때도
        if(v.getId()==R.id.activity_splash_bt_login){
            presenter.checkLogin();
        }
    }

    @Override
    public void onBackPressed() {//종료 버튼
        final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(this, "앱을 종료하시겠습니까?");
        dialog_base_two_button.show();
        dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dialog_base_two_button.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_base_two_button.dismiss();
            }
        });
    }

}
