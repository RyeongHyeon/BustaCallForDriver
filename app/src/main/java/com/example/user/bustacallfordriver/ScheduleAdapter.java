package com.example.user.bustacallfordriver;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;

/**
 * Created by user on 2016-11-12.
 */

public class ScheduleAdapter extends BaseAdapter {

    Rental_List scheduleList;
    ViewHoder viewHoder;

    public ScheduleAdapter(Rental_List schedule_List) {
        this.scheduleList = schedule_List;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;
        Rental schedule = scheduleList.getRental_list().get(pos);
        if (v == null) {
            viewHoder = new ScheduleAdapter.ViewHoder();
        } else {
            viewHoder = (ScheduleAdapter.ViewHoder) v.getTag();
        }

        return v;
    }


    @Override
    public int getCount() {
        return scheduleList.getRental_list().size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleList.getRental_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHoder {
        TextView oneway_startDate, oneway_startTime, oneway_together_startDate, oneway_together_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_startDate, twoway_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_endDate, twoway_endTime; // 출발날짜, 출발시간 (돌아올때)
        TextView oneway_startPlace, oneway_endPlace, oneway_together_startPlace, oneway_together_endPlace; // 출발지, 도착지
        TextView twoway_startPlace, twoway_endPlace; // 출발지, 도착지
        TextView oneway_goal, oneway_together_goal, twoway_goal;// 목적
        TextView oneway_userCnt, twoway_userCnt; // 탑승자수
        TextView oneway_together_price; // 같이타기 가격
    }
}
