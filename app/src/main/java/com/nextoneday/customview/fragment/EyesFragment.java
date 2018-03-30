package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/30.
 */

public class EyesFragment extends  ViewFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_eyesview;
    }

    @Override
    protected void initdata() {

    }

    public static EyesFragment newInstance() {

        Bundle args = new Bundle();

        EyesFragment fragment = new EyesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
