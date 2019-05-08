package com.lls.everydaypushup.spring;

import android.view.animation.Interpolator;

/**
 * 作者：我的孩子叫好帅 on 2018/8/2 13:35
 * Q Q：779594494
 * 邮箱：17600949389@163.com
 * 差值器
 */
public class SpringInterpolator implements Interpolator {

    private float factor;

    public SpringInterpolator(float factor) {
        this.factor = factor;
    }

    @Override
    public float getInterpolation(float input) {
        //factor = 0.4
//        pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1

        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
