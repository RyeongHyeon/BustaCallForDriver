package com.example.user.bustacallfordriver.presenter;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.view.Activity_Splash;

/**
 * Created by user on 2016-11-01.
 */

public class Activity_Splash_Presenter {
    Activity_Splash view;
    AppController app;

    public Activity_Splash_Presenter(Activity_Splash view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }


    // 서버 통신 어떻게 되는지 확인 후 진행하자
}
