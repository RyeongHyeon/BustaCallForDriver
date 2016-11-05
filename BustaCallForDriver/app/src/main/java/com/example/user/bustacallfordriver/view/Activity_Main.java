package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;

public class Activity_Main extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);
    }


    @Override
    public void onClick(View v) {

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
