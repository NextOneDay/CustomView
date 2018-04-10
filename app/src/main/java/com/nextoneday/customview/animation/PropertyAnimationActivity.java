package com.nextoneday.customview.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        mView = findViewById(R.id.view);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(this);


    }

    @SuppressLint("ObjectAnimatorBinding")
    private void animatorSet_code() {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator translation = ObjectAnimator.ofFloat(mView, "translation", 0, 100);
        translation.setDuration(3000);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mView, "rotation", 10, 360);
        rotation.setDuration(3000);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mView, "alpha", 1.0f, 0.5f);

        alpha.setDuration(3000);


        animatorSet.playTogether(translation,rotation,alpha);
        animatorSet.start();
    }

    private void animatorSet_xml() {

        Animator animator = AnimatorInflater.loadAnimator(this,  R.animator.set_animator);
        animator.setTarget(mView);
        animator.setDuration(5000);
        animator.start();


    }

    @Override
    public void onClick(View v) {
        animatorSet_code();
//        animatorSet_xml();
    }
}
