package com.nextoneday.customview.fragment;

import android.view.View;
import android.widget.Button;

import com.nextoneday.customview.R;
import com.nextoneday.customview.view.CircleLoadingView;
import com.nextoneday.customview.view.LoadingView;

/**
 * Created by nextonedaygg on 2018/3/24.
 */

public class LoadingFragment extends ViewFragment implements View.OnClickListener {


    private LoadingView mFlowerLoading;
    private CircleLoadingView mCircleLoadingVeiw;

    public static LoadingFragment newInstance() {
        return  new LoadingFragment();
    }

    @Override
    protected void initView(View view) {

        mFlowerLoading = view.findViewById(R.id.flower);
        mCircleLoadingVeiw = view.findViewById(R.id.circle);
        Button start = view.findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_loading;
    }

    @Override
    protected void initdata() {

    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=100; i++) {

                    try {
                        Thread.sleep(100);
                        mCircleLoadingVeiw.setprogress(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}
