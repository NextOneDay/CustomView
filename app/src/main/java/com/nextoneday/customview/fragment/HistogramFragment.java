package com.nextoneday.customview.fragment;

import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/3/23.
 */

public class HistogramFragment extends ViewFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_histogram;
    }

    @Override
    protected void initdata() {

    }

    public static ViewFragment newinstance() {
        return new HistogramFragment();
    }
}
