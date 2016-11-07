package com.example.user.bustacallfordriver.model;

/**
 * 사용자가 올린 렌탈 정보. 매물 정보
 * Created by user on 2016-11-02.
 */
public class Rental {
//    private int type; //왕복,편도,합승목적지,목적
//    //1일때 왕복
//    //2일때 편도
    private int type; //왕복,편도,합승목적지,목적
    // 1 : 왕복
    // 2 : 왕복 & 같이타기
    // 3 : 편도 &
    // 4 : 편도
    private String start_point_one,start_point_two,end_point_one,end_point_two; //출발지, 도착지
    private String day_one,day_two,time_one,time_two; //날짜, 시간
    private String rental_reason; //목적
    private String user_count; //명수
    private String rental_money; //비용
    private String current_day;//예약한 날짜

    private int bus_45;//45인승 대형
    private int bus_35;//35인승 중형
    private int bus_28;//28인승 리무진
    private int bus_25;//25인승 소형

    int type_two; //예약, 기사 선택 하기, 입금 전, 상세보기, 이용완료 1,2,3,4,5

    public String getCurrent_day() {
        return current_day;
    }

    public void setCurrent_day(String current_day) {
        this.current_day = current_day;
    }

    public int getBus_45() {
        return bus_45;
    }

    public void setBus_45(int bus_45) {
        this.bus_45 = bus_45;
    }

    public int getBus_35() {
        return bus_35;
    }

    public void setBus_35(int bus_35) {
        this.bus_35 = bus_35;
    }

    public int getBus_28() {
        return bus_28;
    }

    public void setBus_28(int bus_28) {
        this.bus_28 = bus_28;
    }

    public int getBus_25() {
        return bus_25;
    }

    public void setBus_25(int bus_25) {
        this.bus_25 = bus_25;
    }


    public Rental(){}

    public Rental(String start_point_one, String start_point_two, String end_point_one,
                  String end_point_two, String day_one, String day_two, String time_one, String time_two,
                  String rental_reason, String user_count,int type,String rental_money,int type_two,
                  String current_day,int bus_45,int bus_35, int bus_28, int bus_25) {
        this.start_point_one = start_point_one;
        this.start_point_two = start_point_two;
        this.end_point_one = end_point_one;
        this.end_point_two = end_point_two;
        this.day_one = day_one;
        this.day_two = day_two;
        this.time_one = time_one;
        this.time_two = time_two;
        this.rental_reason = rental_reason;
        this.type = type;
        this.user_count=user_count;
        this.rental_money=rental_money;
        this.type_two = type_two;
        this.current_day = current_day;
        this.bus_45 = bus_45;
        this.bus_35 = bus_35;
        this.bus_28 = bus_28;
        this.bus_25 = bus_25;
        this.type_two=0; //처음에 아무것도 안한상태.
    }

    public int getType_two() {
        return type_two;
    }

    public void setType_two(int type_two) {
        this.type_two = type_two;
    }
    public String getRental_money() {
        return rental_money;
    }

    public void setRental_money(String rental_money) {
        this.rental_money = rental_money;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public String getStart_point_one() {
        return start_point_one;
    }

    public void setStart_point_one(String start_point_one) {
        this.start_point_one = start_point_one;
    }

    public String getStart_point_two() {
        return start_point_two;
    }

    public void setStart_point_two(String start_point_two) {
        this.start_point_two = start_point_two;
    }

    public String getEnd_point_one() {
        return end_point_one;
    }

    public void setEnd_point_one(String end_point_one) {
        this.end_point_one = end_point_one;
    }

    public String getEnd_point_two() {
        return end_point_two;
    }

    public void setEnd_point_two(String end_point_two) {
        this.end_point_two = end_point_two;
    }

    public String getDay_one() {
        return day_one;
    }

    public void setDay_one(String day_one) {
        this.day_one = day_one;
    }

    public String getDay_two() {
        return day_two;
    }

    public void setDay_two(String day_two) {
        this.day_two = day_two;
    }

    public String getTime_one() {
        return time_one;
    }

    public void setTime_one(String time_one) {
        this.time_one = time_one;
    }

    public String getTime_two() {
        return time_two;
    }

    public void setTime_two(String time_two) {
        this.time_two = time_two;
    }

    public String getRental_reason() {
        return rental_reason;
    }

    public void setRental_reason(String rental_reason) {
        this.rental_reason = rental_reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
