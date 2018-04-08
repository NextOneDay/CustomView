package com.nextoneday.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ViewAnimator;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/26.
 */

public class TestActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        View content = findViewById(R.id.tv_content);
//        ViewAnimationUtils.createCircularReveal();
//        ViewAnimator;
    }
}
