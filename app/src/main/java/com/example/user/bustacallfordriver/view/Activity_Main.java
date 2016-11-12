package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.RentalAdapter;
import com.example.user.bustacallfordriver.dialog.Dialog_base_two_button;
import com.example.user.bustacallfordriver.model.Rental;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Activity_Main extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    AppController app;
    SlidingMenu menu;
    ImageView iv_menu, iv_notiIcon;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (AppController)getApplicationContext();
        init();
        setMenu();
    }

    private void init() {
        iv_menu = (ImageView)findViewById(R.id.activity_main_iv_menu);
        iv_notiIcon = (ImageView)findViewById(R.id.activity_main_iv_noti);
        iv_menu.setOnClickListener(this);
        iv_notiIcon.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.activity_main_listview);
        RentalAdapter adapter= new RentalAdapter(app.getRental_list());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void setMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.fragment_sliding_menu);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sliding_menu_frame, SlidingMenuFragment.newInstance())
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_iv_menu:
                goTomenu();
                break;
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

        Rental rental =  app.getRental_list().getRental_list().get(position);
        int wayType = rental.getType(); // 1 : 왕복, 2: 편도
        int togetherType =  rental.getTogether().getFlag(); // 0 : 기본, 1 : 합승
        int type =0 ;
        if(wayType == 1 && togetherType == 0) { // 왕복 & 기본
            type =1;
        }else if(wayType == 1 && togetherType == 1) { // 왕복 & 합승
            type =2;
        }else if(wayType == 2 && togetherType == 0) { // 편도 & 기본
            type =3;
        }else{
            Log.d("error: ","없는 type");
        }

        intent.putExtra("type",type);
        intent.putExtra("info", rental);

        startActivity(intent);
    }

    public void goTomenu() {
        menu.showMenu();
    }
}
