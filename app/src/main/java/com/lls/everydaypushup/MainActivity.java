package com.lls.everydaypushup;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lls.everydaypushup.Base.BaseActivity;
import com.lls.everydaypushup.Utils.ActivityUtil;
import com.zr.library.StatusBarManager;

public class MainActivity extends BaseActivity {
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
    private static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE,
            ACCESS_NETWORK_STATE
    };
    private TextView startTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println();
        StatusBarManager.getsInstance().setColor(MainActivity.this, Color.TRANSPARENT);
        startTv = findViewById(R.id.startTv);
        startTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面
                Intent intent = new Intent();
               intent.setClass(getApplicationContext(), StartPush.class);
                startActivity(intent);
            }
        });
        TextPaint tp = startTv.getPaint();

        tp.setFakeBoldText(true);
    }

    @Override
    protected void init() {

        for (int i = 0; i < requestPermissions.length; i++) {
            if (!hasPermission(requestPermissions[i])) {
                requestPermission(i, requestPermissions[i]);
            }
        }
    }

    @Override
    protected void initTask() {

    }

    // 设置返回按钮的监听事件
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 5000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    // 处理请求权限结果
    @Override
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        switch (requestCode) {
//            case ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE:// 读取手机信息权限
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 权限请求成功
//                    Toast.makeText(this, "权限请求成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    // 权限请求失败
//                    Toast.makeText(this, "权限请求失败", Toast.LENGTH_SHORT).show();
//                }
//                break;
        }
    }

    // 处理网络状态结果
    @Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);
        //     textView.setText();
        Toast.makeText(getApplicationContext(), netWorkState ? "有网络" : "无网络", 0).show();
    }
}
