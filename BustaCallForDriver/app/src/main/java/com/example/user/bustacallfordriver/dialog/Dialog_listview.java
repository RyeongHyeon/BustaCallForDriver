package com.example.user.bustacallfordriver.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.R;

/**
 * Created by user on 2016-11-02.
 */
public class Dialog_listview extends Dialog {
    private String title;
    private int array;
    private Context mContext = null;
    ListView listView;
    TextView tv_title, btnSubmit, btnCancel;



    public Dialog_listview(Context context, String title, int array) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.dialog_listview);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //뒷배경
        this.setCanceledOnTouchOutside(false); //밖에 눌렀을때 안꺼지게
        this.setCancelable(true); //back키 눌렀을때 꺼지게
        this.title = title;
        this.array = array;
        mContext = context;
        init();
    }

    public void init(){
        tv_title = (TextView) findViewById(R.id.dialog_listview_tv_title);
        listView = (ListView)findViewById(R.id.dialog_listview_lt);
        btnSubmit = (TextView)findViewById(R.id.submit);
        btnCancel = (TextView)findViewById(R.id.cancel);
        DialogListviewAdapter adapter = new DialogListviewAdapter(array);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);
        tv_title.setText(title);

        clickButton();

    }

    private void clickButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public ListView getListView() {
        return listView;
    }

}