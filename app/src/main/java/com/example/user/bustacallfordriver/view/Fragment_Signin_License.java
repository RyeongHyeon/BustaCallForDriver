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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2016-11-03.
 */

public class Fragment_Signin_License extends BaseFragment implements View.OnClickListener{

    public final int RESULT_LOAD_IMAGE_LICENSE = 1;
    public final int RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM = 2;

    Activity_Signin activitySignin;
    ImageView iv_driverLicense, iv_deductionConfirm; // 차량사진
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼

    Bitmap bm_driverLicense, bm_deductionConfirm;

    private static Fragment_Signin_License instnace;

    public static Fragment_Signin_License getInstance () {
        if ( instnace == null )
            instnace = new Fragment_Signin_License();
        return instnace;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_license, null);
        init(view);
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin)getActivity();
        iv_driverLicense = (ImageView)view.findViewById(R.id.activtiy_signin_license_driverlicense);
        iv_deductionConfirm = (ImageView)view.findViewById(R.id.activtiy_signin_license_deductionconfirm);
        tv_btnBack = (TextView)view.findViewById(R.id.activity_signin_license_btn_back);
        tv_btnNext = (TextView)view.findViewById(R.id.activity_signin_license_btn_next);
        iv_driverLicense.setOnClickListener(this);
        iv_deductionConfirm.setOnClickListener(this);
        tv_btnBack.setOnClickListener(this);
        tv_btnNext.setOnClickListener(this);

        if(bm_driverLicense != null) {
            iv_driverLicense.setImageBitmap(bm_driverLicense);
        }
        if(bm_deductionConfirm != null) {
            iv_deductionConfirm.setImageBitmap(bm_deductionConfirm);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activtiy_signin_license_driverlicense:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_LICENSE);
                break;
            case R.id.activtiy_signin_license_deductionconfirm:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM);
                break;
            case R.id.activity_signin_license_btn_back:
                activitySignin.setFramelayout(R.layout.fragment_signin_bus);
                break;
            case R.id.activity_signin_license_btn_next:
                intent = new Intent(getActivity(), Activity_Main.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == RESULT_LOAD_IMAGE_LICENSE || requestCode == RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM)
                && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            switch (requestCode) {
                case 1:
                    bm_driverLicense = BitmapFactory.decodeFile(picturePath);
                    iv_driverLicense.setImageBitmap(bm_driverLicense);
                    break;
                case 2:
                    bm_deductionConfirm = BitmapFactory.decodeFile(picturePath);
                    iv_deductionConfirm.setImageBitmap(bm_deductionConfirm);
                    break;
            }
        }
    }
}
