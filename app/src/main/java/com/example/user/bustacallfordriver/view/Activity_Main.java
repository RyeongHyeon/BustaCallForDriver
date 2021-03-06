package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;

public class Activity_Main extends BaseActivity implements View.OnClickListener{

    ImageView iv_notiIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        iv_notiIcon = (ImageView)findViewById(R.id.activity_main_iv_noti);

        iv_notiIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_iv_noti: // 알림 아이콘
                Intent intent = new Intent(this, Activity_Notice.class);
                startActivity(intent);
                break;
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
