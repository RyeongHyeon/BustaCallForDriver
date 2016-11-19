package com.bustacallfordriver.user.bustacallfordriver;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bustacallfordriver.user.bustacallfordriver.model.Notice_List;

/**
 * Created by user on 2016-11-13.
 */
public class NoticeAdapter extends BaseAdapter {

   Notice_List noticeList; // 알림 리스트
    ViewHoder viewHoder;

    public NoticeAdapter(Notice_List notice_list) {
        this.noticeList = notice_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        View v = convertView;

        String msg = noticeList.getNotice_list().get(pos).getMsg();
        String time = noticeList.getNotice_list().get(pos).getTime();

        if (v == null) {
            viewHoder = new ViewHoder();
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listview_item_notice, parent, false);
            init(v);
            v.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) v.getTag();
        }

        viewHoder.tv_msg.setText(msg);
        viewHoder.tv_time.setText(time);
        return v;
    }

    private void init(View v) {
        viewHoder.tv_msg = (TextView)v.findViewById(R.id.listview_notice_msg);
        viewHoder.tv_time = (TextView)v.findViewById(R.id.listview_notice_time);
    }

    @Override
    public int getCount() {
        return noticeList.getNotice_list().size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.getNotice_list().get(position);
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
        TextView tv_msg, tv_time;
    }
}
