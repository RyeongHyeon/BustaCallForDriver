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
 * Created by user on 2016-11-13.
 */
public class NoticeAdapter extends BaseAdapter {

    Rental_List noticeList; // 알림 리스트
    ViewHoder viewHoder;

    public NoticeAdapter(Rental_List notice_list) {
        this.noticeList = notice_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;

        Rental notice = noticeList.getRental_list().get(pos);

        if (v == null) {
            viewHoder = new NoticeAdapter.ViewHoder();
        } else {
            viewHoder = (NoticeAdapter.ViewHoder) v.getTag();
        }


        v.setTag(viewHoder);


        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        init(v);

        viewHoder.tv_region.setText(notice.getStart_point_one());
        viewHoder.tv_rental_num.setText(notice.getRental_num());

        return v;
    }

    private void init(View v) {
        viewHoder.tv_region = (TextView)v.findViewById(R.id.listview_notice_region);
        viewHoder.tv_rental_num = (TextView)v.findViewById(R.id.listview_notice_rentalNum);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class ViewHoder {
        TextView tv_region, tv_rental_num;
    }
}