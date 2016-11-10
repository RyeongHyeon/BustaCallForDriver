package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.RentalAdapter;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.model.Rental;

public class Activity_Main extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    AppController app;
    ImageView iv_notiIcon;
    ListView listView;

    TextView test_default, test_together; // 테스트용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (AppController)getApplicationContext();
        init();
    }

    private void init() {
        iv_notiIcon = (ImageView)findViewById(R.id.activity_main_iv_noti);
        iv_notiIcon.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.activity_main_listview);
        RentalAdapter adapter= new RentalAdapter(app.getRental_list());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
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

    /**리스트뷰 클릭 시 매물 상세 페이지 이동*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplication(), Activity_RentalDetail.class);
        switch (view.getId()) {
            case R.layout.listview_item_one_way:
                intent.putExtra("oneWayOrTwoWay", 0); // 편도
                intent.putExtra("defaultOrTogether", 0); // 같이타기 아님. 기본
                break;
            case R.layout.listview_item_one_way_together:
                intent.putExtra("oneWayOrTwoWay", 0); // 편도
                intent.putExtra("defaultOrTogether", 1); // 같이타기
                break;
            case R.layout.listview_item_two_way:
                intent.putExtra("oneWayOrTwoWay", 1); // 왕복
                intent.putExtra("defaultOrTogether", 0); // 같이타기 아님. 기본
                break;
            case R.layout.listview_item_two_way_together:
                intent.putExtra("oneWayOrTwoWay", 1); // 왕복
                intent.putExtra("defaultOrTogether", 1); // 같이타기
                break;
        }
        Rental rental =  app.getRental_list().getRental_list().get(position);
        intent.putExtra("info", rental);

        startActivity(intent);
    }
}
