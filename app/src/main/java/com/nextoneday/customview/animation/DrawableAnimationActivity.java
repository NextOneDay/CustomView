package com.nextoneday.customview.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class DrawableAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawable);
        //帧动画
        mView = findViewById(R.id.view);
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    public void startanimation(){
        AnimationDrawable animat = (AnimationDrawable) mView.getBackground();
        animat.start();

    }

    @Override
    public void onClick(View v) {
        startanimation();
    }
}
