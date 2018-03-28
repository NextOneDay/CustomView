package com.nextoneday.customview.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/28.
 */

public class ClockFragment extends ViewFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_clock;
    }

    @Override
    protected void initdata() {

    }

    public static Fragment newInstance() {

        return new ClockFragment();
    }
}
