package com.example.user.bustacallfordriver;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.bustacallfordriver.model.Rental_List;

/**
 * Created by user on 2016-11-12.
 */

public class ScheduleAdapter extends BaseAdapter {

    Rental_List scheduleList;

    public ScheduleAdapter(Rental_List schedule_List) {
        this.scheduleList = schedule_List;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
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

}
