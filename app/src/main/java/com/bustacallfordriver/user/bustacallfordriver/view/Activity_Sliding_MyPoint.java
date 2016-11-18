package com.bustacallfordriver.user.bustacallfordriver.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.AppController;
import com.bustacallfordriver.user.bustacallfordriver.R;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base;
import com.bustacallfordriver.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.bustacallfordriver.user.bustacallfordriver.presenter.Activity_Sliding_MyPoint_Presenter;

/**
 * 사이드 메뉴 > 나의 포인트
 * Created by user on 2016-11-12.
 */

public class Activity_Sliding_MyPoint extends BaseActivity implements View.OnClickListener {

    AppController app;
    ImageView iv_back;
    EditText et_exchange;
    TextView tv_point, tv_enter;
    Activity_Sliding_MyPoint_Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_mypoint);
        app = (AppController) getApplicationContext();
        presenter = new Activity_Sliding_MyPoint_Presenter(this);
        init();
    }

    private void init() {
        iv_back = (ImageView) findViewById(R.id.activity_sliding_mypoint_iv_back);
        tv_point = (TextView) findViewById(R.id.activity_sliding_mypoint_tv_point);
        tv_enter = (TextView) findViewById(R.id.activity_sliding_mypoint_tv_enter);
        et_exchange = (EditText) findViewById(R.id.activity_sliding_mypoint_et_changePoint);
        iv_back.setOnClickListener(this);
        tv_enter.setOnClickListener(this);

        tv_point.setText(app.getBus().getPoint());
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
                            int exchangeInt = Integer.valueOf(et_exchange.getText().toString().trim());
                            presenter.request_send_exchange(app.getBus().getNickname(), app.getBus().getBus_num(),exchangeInt*1000);
                            setPoint(exchangeInt*1000);
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
     * @param exchange 환전 금액
     */
    private void setPoint(int exchange) {
        int point = Integer.valueOf(tv_point.getText().toString().trim());
        String remainPointStr = String.valueOf(point - exchange);
        tv_point.setText(remainPointStr);
    }

    /**
     * 환전하는 포인트가 보유 포인트를 초과하는지 확인
     */
    private boolean checkMyPoint() {
        int point = 0;
        if(!Integer.valueOf(tv_point.getText().toString().trim()).equals("")){
            Integer.valueOf(tv_point.getText().toString().trim());
        }
        int changePoint = 0;
        String changePointStr= et_exchange.getText().toString().trim();
        if(!"".equals(changePointStr)){
            changePoint = Integer.valueOf(changePointStr) * 1000;
        }

        if (changePoint > point || changePoint == 0 ) {
            return false;
        }
        return true;
    }
}