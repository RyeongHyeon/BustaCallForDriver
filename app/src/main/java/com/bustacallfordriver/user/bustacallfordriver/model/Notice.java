package com.bustacallfordriver.user.bustacallfordriver.model;

/**
 * Created by user on 2016-11-19.
 */
public class Notice {
    String msg; // 알림 메세지
    String time; // 시간

    public Notice() {
    }

    public Notice(String msg, String time) {
        this.msg = msg;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
