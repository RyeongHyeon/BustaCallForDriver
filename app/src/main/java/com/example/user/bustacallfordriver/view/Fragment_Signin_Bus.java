package com.example.user.bustacallfordriver.view;

import android.Manifest;
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
import com.example.user.bustacallfordriver.presenter.Fragment_Signin_Bus_Presenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 2016-11-03.
 */

public class Fragment_Signin_Bus extends BaseFragment implements View.OnClickListener{

    public static int RESULT_LOAD_IMAGE_INNER = 1;
    public static int RESULT_LOAD_IMAGE_OUTTER = 2;
    public static int RESULT_LOAD_IMAGE_FREE1 = 3;
    public static int RESULT_LOAD_IMAGE_FREE2 = 4;

    ArrayList<String> bus_url_one = new ArrayList<>();
    ArrayList<String> bus_url_two = new ArrayList<>();
    ArrayList<String> bus_url_three = new ArrayList<>();
    ArrayList<String> bus_url_four = new ArrayList<>();

    AppController app;
    Activity_Signin activitySignin;
    EditText et_busNum,et_buscareer,et_busage; // 차량번호
    Spinner sp_busType; // 차량 종류, 차량 연식
    ImageView iv_busInner, iv_busOutter, iv_free1, iv_free2; // 차량사진
    TextView tv_btnBack, tv_btnNext; // 이전 버튼, 다음 버튼

    Bitmap bm_busInner, bm_busOutter, bm_free1, bm_free2; // 차량사진

    boolean is_busnum;
    boolean is_buscareer;
    boolean is_busage;
    boolean is_bustype;
    boolean is_busprofile1;
    boolean is_busprofile2;
    Fragment_Signin_Bus_Presenter presenter;
    private static Fragment_Signin_Bus instnace;
    Intent intent;

    public static Fragment_Signin_Bus getInstance () {
        if ( instnace == null )
            instnace = new Fragment_Signin_Bus();
        return instnace;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_bus, null);
        init(view);
        app = (AppController)getActivity().getApplicationContext();
        presenter = new Fragment_Signin_Bus_Presenter(this);
        return view;
    }

    private void init(View view) {
        activitySignin = (Activity_Signin)getActivity();
        et_busNum = (EditText)view.findViewById(R.id.activtiy_signin_bus_et_busnum);
        et_busage = (EditText)view.findViewById(R.id.activtiy_signin_bus_et_busage);
        et_buscareer = (EditText)view.findViewById(R.id.activtiy_signin_bus_et_career);
        sp_busType = (Spinner)view.findViewById(R.id.activtiy_signin_bus_spinner_bustype);
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
        et_busNum.addTextChangedListener(tw_busnum);
        et_buscareer.addTextChangedListener(tw_buscareer);
        et_busage.addTextChangedListener(tw_busage);

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

       ArrayAdapter busTypeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.busType, android.R.layout.simple_spinner_item);
        busTypeAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_busType.setAdapter(busTypeAdapter);
        sp_busType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selItem= (String)sp_busType.getSelectedItem();
                presenter.setis_Bustype(selItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activtiy_signin_bus_profile_inner:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener_inner)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activtiy_signin_bus_profile_outter:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener_outter)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activtiy_signin_bus_profile_free1:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener_free1)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activtiy_signin_bus_profile_free2:
                new TedPermission(getContext())
                        .setPermissionListener(permissionlistener_free2)
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();
                break;
            case R.id.activity_signin_bus_btn_back:
                activitySignin.setFramelayout(R.layout.fragment_signin_user);
                break;
            case R.id.activity_signin_bus_btn_next:
                checkTonextPage();
                Log.d("Test",app.toString());
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

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap image;
            Bitmap resizeimage;
            image = BitmapFactory.decodeFile(picturePath, options);
            resizeimage = Bitmap.createScaledBitmap(image, 300, 300, true);

            switch (requestCode) {
                case 1:
                    bus_url_one.add(picturePath);
                    iv_busInner.setImageBitmap(resizeimage);
                    presenter.setis_Busprofile1(picturePath);
                    break;
                case 2:
                    bus_url_two.add(picturePath);
                    iv_busOutter.setImageBitmap(resizeimage);
                    presenter.setis_Busprofile2(picturePath);
                    break;
                case 3:
                    bus_url_three.add(picturePath);
                    iv_free1.setImageBitmap(resizeimage);
                    break;
                case 4:
                    bus_url_four.add(picturePath);
                    iv_free2.setImageBitmap(resizeimage);
                    break;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////
    PermissionListener permissionlistener_inner = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_INNER);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };
    PermissionListener permissionlistener_outter = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_OUTTER);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };
    PermissionListener permissionlistener_free1 = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_FREE1);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };
    PermissionListener permissionlistener_free2 = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE_FREE2);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
        }
    };
    //////////////////////////////////////////////////////////////////////////////

    TextWatcher tw_busnum = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.setis_Busnum(et_busNum.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher tw_buscareer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.setis_Buscareer(et_buscareer.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher tw_busage = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.setis_Busage(et_busage.getText().toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public boolean is_busnum() {
        return is_busnum;
    }

    public void setIs_busnum(boolean is_busnum) {
        this.is_busnum = is_busnum;
    }

    public boolean is_buscareer() {
        return is_buscareer;
    }

    public void setIs_buscareer(boolean is_buscareer) {
        this.is_buscareer = is_buscareer;
    }

    public boolean is_busage() {
        return is_busage;
    }

    public void setIs_busage(boolean is_busage) {
        this.is_busage = is_busage;
    }

    public boolean is_busprofile1() {
        return is_busprofile1;
    }

    public void setIs_busprofile1(boolean is_busprofile1) {
        this.is_busprofile1 = is_busprofile1;
    }

    public boolean is_busprofile2() {
        return is_busprofile2;
    }

    public void setIs_busprofile2(boolean is_busprofile2) {
        this.is_busprofile2 = is_busprofile2;
    }

    public boolean is_bustype() {
        return is_bustype;
    }

    public void setIs_bustype(boolean is_bustype) {
        this.is_bustype = is_bustype;
    }

    public void checkTonextPage(){
        if(is_busage==true&&is_buscareer==true&&is_busnum==true&&is_busprofile1==true&&is_busprofile2==true){
            activitySignin.setFramelayout(R.layout.fragment_signin_license);
            /////////////////////////////////////////////////////////
            app.getBus().getBus_url().add(bus_url_one.get(bus_url_one.size()-1));
            app.getBus().getBus_url().add(bus_url_two.get(bus_url_two.size()-1));
            if(bus_url_three.size() != 0){
                app.getBus().getBus_url().add(bus_url_three.get(bus_url_three.size()-1));
            }
            if(bus_url_four.size() != 0 ){
                app.getBus().getBus_url().add(bus_url_four.get(bus_url_four.size()-1));
            }
            /////////////////////////////////////////////////////////
            app.getBus().setBus_num(et_busNum.getText().toString());
            app.getBus().setBus_type(sp_busType.getSelectedItem().toString().trim());
            app.getBus().setBus_career(et_buscareer.getText().toString());
            app.getBus().setBus_age(et_busage.getText().toString());
        }
    }
}
