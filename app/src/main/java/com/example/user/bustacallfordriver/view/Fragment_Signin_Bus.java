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

public class Fragment_Signin_Bus extends BaseFragment implements View.OnClickListener{

    public static int RESULT_LOAD_IMAGE_INNER = 1;
    public static int RESULT_LOAD_IMAGE_OUTTER = 2;
    public static int RESULT_LOAD_IMAGE_FREE1 = 3;
    public static int RESULT_LOAD_IMAGE_FREE2 = 4;

    Activity_Signin activitySignin;
    EditText et_busNum; // 차량번호
    Spinner sp_busType, sp_busCareer, sp_busAge; // 차량 종류, 차량 연식
    ImageView iv_busInner, iv_busOutter, iv_free1, iv_free2; // 차량사진
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼

    Bitmap bm_busInner, bm_busOutter, bm_free1, bm_free2; // 차량사진

    private static Fragment_Signin_Bus instnace;

    public static Fragment_Signin_Bus getInstance () {
        if ( instnace == null )
            instnace = new Fragment_Signin_Bus();
        return instnace;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_bus, null);
        init(view);
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin)getActivity();
        et_busNum = (EditText)view.findViewById(R.id.activtiy_signin_bus_et_busnum);
        sp_busType = (Spinner)view.findViewById(R.id.activtiy_signin_bus_spinner_bustype);
        sp_busCareer = (Spinner)view.findViewById(R.id.activtiy_signin_bus_spinner_buscareer);
        sp_busAge = (Spinner)view.findViewById(R.id.activtiy_signin_bus_spinner_busage);
        iv_busInner = (ImageView)view.findViewById(R.id.activtiy_signin_bus_profile_inner);
        iv_busOutter = (ImageView)view.findViewById(R.id.activtiy_signin_bus_profile_outter);
        iv_free1 = (ImageView)view.findViewById(R.id.activtiy_signin_bus_profile_free1);
        iv_free2 = (ImageView)view.findViewById(R.id.activtiy_signin_bus_profile_free2);
        tv_btnBack = (TextView)view.findViewById(R.id.activity_signin_bus_btn_back);
        tv_btnNext = (TextView)view.findViewById(R.id.activity_signin_bus_btn_next);
        tv_btnBack.setOnClickListener(this);
        tv_btnNext.setOnClickListener(this);
        iv_busInner.setOnClickListener(this);
        iv_busOutter.setOnClickListener(this);
        iv_free1.setOnClickListener(this);
        iv_free2.setOnClickListener(this);

        if(bm_busInner != null) {
            iv_busInner.setImageBitmap(bm_busInner);
        }
        if(bm_busOutter != null) {
            iv_busOutter.setImageBitmap(bm_busOutter);
        }
        if(bm_free1 != null) {
            iv_free1.setImageBitmap(bm_free1);
        }
        if(bm_free2 != null) {
            iv_free2.setImageBitmap(bm_free2);
        }

        ArrayAdapter busCareerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.busCareer, android.R.layout.simple_spinner_item);
        busCareerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_busCareer.setAdapter(busCareerAdapter);

        ArrayAdapter busTypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.busType, android.R.layout.simple_spinner_item);
        busTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_busType.setAdapter(busTypeAdapter);

        ArrayAdapter busAgeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.busAge, android.R.layout.simple_spinner_item);
        busAgeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_busAge.setAdapter(busAgeAdapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activtiy_signin_bus_profile_inner:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_INNER);
                break;
            case R.id.activtiy_signin_bus_profile_outter:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_OUTTER);
                break;
            case R.id.activtiy_signin_bus_profile_free1:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_FREE1);
                break;
            case R.id.activtiy_signin_bus_profile_free2:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE_FREE2);
                break;
            case R.id.activity_signin_bus_btn_back:
                activitySignin.setFramelayout(R.layout.fragment_signin_user);
                break;
            case R.id.activity_signin_bus_btn_next:
                activitySignin.setFramelayout(R.layout.fragment_signin_license);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == RESULT_LOAD_IMAGE_INNER || requestCode == RESULT_LOAD_IMAGE_OUTTER
        || requestCode == RESULT_LOAD_IMAGE_FREE1 || requestCode == RESULT_LOAD_IMAGE_FREE2 )
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
                    bm_busInner = BitmapFactory.decodeFile(picturePath);
                    iv_busInner.setImageBitmap(bm_busInner);
                    break;
                case 2:
                    bm_busOutter = BitmapFactory.decodeFile(picturePath);
                    iv_busOutter.setImageBitmap(bm_busOutter);
                    break;
                case 3:
                    bm_free1 = BitmapFactory.decodeFile(picturePath);
                    iv_free1.setImageBitmap(bm_free1);
                    break;
                case 4:
                    bm_free2 = BitmapFactory.decodeFile(picturePath);
                    iv_free2.setImageBitmap(bm_free2);
                    break;
            }
        }
    }
}
