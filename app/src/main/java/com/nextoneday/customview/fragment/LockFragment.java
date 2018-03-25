package com.nextoneday.customview.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/25.
 */

public class LockFragment extends ViewFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_lock;
    }

    @Override
    protected void initdata() {

    }

    public static Fragment newInstance() {

        return  new LockFragment();
    }
}
