package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/3/30.
 */

public class TaijiFragment extends ViewFragment {
    @Override
    protected void initView(View view) {

    }

    @Override
    public  int getlayoutId() {
        return R.layout.fragment_taiji;
    }

    @Override
    protected void initdata() {

    }

    public static TaijiFragment newInstance() {

        Bundle args = new Bundle();

        TaijiFragment fragment = new TaijiFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
