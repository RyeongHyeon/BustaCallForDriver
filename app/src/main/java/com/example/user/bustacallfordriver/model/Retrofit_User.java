package com.example.user.bustacallfordriver.model;

import java.io.File;

import javax.xml.transform.Result;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user on 2016-10-31.
 */

public interface Retrofit_User {

    @GET("/smssend_bus.php")
    Call<Void> request_phonerequest(@Query("phonenum") String phonenum);

    // splash 말고 진짜 로그인해서 메인 넘어갈때 넘기는 부분
    @GET("/login_bus.php")
    Call<Void> request_login(@Query("phonenum") String phonenum, @Query("certificationnum") String certificationnum,@Query("token")String token);

    @Multipart
    @POST("/test.php")
    Call<Void> request_test(@Part MultipartBody.Part[] image1);
}

//이게 레트로핏 쓸려면 기본
//Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
//    Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
//    Call<Void> retrofitinfo = retrofit_user.request_phonerequest(phonenum);
//retrofitinfo.enqueue