package com.nextoneday.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/3/23.
 */

public class SpinnerView extends RelativeLayout implements View.OnClickListener {

    private ImageView mIvArr;
    private EditText mEditText;

    public SpinnerView(Context context) {
        super(context);
        init();
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化
     */
    private void init() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this);
        mEditText = view.findViewById(R.id.ed_text);
        mIvArr = view.findViewById(R.id.iv_arr);

        initEvent();
    }

    private void initEvent() {
        mIvArr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        showWindow();
    }

    /**
     * 弹出一个窗口
     */
    private void showWindow() {

    }
}
