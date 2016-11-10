package com.example.user.bustacallfordriver.model;

import java.io.Serializable;

/**
 * Created by user on 2016-11-06.
 */
public class Together implements Serializable {
    int flag;//합승 여부 0 : 합승 안함, 1 : 합승 함
    String user_count;//몇명이 합승할것인지
    String money;//합승 가격.
    int rental_num;//고유 렌탈 num

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Together(int flag, String user_count, String money) {
        this.flag = flag;
        this.user_count = user_count;
        this.money = money;
        this.flag = 0;
    }

    public int getRental_num() {
        return rental_num;
    }

    public void setRental_num(int rental_num) {
        this.rental_num = rental_num;
    }

    public Together(){
        this.flag = 0;
    }
}
