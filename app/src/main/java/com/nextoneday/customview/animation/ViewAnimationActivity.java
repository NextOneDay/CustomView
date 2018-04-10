package com.nextoneday.customview.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ViewAnimationActivity  extends AppCompatActivity implements View.OnClickListener {

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
    private void set_code(){


    }

    private void rotate() {

    }

    private void scale() {

    }

    private void translate() {

    }

    private void alpha_xml() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        mView.startAnimation(animation);

    }

    private void alpha_code(){

        AlphaAnimation animation= new AlphaAnimation(1,0);
        animation.setDuration(2000);
        mView.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
       set_xml();
    }
}
