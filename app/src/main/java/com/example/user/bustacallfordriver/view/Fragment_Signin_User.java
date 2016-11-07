package com.example.user.bustacallfordriver.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.BitmapUtil;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.Result;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2016-11-03.
 */

public class Fragment_Signin_User extends BaseFragment implements View.OnClickListener {

    public static int RESULT_LOAD_IMAGE = 1;
    int count = 0;
    String[] picturepathp = new String[5];
    Activity_Signin activitySignin;
    ImageView iv_profile; // 프로필 사진
    EditText et_name, et_birth, et_group, et_accountNum; // 이름, 생년월일, 계좌번호
    Spinner sp_workArea, sp_back; // 영업 지역, 계좌 은행
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼
    Bitmap bitmap_profile;
    AppController app;

    private static Fragment_Signin_User instnace;

    public static Fragment_Signin_User getInstance() {
        if (instnace == null)
            instnace = new Fragment_Signin_User();
        return instnace;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_user, null);
        init(view);
        app = (AppController) getActivity().getApplicationContext();
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin) getActivity();
        iv_profile = (ImageView) view.findViewById(R.id.activtiy_signin_user_profile);
        et_name = (EditText) view.findViewById(R.id.activtiy_signin_user_et_name);
        et_birth = (EditText) view.findViewById(R.id.activtiy_signin_user_et_birth);
        et_group = (EditText) view.findViewById(R.id.activtiy_signin_user_et_group);
        et_accountNum = (EditText) view.findViewById(R.id.activtiy_signin_user_et_accountnum);
        sp_workArea = (Spinner) view.findViewById(R.id.activtiy_signin_user_spinner_workarea);
        sp_back = (Spinner) view.findViewById(R.id.activtiy_signin_user_spinner_bank);
        tv_btnBack = (TextView) view.findViewById(R.id.activity_signin_user_btn_back);
        tv_btnNext = (TextView) view.findViewById(R.id.activity_signin_user_btn_next);
        iv_profile.setOnClickListener(this);
        tv_btnBack.setOnClickListener(this);
        tv_btnNext.setOnClickListener(this);

        if (bitmap_profile != null) {
            iv_profile.setImageBitmap(bitmap_profile);
        }

        ArrayAdapter workAreaAdapter = ArrayAdapter.createFromResource(getContext(), R.array.workArea, android.R.layout.simple_spinner_item);
        workAreaAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_workArea.setAdapter(workAreaAdapter);

        ArrayAdapter bankAdapter = ArrayAdapter.createFromResource(getContext(), R.array.bank, android.R.layout.simple_spinner_item);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_back.setAdapter(bankAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activtiy_signin_user_profile:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activity_signin_user_btn_back:
                activitySignin.finish();
                break;
            case R.id.activity_signin_user_btn_next:
                activitySignin.setFramelayout(R.layout.fragment_signin_bus);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            System.out.println("령현 :" + selectedImage);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //String picturePath = cursor.getString(columnIndex);
            picturepathp[count] = cursor.getString(columnIndex);
            cursor.close();
            if (count <3) {
                count++;
                return;
            } else {
                count++;
                //bitmap_profile = BitmapFactory.decodeFile(picturePath);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap[] image = new Bitmap[5];
                Bitmap[] resizeimage = new Bitmap[5];
                for(int i=0;i<count;i++)
                {
                    image[i] = BitmapFactory.decodeFile(picturepathp[i],options);
                    resizeimage[i] = Bitmap.createScaledBitmap(image[i],300,300,true);
                }

                File[] file = new File[5];
                try {
                    for(int i=0;i<count;i++)
                        file[i] = saveBitmapToJpeg(getContext(),resizeimage[i],"image+1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MultipartBody.Part[] body2 = new MultipartBody.Part[5];
                for(int i=0;i<count;i++){
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file[i]);
                    body2[i] = MultipartBody.Part.createFormData("uploaded_file"+String.valueOf(i+1), file[i].getName(), requestBody);
                }
                Retrofit retrofit = new Retrofit.Builder().baseUrl(AppController.SERVERIP).addConverterFactory(GsonConverterFactory.create()).build();
                Retrofit_User retrofit_user = retrofit.create(Retrofit_User.class);
                Call<Void> retrofitinfo = retrofit_user.request_test(body2);
                retrofitinfo.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", "qt");
                        } else {
                            Log.d("test", "qt");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("test", "qt");
                    }
                });
                iv_profile.setImageBitmap(resizeimage[0]);
            }
        }
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }

    };

    public File saveBitmapToJpeg(Context context,Bitmap bitmap, String name) throws IOException {

        File storage = context.getCacheDir(); // 이 부분이 임시파일 저장 경로
        String fileName = name + ".jpg";  // 파일이름은 마음대로!
        File tempFile = File.createTempFile(name,".jpg");
        try{
            tempFile.createNewFile();  // 파일을 생성해주고
            FileOutputStream out = new FileOutputStream(tempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , out);  // 넘거 받은 bitmap을 jpeg(손실압축)으로 저장해줌
            out.close(); // 마무리로 닫아줍니다.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile; // 임시파일 저장경로를 리턴해주면 끝!
    }
}
