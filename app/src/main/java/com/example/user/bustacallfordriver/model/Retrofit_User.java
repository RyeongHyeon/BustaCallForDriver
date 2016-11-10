package com.example.user.bustacallfordriver.model;

import android.content.Intent;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user on 2016-10-31.
 */

public interface Retrofit_User {

    @GET("/smssend_bus.php") //인증 번호요청
    Call<Void> request_phonerequest(@Query("phonenum") String phonenum);

    // splash 말고 진짜 로그인해서 메인 넘어갈때 넘기는 부분
    @GET("/login_bus.php") //login 레이아웃에서 다음 화면으로 넘어갈때, 인증번호 맞는지 여부, 푸쉬용 토큰
    Call<Void> request_login(@Query("phonenum") String phonenum, @Query("certificationnum") String certificationnum,@Query("token")String token);

    @Multipart //이미지 처리할때
    @POST("/mainlogin_bus.php")//회원가입 마지막에 전부다 보낼때
    Call<Void> request_mainlogin_bus(@Part("nickname") RequestBody nickname, @Part("phone_num") RequestBody phonenum,
                                     @Part("bus_type") RequestBody bus_type,@Part("bus_num") RequestBody bus_num,
                                     @Part("bus_career") RequestBody bus_career,@Part("region")RequestBody bus_region,@Part MultipartBody.Part[] image1);

    @GET("/autologin_bus.php")//오토 로그인
    Call<Bus> request_autologin_bus(@Query("bus_num") String bus_num);

    @GET("/get_rental.php")//렌탈 리스트
    Call<Rental_List> request_get_rental();

    @GET("/send_bus.php")
    Call<JsonObject> request_send_bus(@Query("rental_num") int rental_num, @Query("bus_num")String bus_num, @Query("money")String money, @Query("money_one")int money_one,
                                      @Query("money_two")int money_two, @Query("money_three")int money_three, @Query("money_four")int money_four, @Query("money_five")int money_five,
                                      @Query("money_six")int money_six);
}
