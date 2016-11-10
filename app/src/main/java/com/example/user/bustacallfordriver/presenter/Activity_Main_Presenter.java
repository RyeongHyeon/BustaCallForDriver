package com.example.user.bustacallfordriver.presenter;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.view.Activity_Main;

/**
 * Created by user on 2016-11-06.
 */

public class Activity_Main_Presenter {
    Activity_Main view;
    AppController app;

    public Activity_Main_Presenter(Activity_Main view) {
        this.view = view;
        app = (AppController)view.getApplicationContext();
    }
}
