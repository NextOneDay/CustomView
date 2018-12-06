package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/12/6.
 */

public class LottieFragment extends  ViewFragment {
    @Override
    protected void initView(View view) {


    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_lottie;
    }

    @Override
    protected void initdata() {

    }

    public static LottieFragment newInstance() {

        Bundle args = new Bundle();

        LottieFragment fragment = new LottieFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
