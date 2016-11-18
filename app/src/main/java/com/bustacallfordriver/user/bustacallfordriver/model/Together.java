package com.bustacallfordriver.user.bustacallfordriver.model;

import java.io.Serializable;

/**
 * Created by user on 2016-11-06.
 */
public class Together implements Serializable {
    int flag;//합승 여부 0 : 합승 안함, 1 : 합승 함, 2 : 같이 타기
    int max_user_count;//몇명이 합승할것인지
    int current_user_count;//현재까지 몇명이 합승중인지
    String money;//합승 가격.
    int rental_num;//고유 렌탈 num
    String text;//같이 타기 할때 내용


    public Together(){
        this.flag = 0;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getMax_user_count() {
        return max_user_count;
    }

    public void setMax_user_count(int max_user_count) {
        this.max_user_count = max_user_count;
    }

    public int getCurrent_user_count() {
        return current_user_count;
    }

    public void setCurrent_user_count(int current_user_count) {
        this.current_user_count = current_user_count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getRental_num() {
        return rental_num;
    }

    public void setRental_num(int rental_num) {
        this.rental_num = rental_num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
