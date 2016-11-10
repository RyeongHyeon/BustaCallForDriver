package com.example.user.bustacallfordriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.bustacallfordriver.model.Rental;
import com.example.user.bustacallfordriver.model.Rental_List;

/**
 * 메인 > 매물 리스트 어댑터
 * Created by user on 2016-11-07.
 */
public class RentalAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_ONE_WAY = 0;
    private static final int ITEM_VIEW_TYPE_ONE_WAY_TOGETHER = 1;
    private static final int ITEM_VIEW_TYPE_TWO_WAY = 2;
    private static final int ITEM_VIEW_TYPE_TWO_WAY_TOGETHER = 3;
    private static final int ITEM_VIEW_TYPE_MAX = 4;
    Rental_List rentalList; //  렌탈리스트 가져오는 레트로핏 클래스
    TextView oneway_startDate, oneway_startTime, oneway_together_startDate, oneway_together_startTime; // 출발날짜, 출발시간 (출발할때)
    TextView twoway_startDate, twoway_startTime, twoway_together_startDate, twoway_together_startTime; // 출발날짜, 출발시간 (출발할때)
    TextView twoway_endDate, twoway_endTime, twoway_together_endDate, twoway_together_endTime; // 출발날짜, 출발시간 (돌아올때)
    TextView oneway_startPlace, oneway_endPlace, oneway_together_startPlace, oneway_together_endPlace; // 출발지, 도착지
    TextView twoway_startPlace, twoway_endPlace, twoway_together_startPlace, twoway_together_endPlace; // 출발지, 도착지
    TextView oneway_goal, oneway_together_goal, twoway_goal, twoway_together_goal;// 목적
    TextView oneway_userCnt, twoway_userCnt; // 탑승자수

    public RentalAdapter(Rental_List rental_list) {
        this.rentalList = rental_list;
    }

    @Override
    public int getItemViewType(int position) {
        int wayType = rentalList.getRental_list().get(position).getType(); // 1 : 왕복, 2: 편도
        int togetherType = rentalList.getRental_list().get(position).getTogether().getFlag(); // 0 : 기본, 1 : 합승
        int type =0 ;
        if(wayType == 1 && togetherType == 0) { // 왕복 & 기본
            type =1;
        }else if(wayType == 1 && togetherType == 1) { // 왕복 & 합승
            type =2;
        }else if(wayType == 2 && togetherType == 0) { // 편도 & 기본
            type =3;
        }else if(wayType == 2 && togetherType == 1) { // 편도 & 합승
            type =4;
        }

        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;
        int viewType = getItemViewType(pos);
        Rental rental = rentalList.getRental_list().get(pos);
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (viewType) {
                case 1: // 왕복 & 기본
                    v = vi.inflate(R.layout.listview_item_two_way, null);
                    twoWayInit(v);
                    twoway_startDate.setText(rental.getDay_one());
                    twoway_startTime.setText(rental.getTime_one());
                    twoway_endDate.setText(rental.getDay_two());
                    twoway_endTime.setText(rental.getTime_two());
                    twoway_startPlace.setText(rental.getStart_point_one());
                    twoway_endPlace.setText(rental.getEnd_point_one());
                    twoway_goal.setText(rental.getRental_reason());
                    twoway_userCnt.setText(rental.getUser_count());
                    break;
                case 2: // 왕복 & 합승
                    v = vi.inflate(R.layout.listview_item_two_way_together, null);
                    twoWayTogetherInit(v);
                    twoway_together_startDate.setText(rental.getDay_one());
                    twoway_together_startTime.setText(rental.getTime_one());
                    twoway_together_endDate.setText(rental.getDay_two());
                    twoway_together_endTime.setText(rental.getTime_two());
                    twoway_together_startPlace.setText(rental.getStart_point_one());
                    twoway_together_endPlace.setText(rental.getEnd_point_one());
                    twoway_together_goal.setText(rental.getRental_reason());
                    break;
                case 3: // 편도 & 기본
                    v = vi.inflate(R.layout.listview_item_one_way, null);
                    oneWayInit(v);
                    oneway_startDate.setText(rental.getDay_one());
                    oneway_startTime.setText(rental.getTime_one());
                    oneway_startPlace.setText(rental.getStart_point_one());
                    oneway_endPlace.setText(rental.getEnd_point_one());
                    oneway_goal.setText(rental.getRental_reason());
                    oneway_userCnt.setText(rental.getUser_count());
                    break;
                case 4: // 편도 & 합승
                    v = vi.inflate(R.layout.listview_item_one_way_together, null);
                    oneWayTogetherInit(v);
                    oneway_together_startDate.setText(rental.getDay_one());
                    oneway_together_startTime.setText(rental.getTime_one());
                    oneway_together_startPlace.setText(rental.getStart_point_one());
                    oneway_together_endPlace.setText(rental.getEnd_point_one());
                    oneway_together_goal.setText(rental.getRental_reason());
                    break;
            }
        }

        return v;
    }

    private void oneWayInit(View v) {
        oneway_startDate = (TextView) v.findViewById(R.id.listview_one_way_startDate);
        oneway_startTime = (TextView) v.findViewById(R.id.listview_one_way_startTime);
        oneway_startPlace = (TextView) v.findViewById(R.id.listview_one_way_startPlace);
        oneway_endPlace = (TextView) v.findViewById(R.id.listview_one_way_endPlace);
        oneway_goal = (TextView) v.findViewById(R.id.listview_one_way_goal);
        oneway_userCnt = (TextView) v.findViewById(R.id.listview_one_way_userCnt);
    }

    private void oneWayTogetherInit(View v) {
        oneway_together_startDate = (TextView) v.findViewById(R.id.listview_one_way_together_top_startDate);
        oneway_together_startTime = (TextView) v.findViewById(R.id.listview_one_way_together_top_startTime);
        oneway_together_startPlace = (TextView) v.findViewById(R.id.listview_one_way_together_top_startPlace);
        oneway_together_endPlace = (TextView) v.findViewById(R.id.listview_one_way_together_top_endPlace);
        oneway_together_goal = (TextView) v.findViewById(R.id.listview_one_way_together_top_goal);
    }

    private void twoWayInit(View v) {
        twoway_startDate = (TextView) v.findViewById(R.id.listview_two_way_startDate);
        twoway_startTime = (TextView) v.findViewById(R.id.listview_two_way_startTime);
        twoway_endDate = (TextView) v.findViewById(R.id.listview_two_way_endDate);
        twoway_endTime = (TextView) v.findViewById(R.id.listview_two_way_endTime);
        twoway_startPlace = (TextView) v.findViewById(R.id.listview_two_way_startPlace);
        twoway_endPlace = (TextView) v.findViewById(R.id.listview_two_way_endPlace);
        twoway_goal = (TextView) v.findViewById(R.id.listview_two_way_goal);
        twoway_userCnt = (TextView) v.findViewById(R.id.listview_two_way_userCnt);
    }

    private void twoWayTogetherInit(View v) {
        twoway_together_startDate = (TextView) v.findViewById(R.id.listview_two_way_together_startDate);
        twoway_together_startTime = (TextView) v.findViewById(R.id.listview_two_way_together_startTime);
        twoway_together_endDate = (TextView) v.findViewById(R.id.listview_two_way_together_endDate);
        twoway_together_endTime = (TextView) v.findViewById(R.id.listview_two_way_together_endTime);
        twoway_together_startPlace = (TextView) v.findViewById(R.id.listview_two_way_together_startPlace);
        twoway_together_endPlace = (TextView) v.findViewById(R.id.listview_two_way_together_endPlace);
        twoway_together_goal = (TextView) v.findViewById(R.id.listview_two_way_together_goal);
    }


    @Override
    public int getCount() {
        return rentalList.getRental_list().size();
    }

    @Override
    public Object getItem(int position) {
        return rentalList.getRental_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
