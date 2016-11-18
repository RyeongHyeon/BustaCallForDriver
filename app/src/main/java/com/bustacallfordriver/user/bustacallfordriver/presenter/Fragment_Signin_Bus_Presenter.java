package com.bustacallfordriver.user.bustacallfordriver.presenter;

import com.bustacallfordriver.user.bustacallfordriver.view.Fragment_Signin_Bus;

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
}
