package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/24.
 */

public class SwitchFragment extends ViewFragment {

    @Override
    protected void initView(View view) {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_switch;
    }

    @Override
    protected void initdata() {

    }

   public static SwitchFragment newInstance() {

        Bundle args = new Bundle();

        SwitchFragment fragment = new SwitchFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
