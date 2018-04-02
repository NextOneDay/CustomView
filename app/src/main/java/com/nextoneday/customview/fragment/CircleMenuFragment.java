package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.view.View;

import com.nextoneday.customview.R;
import com.nextoneday.customview.view.CircleMenuView;

/**
 * Created by Administrator on 2018/4/2.
 */

public class CircleMenuFragment extends ViewFragment {

    private CircleMenuView mCirclemenu;

    private int[] images = {R.drawable.home_mbank_1_normal, R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,R.drawable.home_mbank_6_normal};
    private String[] titles = {"标题一","标题二","标题三","标题四","标题五","标题六"};

    @Override
    protected void initView(View view) {

        mCirclemenu = view.findViewById(R.id.circlemenu);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_circlemenu;
    }

    @Override
    protected void initdata() {

        mCirclemenu.setData(images,titles);

    }

    public static CircleMenuFragment newInstance() {

        Bundle args = new Bundle();
        CircleMenuFragment fragment = new CircleMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
