package com.bustacallfordriver.user.bustacallfordriver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.model.Rental;
import com.bustacallfordriver.user.bustacallfordriver.model.Rental_List;
import com.bustacallfordriver.user.bustacallfordriver.view.Activity_Booked_UserInfo;

/**
 * Created by user on 2016-11-12.
 */

public class ScheduleAdapter extends BaseAdapter {

    Rental_List scheduleList;
    ViewHoder viewHoder;

    public ScheduleAdapter(Rental_List schedule_List) {
        this.scheduleList = schedule_List;
    }


    public int getItemViewType_view(Rental rental) {
        int wayType = rental.getType(); // 1 : 왕복, 2: 편도
        int togetherType = rental.getTogether().getFlag(); // 0 : 기본, 2 : 같이타기
        int type = 0;
        if (wayType == 1 && togetherType == 0) { // 왕복 & 기본
            type = 1;
        } else if (wayType == 2 && togetherType == 0) { // 편도 & 기본
            type = 2;
        } else if (wayType == 2 && togetherType == 2) { // 편도 & 같이타기
            type = 3;
        } else {
            Log.d("error: ", "없는 type");
        }
        return type;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;
        Rental schedule = scheduleList.getRental_list().get(pos);
        int viewType = getItemViewType_view(schedule);

        if (v == null) {
            viewHoder = new ViewHoder();
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (viewType) {
                case 1: // 왕복
                    v = vi.inflate(R.layout.listview_item_schedule_two_way, null);
                    twoWayInit(v);
                    break;
                case 2: // 편도
                    v = vi.inflate(R.layout.listview_item_schedule_one_way, null);
                    oneWayInit(v);
                    break;
                case 3: // 편도 & 같이타기
                    v = vi.inflate(R.layout.listview_item_schedule_one_way, null);
                    oneWayInit(v);
                    break;
            }

            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }

        switch (viewType) {
            case 1: // 왕복
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.twoway_startDate.setText(schedule.getDay_one());
                viewHoder.twoway_startTime.setText(schedule.getTime_one());
                viewHoder.twoway_endDate.setText(schedule.getDay_two());
                viewHoder.twoway_endTime.setText(schedule.getTime_two());
                viewHoder.twoway_startPlace.setText(schedule.getStart_point_one());
                viewHoder.twoway_endPlace.setText(schedule.getEnd_point_one());
                viewHoder.twoway_price.setText(schedule.getRental_money());
                viewHoder.twoway_btn_showUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mSchedule = scheduleList.getRental_list().get(pos);
                        Intent intent = new Intent(context, Activity_Booked_UserInfo.class);
                        intent.putExtra("type", 1);
                        intent.putExtra("info", mSchedule);
                        context.startActivity(intent);
                    }
                });

                break;
            case 2: // 편도
                v.setBackgroundResource(R.drawable.listview_default_back_file);
                viewHoder.oneway_startDate.setText(schedule.getDay_one());
                viewHoder.oneway_startTime.setText(schedule.getTime_one());
                viewHoder.oneway_startPlace.setText(schedule.getStart_point_one());
                viewHoder.oneway_endPlace.setText(schedule.getEnd_point_one());
                viewHoder.oneway_price.setText(schedule.getRental_money());
                viewHoder.oneway_btn_showUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mSchedule = scheduleList.getRental_list().get(pos);
                        Intent intent = new Intent(context, Activity_Booked_UserInfo.class);
//                        intent.putExtra("type", 2);
                        // 일단 '예약된 손님 정보' 에서 같이타기 없다는 가정으로 진행
                        intent.putExtra("type", 1);
                        intent.putExtra("info", mSchedule);
                        context.startActivity(intent);
                    }
                });
                break;
            case 3: // 편도 & 같이타기
                v.setBackgroundResource(R.drawable.listview_together_back_file);
                viewHoder.oneway_startDate.setText(schedule.getDay_one());
                viewHoder.oneway_startTime.setText(schedule.getTime_one());
                viewHoder.oneway_startPlace.setText(schedule.getStart_point_one());
                viewHoder.oneway_endPlace.setText(schedule.getEnd_point_one());
                viewHoder.oneway_price.setText(schedule.getRental_money());
                viewHoder.oneway_btn_showUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rental mSchedule = scheduleList.getRental_list().get(pos);
                        Intent intent = new Intent(context, Activity_Booked_UserInfo.class);
                        intent.putExtra("type", 2);
                        intent.putExtra("info", mSchedule);
                        context.startActivity(intent);
                    }
                });
                break;
        }
        
        return v;
    }
    private void oneWayInit(View v) {
        viewHoder.oneway_startDate = (TextView) v.findViewById(R.id.listview_schedule_one_way_startDate);
        viewHoder.oneway_startTime = (TextView) v.findViewById(R.id.listview_schedule_one_way_startTime);
        viewHoder.oneway_startPlace = (TextView) v.findViewById(R.id.listview_schedule_one_way_startPlace);
        viewHoder.oneway_endPlace = (TextView) v.findViewById(R.id.listview_schedule_one_way_endPlace);
        viewHoder.oneway_price = (TextView) v.findViewById(R.id.listview_schedule_one_way_price);
        viewHoder.oneway_btn_showUser = (LinearLayout) v.findViewById(R.id.listview_schedule_one_way_btn_showUser);

    }

    private void twoWayInit(View v) {
        viewHoder.twoway_startDate = (TextView) v.findViewById(R.id.listview_schedule_two_way_startDate);
        viewHoder.twoway_startTime = (TextView) v.findViewById(R.id.listview_schedule_two_way_startTime);
        viewHoder.twoway_endDate = (TextView) v.findViewById(R.id.listview_schedule_two_way_endDate);
        viewHoder.twoway_endTime = (TextView) v.findViewById(R.id.listview_schedule_two_way_startTime);
        viewHoder.twoway_startPlace = (TextView) v.findViewById(R.id.listview_schedule_two_way_startPlace);
        viewHoder.twoway_endPlace = (TextView) v.findViewById(R.id.listview_schedule_two_way_endPlace);
        viewHoder.twoway_price = (TextView) v.findViewById(R.id.listview_schedule_two_way_price);
        viewHoder.twoway_btn_showUser = (LinearLayout) v.findViewById(R.id.listview_schedule_two_way_btn_showUser);
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

   @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHoder {
        TextView oneway_startDate, oneway_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_startDate, twoway_startTime; // 출발날짜, 출발시간 (출발할때)
        TextView twoway_endDate, twoway_endTime; // 출발날짜, 출발시간 (돌아올때)
        TextView oneway_startPlace, oneway_endPlace; // 출발지, 도착지
        TextView twoway_startPlace, twoway_endPlace; // 출발지, 도착지
        TextView oneway_price, twoway_price; //가격
        LinearLayout oneway_btn_showUser, twoway_btn_showUser; // 유저보기
    }
}
