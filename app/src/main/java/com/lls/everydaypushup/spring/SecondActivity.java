package com.lls.everydaypushup.spring;

import android.support.animation.DynamicAnimation;
import android.support.animation.FloatPropertyCompat;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lls.everydaypushup.R;

public class SecondActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private LinearLayout imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        setTitle("SpringAnimation实现弹性动画");

        CheckBox cb0 = (CheckBox) findViewById(R.id.cb_0);
        CheckBox cb1 = (CheckBox) findViewById(R.id.cb_1);
        CheckBox cb2 = (CheckBox) findViewById(R.id.cb_2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.cb_3);


        cb0.setOnCheckedChangeListener(this);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);

        imageview = (LinearLayout) findViewById(R.id.ll);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        SpringAnimation springAnimation0 = null;
        SpringAnimation springAnimation1 = null;

        switch (buttonView.getId()) {
            case R.id.cb_0://缩放

                if (isChecked) {
                    imageview.setVisibility(View.GONE);
                    springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("scaleX") {
                        @Override
                        public float getValue(LinearLayout object) {
                            float scaleX = object.getScaleX();
//                            Log.i("", "get:scaleX == " + scaleX);
                            return scaleX;
                        }

                        @Override
                        public void setValue(LinearLayout object, float value) {
//                            Log.i("", "set:scaleX == " + value);
                            object.setScaleX(value);
                        }
                    }, 2.0f);
                    springAnimation1 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("scaleY") {
                        @Override
                        public float getValue(LinearLayout object) {
                            float scaleY = object.getScaleY();
//                            Log.i("", "get:scaleY == " + scaleY);
                            return scaleY;
                        }

                        @Override
                        public void setValue(LinearLayout object, float value) {
//                            Log.i("", "set:scaleY == " + value);
                            object.setScaleY(value);
                        }
                    }, 2.0f);
                } else {
                    springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("scaleX") {
                        @Override
                        public float getValue(LinearLayout object) {
                            float scaleX = object.getScaleX();
//                            Log.i("", "get:scaleX0 == " + scaleX);
                            return scaleX;
                        }

                        @Override
                        public void setValue(LinearLayout object, float value) {
//                            Log.i("", "set:scaleX0 == " + value);
                            object.setScaleX(value);
                        }
                    }, 1.0f);
                    springAnimation1 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("scaleY") {
                        @Override
                        public float getValue(LinearLayout object) {
                            float scaleY = object.getScaleY();
//                            Log.i("", "get:scaleY0 == " + scaleY);
                            return scaleY;
                        }

                        @Override
                        public void setValue(LinearLayout object, float value) {
//                            Log.i("", "set:scaleY0 == " + value);
                            object.setScaleY(value);
                        }
                    }, 1.0f);
                    imageview.setVisibility(View.VISIBLE);
                }

                //作一些设置，要不然肉眼看不出来生效
                springAnimation0.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);
                springAnimation1.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);

                break;
            case R.id.cb_1://平移

                springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("translationY") {
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

                break;
            case R.id.cb_2://旋转
                springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("rotation") {
                    @Override
                    public float getValue(LinearLayout object) {
                        return object.getRotation();
                    }

                    @Override
                    public void setValue(LinearLayout object, float value) {
                        object.setRotation(value);
                    }
                });

                if (isChecked) {
                    springAnimation0.setSpring(new SpringForce(360));
                } else {
                    springAnimation0.setSpring(new SpringForce(0));
                }
                //作一些设置，要不然肉眼看不出来生效
                springAnimation0.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES);

                break;
            case R.id.cb_3://淡入淡出
                springAnimation0 = new SpringAnimation(imageview, new FloatPropertyCompat<LinearLayout>("alpha") {
                    @Override
                    public float getValue(LinearLayout object) {
                        return object.getAlpha();
                    }

                    @Override
                    public void setValue(LinearLayout object, float value) {
                        object.setAlpha(value);
                    }
                });
                if (isChecked) {
                    springAnimation0.setSpring(new SpringForce(0.0f));
                } else {
                    springAnimation0.setSpring(new SpringForce(1.0f));
                }

                //作一些设置，要不然肉眼看不出来生效
                springAnimation0.setMinimumVisibleChange(DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA);
                break;
        }


        springAnimation0.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimation0.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

        if (springAnimation1 != null) {
            springAnimation1.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
            springAnimation1.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        }

        springAnimation0.start();
        if (springAnimation1 != null) {
            springAnimation1.start();
        }
    }
}
