package com.example.user.bustacallfordriver.model;


import java.util.ArrayList;

/**
 * 사용자 정보
 * Created by user on 2016-10-31.
 */
public class User {
    /**개인정보*/
    String user_PhoneNum;       // 휴대폰 번호
//    ImgUrl user_Profile;        // 프로필 사진
    String user_Name;           // 이름
    String user_BirthDate;      // 생년월일
    String user_WorkArea;       // 영업 지역
    String user_Group;          // 소속
    String user_AccountBank;    // 계좌 은행
    String user_AccountNum;     // 계좌 번호


    /**가입 후 가지는 정보*/
    String user_point;          // 포인트
    ArrayList<Tender> tenderList = new ArrayList<>(); // 입찰 리스트

    public User() {this.user_point ="0";}

    public String getUser_PhoneNum() {
        return user_PhoneNum;
    }

    public void setUser_PhoneNum(String user_PhoneNum) {
        this.user_PhoneNum = user_PhoneNum;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_BirthDate() {
        return user_BirthDate;
    }

    public void setUser_BirthDate(String user_BirthDate) {
        this.user_BirthDate = user_BirthDate;
    }

    public String getUser_WorkArea() {
        return user_WorkArea;
    }

    public void setUser_WorkArea(String user_WorkArea) {
        this.user_WorkArea = user_WorkArea;
    }

    public String getUser_Group() {
        return user_Group;
    }

    public void setUser_Group(String user_Group) {
        this.user_Group = user_Group;
    }

    public String getUser_AccountBank() {
        return user_AccountBank;
    }

    public void setUser_AccountBank(String user_AccountBank) {
        this.user_AccountBank = user_AccountBank;
    }

    public String getUser_AccountNum() {
        return user_AccountNum;
    }

    public void setUser_AccountNum(String user_AccountNum) {
        this.user_AccountNum = user_AccountNum;
    }

    public String getUser_point() {
        return user_point;
    }

    public void setUser_point(String user_point) {
        this.user_point = user_point;
    }

    public ArrayList<Tender> getTenderList() {
        return tenderList;
    }

    public void setTenderList(ArrayList<Tender> tenderList) {
        this.tenderList = tenderList;
    }
}
