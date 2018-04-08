package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/4/9.
 */

public class AlipayFragment extends ViewFragment{
    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_alipay;
    }

    @Override
    protected void initdata() {

    }

    public static AlipayFragment newInstance() {

        Bundle args = new Bundle();

        AlipayFragment fragment = new AlipayFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
