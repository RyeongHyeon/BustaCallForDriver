package com.example.user.bustacallfordriver.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 2016-10-31.
 */

public interface Retrofit_User {

    @GET("/smssend.php")
    Call<Void> request_phonerequest(@Query("phonenum") String phonenum);

    @GET("/overlapnickname.php")
    Call<Void> request_overlapNickname(@Query("nickname") String nickname);

    // splash 말고 진짜 로그인해서 메인 넘어갈때 넘기는 부분
    @GET("/login.php")
    Call<Void> request_login(@Query("nickname") String nickname,@Query("phonenum") String phonenum, @Query("certificationnum") String certificationnum);

    // splash에서 자동 로그인
    @GET("/mainlogin.php")
    Call<User> request_mainlogin(@Query("nickname") String nickname);
}

//이게 레트로핏 쓸려면 기본
//Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
//    Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
//    Call<Void> retrofitinfo = retrofit_user.request_phonerequest(phonenum);
//retrofitinfo.enqueue