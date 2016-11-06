package com.example.user.bustacallfordriver.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2016-11-03.
 */

public class Fragment_Signin_User extends BaseFragment implements View.OnClickListener{

    public static int RESULT_LOAD_IMAGE = 1;

    Activity_Signin activitySignin;
    ImageView iv_profile; // 프로필 사진
    EditText et_name, et_birth, et_group, et_accountNum; // 이름, 생년월일, 계좌번호
    Spinner sp_workArea, sp_back; // 영업 지역, 계좌 은행
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼
    Bitmap bitmap_profile;

    private static Fragment_Signin_User instnace;

    public static Fragment_Signin_User getInstance () {
        if ( instnace == null )
            instnace = new Fragment_Signin_User();
        return instnace;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_user, null);
        init(view);
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin) getActivity();
        iv_profile = (ImageView)view.findViewById(R.id.activtiy_signin_user_profile);
        et_name = (EditText)view.findViewById(R.id.activtiy_signin_user_et_name);
        et_birth = (EditText)view.findViewById(R.id.activtiy_signin_user_et_birth);
        et_group = (EditText)view.findViewById(R.id.activtiy_signin_user_et_group);
        et_accountNum = (EditText)view.findViewById(R.id.activtiy_signin_user_et_accountnum);
        sp_workArea = (Spinner)view.findViewById(R.id.activtiy_signin_user_spinner_workarea);
        sp_back = (Spinner)view.findViewById(R.id.activtiy_signin_user_spinner_bank);
        tv_btnBack = (TextView)view.findViewById(R.id.activity_signin_user_btn_back);
        tv_btnNext = (TextView)view.findViewById(R.id.activity_signin_user_btn_next);
        iv_profile.setOnClickListener(this);
        tv_btnBack.setOnClickListener(this);
        tv_btnNext.setOnClickListener(this);

        if(bitmap_profile != null) {
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
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
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
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            System.out.println("령현 :" + selectedImage);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmap_profile = BitmapFactory.decodeFile(picturePath);
            iv_profile.setImageBitmap(bitmap_profile);
        }
    }
}
