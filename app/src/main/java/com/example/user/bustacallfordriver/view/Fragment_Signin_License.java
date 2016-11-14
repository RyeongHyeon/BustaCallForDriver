package com.example.user.bustacallfordriver.view;

import android.Manifest;
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

import com.example.user.bustacallfordriver.AppController;
import com.example.user.bustacallfordriver.BaseFragment;
import com.example.user.bustacallfordriver.R;
import com.example.user.bustacallfordriver.presenter.Fragment_Signin_License_Presenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2016-11-03.
 */

public class Fragment_Signin_License extends BaseFragment implements View.OnClickListener {

    public final int RESULT_LOAD_IMAGE_LICENSE = 1;
    public final int RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM = 2;
    ArrayList<String> bus_url_one = new ArrayList<>();
    ArrayList<String> bus_url_two = new ArrayList<>();
    Activity_Signin activitySignin;
    ImageView iv_driverLicense, iv_deductionConfirm; // 차량사진
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼

    Bitmap bm_driverLicense, bm_deductionConfirm;
    Intent intent;
    boolean is_buslicense;
    boolean is_deductionconfirm;
    AppController app;
    private static Fragment_Signin_License instnace;
    Fragment_Signin_License_Presenter presenter;

    public static Fragment_Signin_License getInstance() {
        if (instnace == null)
            instnace = new Fragment_Signin_License();
        return instnace;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_license, null);
        init(view);
        app = (AppController) getActivity().getApplicationContext();
        presenter = new Fragment_Signin_License_Presenter(this);
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin) getActivity();
        iv_driverLicense = (ImageView) view.findViewById(R.id.activtiy_signin_license_driverlicense);
        iv_deductionConfirm = (ImageView) view.findViewById(R.id.activtiy_signin_license_deductionconfirm);
        tv_btnBack = (TextView) view.findViewById(R.id.activity_signin_license_btn_back);
        tv_btnNext = (TextView) view.findViewById(R.id.activity_signin_license_btn_next);
        iv_driverLicense.setOnClickListener(this);
        iv_deductionConfirm.setOnClickListener(this);
        tv_btnBack.setOnClickListener(this);
        tv_btnNext.setOnClickListener(this);

        if (bm_driverLicense != null) {
            iv_driverLicense.setImageBitmap(bm_driverLicense);
        }
        if (bm_deductionConfirm != null) {
            iv_deductionConfirm.setImageBitmap(bm_deductionConfirm);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activtiy_signin_license_driverlicense:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activtiy_signin_license_deductionconfirm:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener_confirm)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activity_signin_license_btn_back:
                activitySignin.setFramelayout(R.layout.fragment_signin_bus);
                break;
            case R.id.activity_signin_license_btn_next:
                checkPermission();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == RESULT_LOAD_IMAGE_LICENSE || requestCode == RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM)
                && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap image;
            Bitmap resizeimage;
            image = BitmapFactory.decodeFile(picturePath, options);
            resizeimage = Bitmap.createScaledBitmap(image, 300, 300, true);


            switch (requestCode) {
                case 1:
                    iv_driverLicense.setImageBitmap(resizeimage);
                    presenter.getis_Bus_license(picturePath);
                    app.getBus().setBus_license(picturePath);
                    break;
                case 2:
                    iv_deductionConfirm.setImageBitmap(resizeimage);
                    presenter.getis_Bus_confirm(picturePath);
                    app.getBus().setBus_confirm(picturePath);
                    break;
            }
        }
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_LICENSE);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };

    PermissionListener permissionlistener_confirm = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_DEDUCTIONCONFIRM);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };

    public boolean is_deductionconfirm() {
        return is_deductionconfirm;
    }

    public void setIs_deductionconfirm(boolean is_deductionconfirm) {
        this.is_deductionconfirm = is_deductionconfirm;
    }

    public boolean is_buslicense() {
        return is_buslicense;
    }

    public void setIs_buslicense(boolean is_buslicense) {
        this.is_buslicense = is_buslicense;
    }

    public void checkPermission() {
        if (is_buslicense == true && is_deductionconfirm == true) {
            //여기서 통신
            presenter.request_mainlogin_bus();
        }
    }

    public void goTonextPage(){
        intent = new Intent(getActivity(), Activity_Main.class);
        startActivity(intent);
    }
}
