package com.bustacallfordriver.user.bustacallfordriver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-11-19.
 */

public class Notice_List {
    List<Notice> notice_list = new ArrayList<>();

    public List<Notice> getNotice_list() {return notice_list;}

    public void setNotice_list(List<Notice> notice_list) {
        this.notice_list = notice_list;
    }
}
