package com.lls.everydaypushup;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.lls.everydaypushup.Base.BaseActivity;
import com.lls.everydaypushup.Utils.TimeUtils;
import com.zr.library.StatusBarManager;

import java.util.Timer;
import java.util.TimerTask;

public class StartPush extends BaseActivity {

    private TextView startNum;
    private TextView tv_time;
    private Timer timer;
    int timeNum = 0;
    int OnClickNum=0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tv_time.setText(TimeUtils.stringForTime(timeNum));
                    timeNum += 1;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_push);
        StatusBarManager.getsInstance().setColor(StartPush.this, Color.TRANSPARENT);
        initView();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               mHandler.sendEmptyMessage(0);
            }
        },0, 1000);

        System.out.println("jintian egkjergjerjgh");
    }

    private void initView() {
        startNum = findViewById(R.id.startNum);
        tv_time = findViewById(R.id.tv_time);
        startNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickNum+=1;
                startNum.setText(OnClickNum+"");
            }
        });
        TextPaint tp = startNum.getPaint();

        tp.setFakeBoldText(true);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initTask() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != timer) {
            timer.cancel();
            timer.purge();
        }
    }
}
