package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.presenter.SlidingMenuFragment_Presenter;

/**
 * Created by user on 2016-11-12.
 */

public class SlidingMenuFragment extends Fragment implements View.OnClickListener{
    TextView tv_nickname;
    ViewGroup ll_tender,ll_schedule, ll_myInfo, ll_myPoint,ll_inqulry,ll_setting,ll_logout;
    AppController app;

    SlidingMenuFragment_Presenter presenter;

    public SlidingMenuFragment(){}

    public static SlidingMenuFragment newInstance(){
        return new SlidingMenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sliding_menu, container, false);
        app = (AppController)getActivity().getApplicationContext();
        presenter = new SlidingMenuFragment_Presenter(this);
        init(rootView);
        return rootView;
    }

    private void init(View v) {

                // Instantiate image view object of the user avatar and attaching onClick listener to it.
        tv_nickname = (TextView)v.findViewById(R.id.sliding_menu_tv_name);
        ll_tender = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_tender);
        ll_schedule = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_schedule);
        ll_myInfo = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_myinfo);
        ll_myPoint = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_mypoint);
        ll_inqulry = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_inqulry);
        ll_setting = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_setting);
        ll_logout = (LinearLayout)v.findViewById(R.id.sliding_menu_ll_logout);
        ll_tender.setOnClickListener(this);
        ll_schedule.setOnClickListener(this);
        ll_myInfo.setOnClickListener(this);
        ll_myPoint.setOnClickListener(this);
        ll_inqulry.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_logout.setOnClickListener(this);

        tv_nickname.setText(app.getBus().getNickname());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sliding_menu_ll_tender){
            goToTender();
        }else if(v.getId()==R.id.sliding_menu_ll_schedule){
            goToSchedule();
        }else if(v.getId()==R.id.sliding_menu_ll_myinfo){
            goToMyInfo();
        }else if(v.getId()==R.id.sliding_menu_ll_mypoint){
            goToMyPoint();
        }else if(v.getId()==R.id.sliding_menu_ll_inqulry){
            goToInqulry();
        }else if(v.getId()==R.id.sliding_menu_ll_setting){
            goToSetting();
        }else if(v.getId()==R.id.sliding_menu_ll_logout){
            goToLogout();
        }
    }

    public void goToTender(){
        Intent intent = new Intent(getContext(), Activity_Sliding_Tender.class);
        startActivity(intent);
    }
    public void goToSchedule() {
        Intent intent = new Intent(getContext(), Activity_Sliding_Schedule.class);
        startActivity(intent);
    }

    public void goToMyPoint() {
        Intent intent = new Intent(getContext(), Activity_Sliding_MyPoint.class);
        startActivity(intent);
    }


    public void goToMyInfo() {
        Intent intent = new Intent(getContext(), Activity_Sliding_MyInfo.class);
        startActivity(intent);
    }

    public void goToInqulry() {
        Intent intent = new Intent(getContext(), Activity_Sliding_Inquiry.class);
        startActivity(intent);
    }

    public void goToSetting() {
        Intent intent = new Intent(getContext(), Activity_Sliding_Setting.class);
        startActivity(intent);
    }

    public void goToLogout() {
        //다이알로그
        final Dialog_base_two_button dialog_base_two_button = new Dialog_base_two_button(getContext(), getActivity().getString(R.string.logout_content));
        dialog_base_two_button.show();
        dialog_base_two_button.getTv_enter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃
                app.setSavedId("0");
                Intent intent = new Intent(getContext(), Activity_Splash.class);
                startActivity(intent);
                //TODO 통신
            }
        });
        dialog_base_two_button.getTv_cancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃 취소
                dialog_base_two_button.dismiss();
            }
        });
    }
}
