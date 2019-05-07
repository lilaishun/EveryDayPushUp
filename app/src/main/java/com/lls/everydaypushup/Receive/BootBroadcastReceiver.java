package com.lls.everydaypushup.Receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



/**
 * Created by 马吉尧 on 2018/3/29.
 * 邮箱:1505508999@qq.com
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {

        }
    }
}
