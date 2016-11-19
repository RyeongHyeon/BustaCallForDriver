package com.bustacallfordriver.user.bustacallfordriver.view;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.R;

/**
 * Created by user on 2016-10-31.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void onClick_backButton(View v) {
        finish();
    }
    public void setEnableTextView(TextView tv, boolean b) {
        if (b) {
            tv.setEnabled(true);
            tv.setBackgroundResource(R.drawable.roundbutton_blue);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            tv.setEnabled(false);
            tv.setBackgroundResource(R.drawable.roundbutton_gray);
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv.setTextColor(getColor(R.color.AppColorWhite));
            } else {
                tv.setTextColor(getResources().getColor(R.color.AppColorWhite));
            }
        }
    }

    public void setEnableTextView_Rect(TextView tv, boolean b) {
        if (b) {
            tv.setEnabled(true);
            tv.setBackgroundResource(R.drawable.rectbutton_blue);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            tv.setEnabled(false);
            tv.setBackgroundResource(R.drawable.rectbutton_gray);
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M) {
                tv.setTextColor(getColor(R.color.AppColorWhite));
            } else {
                tv.setTextColor(getResources().getColor(R.color.AppColorWhite));
            }
        }
    }
}
