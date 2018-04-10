package com.nextoneday.customview.animation;

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

    private void animatorSet_code() {

    }

    private void animatorSet_xml() {


    }

    @Override
    public void onClick(View v) {
        animatorSet_code();
        animatorSet_xml();
    }
}
