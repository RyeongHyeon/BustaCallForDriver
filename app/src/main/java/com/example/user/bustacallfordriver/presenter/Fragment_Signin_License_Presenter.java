package com.example.user.bustacallfordriver.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.dialog.Dialog_Progress;
import com.example.user.bustacallfordriver.model.Rental_List;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.view.Fragment_Signin_License;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2016-11-08.
 */
public class Fragment_Signin_License_Presenter {
    Fragment_Signin_License view;
    AppController app;
    Dialog_Progress dialog_progress;

    public Fragment_Signin_License_Presenter(Fragment_Signin_License view) {
        this.view = view;
        app = (AppController) view.getActivity().getApplicationContext();
        dialog_progress = new Dialog_Progress(view.getActivity());
    }

    public void getis_Bus_license(String str) {
        if (str.equals("")) {
            view.setIs_buslicense(false);
        } else {
            view.setIs_buslicense(true);
        }
    }

    public void getis_Bus_confirm(String str) {
        if (str.equals("")) {
            view.setIs_deductionconfirm(false);
        } else {
            view.setIs_deductionconfirm(true);
        }
    }

    private MultipartBody.Part[] chageImageToFile(ArrayList<String> bodyList) {
        int count = bodyList.size();
        String[] picturepathp = new String[count];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap[] image = new Bitmap[count];
        final Bitmap[] resizeimage = new Bitmap[count];

        for (int i = 0; i < count; i++) {
            picturepathp[i] = bodyList.get(i);
            image[i] = BitmapFactory.decodeFile(picturepathp[i], options);
            resizeimage[i] = Bitmap.createScaledBitmap(image[i], 300, 300, true); //비트맵 리사이즈
        }

        File[] file = new File[count]; //file
        try {
            for (int i = 0; i < count; i++)
                file[i] = saveBitmapToJpeg(view.getActivity(), resizeimage[i], "image+1"); //file형태로 변환
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartBody.Part[] body = new MultipartBody.Part[count]; //multipart 배열
        for (int i = 0; i < count; i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file[i]);
            body[i] = MultipartBody.Part.createFormData("uploaded_file" + String.valueOf(i + 1), file[i].getName(), requestBody);
        }

        return body;
    }

    public void request_mainlogin_bus() {
        int user_bus_imagesCnt = app.getBus().getBus_url().size();
        int bus_license_confirm_imagesCnt = app.getBus().getBus_license_confirm_url().size();

        ArrayList<String> bodyList = new ArrayList<>();
        for (int i = 0; i < user_bus_imagesCnt; i++) {
            bodyList.add(app.getBus().getBus_url().get(i));
        }
        for (int i = 0; i < bus_license_confirm_imagesCnt; i++) {
            bodyList.add(app.getBus().getBus_license_confirm_url().get(i));
        }

        MultipartBody.Part[] body =  chageImageToFile(bodyList);

        RequestBody nickname = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getNickname()); //
        RequestBody phonenum = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getPhone_num());
        RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getBirthday());
        RequestBody bus_region = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getRegion());
        RequestBody bus_group = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getGroup());
        RequestBody bank = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getAccount_bank());
        RequestBody accountNum = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getAccount_num());
        RequestBody bus_num = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getBus_num());
        RequestBody bus_type = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getBus_type());
        RequestBody bus_career = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getBus_career()+"년차");
        RequestBody bus_age = RequestBody.create(MediaType.parse("text/plain"), app.getBus().getBus_age());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Void> retrofitinfo = retrofit_user.request_mainlogin_bus(nickname, phonenum, birthday, bus_region,
                bus_group, bank, accountNum, bus_num, bus_type, bus_career, bus_age, body);
        dialog_progress.show();
        retrofitinfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    app.setSavedId(app.getBus().getBus_num());
                    request_get_rental();
                } else {
                    Log.d("test", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Test", t.toString());
            }
        });
    }

    public void request_get_rental() {//
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
        Call<Rental_List> retrofitinfo = retrofit_user.request_get_rental();
        retrofitinfo.enqueue(new Callback<Rental_List>() {
            @Override
            public void onResponse(Call<Rental_List> call, Response<Rental_List> response) {
                if (response.isSuccessful()) {
                    app.setRental_list(response.body());
                    Log.d("app", app.toString());
                    view.goTonextPage();
                } else {

                }
                dialog_progress.dismiss();
            }

            @Override
            public void onFailure(Call<Rental_List> call, Throwable t) {
                dialog_progress.dismiss();
            }
        });
    }

    public File saveBitmapToJpeg(Context context, Bitmap bitmap, String name) throws IOException { //파일 명 새로지정하면서 이미지 비트맵을 임시로 생성.
        File storage = context.getCacheDir(); // 이 부분이 임시파일 저장 경로
        String fileName = name + ".jpg";  // 파일이름은 마음대로!
        File tempFile = File.createTempFile(name, ".jpg");

        try {
            tempFile.createNewFile();  // 파일을 생성해주고
            FileOutputStream out = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);  // 넘거 받은 bitmap을 jpeg(손실압축)으로 저장해줌
            out.close(); // 마무리로 닫아줍니다.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile; // 임시파일 저장경로를 리턴해주면 끝!
    }
}
