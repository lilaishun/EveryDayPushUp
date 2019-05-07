package com.lls.everydaypushup.App;

import android.app.Application;

import com.zr.library.StatusBarManager;
import com.zr.library.config.DefaultStatusBarCompatConfig;

/**
 * 作者：我的孩子叫好帅 on 2018/8/2 13:35
 * Q Q：779594494
 * 邮箱：17600949389@163.com
 */
public class MyApp  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarManager.getsInstance().init(new DefaultStatusBarCompatConfig());
    }
}
