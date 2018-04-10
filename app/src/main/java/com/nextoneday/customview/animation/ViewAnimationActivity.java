package com.nextoneday.customview.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private View mView;
    private Button mStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mView = findViewById(R.id.view);
        mStart = findViewById(R.id.start);
        mStart.setOnClickListener(this);
    }

    private void set_xml() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_animator);

        mView.startAnimation(animation);
    }

    private void set_code() {

        AnimationSet set = new AnimationSet(false);

        RotateAnimation rotate = new RotateAnimation(120, 360);
        rotate.setInterpolator(new LinearInterpolator());

        ScaleAnimation scale = new ScaleAnimation(1,1,1,0.5f);
        scale.setDuration(1500);

        set.addAnimation(scale);
        set.addAnimation(rotate);
        set.setDuration(3000);
        mView.startAnimation(set);

    }



    private void scale_code() {

        ScaleAnimation scale = new ScaleAnimation(1.0f,1.5f,1.0f,1.5f);
        scale.setDuration(2000);
        scale.setFillAfter(true);
        mView.startAnimation(scale);
    }

    private void scale_xml(){

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        mView.startAnimation(animation);


    }
    private void translate_code() {

        TranslateAnimation translate = new TranslateAnimation(0,400,0,400);
        translate.setFillAfter(true);
        translate.setDuration(3000);
        mView.startAnimation(translate);
    }
    private void translate_xml(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
        mView.startAnimation(animation);
    }

    private void alpha_xml() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        mView.startAnimation(animation);

    }

    private void alpha_code() {

        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(2000);
        animation.setRepeatCount(2);
        animation.setFillBefore(true);
        mView.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        translate_xml();
    }
}
