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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.model.Bus;
import com.example.user.bustacallfordriver.model.Retrofit_User;
import com.example.user.bustacallfordriver.presenter.Fragment_Signin_User_Presenter;
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
    Activity_Signin activitySignin;
    ImageView iv_profile; // 프로필 사진
    EditText et_name, et_birth, et_group, et_accountNum; // 이름, 생년월일, 계좌번호
    Spinner sp_workArea, sp_back; // 영업 지역, 계좌 은행
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼
    Bitmap bitmap_profile;
    AppController app;
    Bus bus = new Bus();
    String picturepath;
    Fragment_Signin_User_Presenter presenter;
    boolean is_profile,is_nickname,is_birthady,is_group,is_account_num;

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
        presenter = new Fragment_Signin_User_Presenter(this);
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
        et_name.addTextChangedListener(tw_nickname);
        et_birth.addTextChangedListener(tw_brithday);
        et_group.addTextChangedListener(tw_group);
        et_accountNum.addTextChangedListener(tw_account_num);

        if (bitmap_profile != null) {
            iv_profile.setImageBitmap(bitmap_profile);
        }

        ArrayAdapter workAreaAdapter = ArrayAdapter.createFromResource(getContext(), R.array.workArea, android.R.layout.simple_spinner_item);
        workAreaAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_workArea.setAdapter(workAreaAdapter);

        sp_workArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selItem= (String)sp_workArea.getSelectedItem();
                app.getBus().setRegion(selItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter bankAdapter = ArrayAdapter.createFromResource(getContext(), R.array.bank, android.R.layout.simple_spinner_item);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_back.setAdapter(bankAdapter);

        sp_back.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selItem= (String)sp_back.getSelectedItem();
                bus.setAccount_bank(selItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                CheckNextToPage();
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

            picturepath = cursor.getString(columnIndex);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap image;
            Bitmap resizeimage;
            image = BitmapFactory.decodeFile(picturepath, options);
            resizeimage = Bitmap.createScaledBitmap(image, 300, 300, true);

            iv_profile.setImageBitmap(resizeimage);
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

    public boolean is_profile() {
        return is_profile;
    }

    public void setIs_profile(boolean is_profile) {
        this.is_profile = is_profile;
    }

    public boolean is_nickname() {
        return is_nickname;
    }

    public void setIs_nickname(boolean is_nickname) {
        this.is_nickname = is_nickname;
    }

    public boolean is_birthady() {
        return is_birthady;
    }

    public void setIs_birthady(boolean is_birthady) {
        this.is_birthady = is_birthady;
    }

    public boolean is_group() {
        return is_group;
    }

    public void setIs_group(boolean is_group) {
        this.is_group = is_group;
    }

    public boolean is_account_num() {
        return is_account_num;
    }

    public void setIs_account_num(boolean is_account_num) {
        this.is_account_num = is_account_num;
    }

    TextWatcher tw_nickname = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_Nickname(et_name.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tw_brithday = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_Brithday(et_birth.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tw_group = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_Group(et_group.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tw_account_num = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.getis_Account_Num(et_accountNum.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void CheckNextToPage(){
        if(is_account_num==true&&is_birthady==true&&is_group==true&&is_nickname){
            app.getBus().setNickname(et_name.getText().toString());
            app.getBus().setGroup(et_group.getText().toString());
            app.getBus().setAccount_num(et_accountNum.getText().toString());
            app.getBus().setBirthday(et_birth.getText().toString());
            app.getBus().getBus_url().add(picturepath);
            Log.d("bus",app.toString());
            activitySignin.setFramelayout(R.layout.fragment_signin_bus);
        }
    }
}
