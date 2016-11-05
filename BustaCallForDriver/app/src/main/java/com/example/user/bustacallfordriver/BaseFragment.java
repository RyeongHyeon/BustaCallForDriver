package com.example.user.bustacallfordriver;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by user on 2016-11-05.
 */

public class BaseFragment extends Fragment{

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
                tv.setTextColor(Color.parseColor("#616060"));
            } else {
                tv.setTextColor(Color.parseColor("#616060"));
            }
        }
    }

}
