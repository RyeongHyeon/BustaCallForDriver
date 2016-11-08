package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
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
}
