package com.example.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.dialog.Dialog_base;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;

/**
 * 사이드 메뉴 > 나의 포인트
 * Created by user on 2016-11-12.
 */

public class Activity_Sliding_MyPoint extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_back;
    EditText et_chanePoint;
    TextView tv_point, tv_enter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_mypoint);
        app = (AppController) getApplicationContext();
        init();
    }

    private void init() {
        iv_back = (ImageView) findViewById(R.id.activity_sliding_mypoint_iv_back);
        tv_point = (TextView) findViewById(R.id.activity_sliding_mypoint_tv_point);
        tv_enter = (TextView) findViewById(R.id.activity_sliding_mypoint_tv_enter);
        et_chanePoint = (EditText) findViewById(R.id.activity_sliding_mypoint_et_changePoint);
        iv_back.setOnClickListener(this);
        tv_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sliding_mypoint_iv_back:
                finish();
                break;
            case R.id.activity_sliding_mypoint_tv_enter:
                if (checkMyPoint()) {
                    final Dialog_base_two_button dialog = new Dialog_base_two_button(this, "환전 하시겠습니까?");
                    dialog.show();
                    dialog.getTv_enter().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            //환전 서버에 보내고 남은 포인트 셋팅
                            setPoint();
                            dialog.dismiss();
                        }
                    });

                    dialog.getTv_cancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    final Dialog_base dialog = new Dialog_base(this, "환전값을 확인해주세요~");
                    dialog.show();
                }
                break;
        }
    }

    /**
     * 남은 포인트 셋팅, 서버에도 저장
     */
    private void setPoint() {
    }

    /**
     * 환전하는 포인트가 보유 포인트를 초과하는지 확인
     */
    private boolean checkMyPoint() {
        int point = Integer.valueOf(tv_point.getText().toString().trim());
        int changePoint = 0;
        String changePointStr= et_chanePoint.getText().toString().trim();
        if(!"".equals(changePointStr)){
            changePoint = Integer.valueOf(changePointStr) * 1000;
        }

        if (changePoint > point || changePoint == 0 ) {
            return false;
        }
        return true;
    }
}