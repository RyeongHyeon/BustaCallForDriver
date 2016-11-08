package com.example.user.bustacallfordriver.presenter;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.view.Fragment_Signin_User;

/**
 * Created by user on 2016-11-08.
 */
public class Fragment_Signin_User_Presenter {
    Fragment_Signin_User view;
    AppController app;
    public Fragment_Signin_User_Presenter(Fragment_Signin_User view){
        this.view = view;
    }

    public void getis_Nickname(String str){
        if(str.equals("")){
            view.setIs_nickname(false);
        }else{
            view.setIs_nickname(true);
        }
    }

    public void getis_Brithday(String str){
        if(str.equals("")){
            view.setIs_birthady(false);
        }else{
            view.setIs_birthady(true);
        }
    }

    public void getis_Group(String str){
        if(str.equals("")){
            view.setIs_group(false);
        }else{
            view.setIs_group(true);
        }
    }
    public void getis_Account_Num(String str){
        if(str.equals("")){
            view.setIs_account_num(false);
        }else{
            view.setIs_account_num(true);
        }
    }
    public void getis_Profile(String str){
        if(str.equals("")){
            view.setIs_profile(false);
        }else{
            view.setIs_profile(true);
        }
    }
}
