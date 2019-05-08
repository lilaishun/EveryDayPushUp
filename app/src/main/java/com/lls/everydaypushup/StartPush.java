package com.lls.everydaypushup;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.animation.DynamicAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lls.everydaypushup.Base.BaseActivity;
import com.lls.everydaypushup.Bean.StuInfoBean;
import com.lls.everydaypushup.Utils.TimeUtils;
import com.lls.everydaypushup.sql.SQLiteDbHelper;
import com.lls.everydaypushup.sql.StuInfoDao;
import com.zr.library.StatusBarManager;

import java.util.Timer;
import java.util.TimerTask;

public class StartPush extends BaseActivity {

    private TextView startNum;
    private TextView tv_time;
    private Timer timer;
    int timeNum = 0;
    int OnClickNum=0;
    private boolean isChecked;
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
    private Button stop_bt;
    private LinearLayout ll_dialog;
    private Button btn_ok;
private boolean stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_push);
        StatusBarManager.getsInstance().setColor(StartPush.this, Color.TRANSPARENT);
        //创建一个数据库帮助类对象
        SQLiteDbHelper mySqliteOpenHelper = new SQLiteDbHelper(StartPush.this);
        initView();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               mHandler.sendEmptyMessage(0);
            }
        },0, 1000);


    }

    private void initView() {
        btn_ok = findViewById(R.id.btn_ok);
        startNum = findViewById(R.id.startNum);
        tv_time = findViewById(R.id.tv_time);
        stop_bt = findViewById(R.id.stop_bt);
        ll_dialog = findViewById(R.id.ll);
        stop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != timer) {
                    timer.cancel();
                    timer.purge();
                }
                stop=true;
           //     ll_dialog.setVisibility(View.VISIBLE);
                isChecked=!isChecked;
                setTranls(ll_dialog);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  StuInfoBean stuInfoBean = new StuInfoBean();
                 stuInfoBean.Userid = "0";
                 stuInfoBean.UserName = "xiaoming";

                 stuInfoBean.phone = "1324647789";

                 stuInfoBean.createData = TimeUtils.ForData();
               stuInfoBean.RunTime=TimeUtils.stringForTime(timeNum);
                stuInfoBean.RunMun=OnClickNum+"";
                stuInfoBean.RunType="俯卧撑";
                 StuInfoDao studao = new StuInfoDao(StartPush.this);
                 studao.add(stuInfoBean);
                isChecked=!isChecked;
                setTranls(ll_dialog);
            }
        });
        startNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stop){
                    OnClickNum+=1;
                    startNum.setText(OnClickNum+"");
                }

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


    private void setTranls(LinearLayout imageview){
        SpringAnimation springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("translationY") {
            @Override
            public float getValue(LinearLayout object) {
                return object.getTranslationY();
            }

            @Override
            public void setValue(LinearLayout object, float value) {
                object.setTranslationY(value);
            }
        });

        float translationY = imageview.getTranslationY();
        if (isChecked) {
            imageview.setVisibility(View.VISIBLE);
            springAnimation0.setSpring(new SpringForce(translationY + 200));
        } else {

            springAnimation0.setSpring(new SpringForce(translationY - 200));
            imageview.setVisibility(View.GONE);
        }
        //作一些设置，要不然肉眼看不出来生效
        springAnimation0.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS);
    }
}
