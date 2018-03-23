package com.nextoneday.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/22.
 */

public abstract class ViewFragment extends Fragment {

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getlayoutId(),null);
        initView(mView);
         return mView;
    }

    protected abstract void initView(View view);

    public abstract int getlayoutId() ;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata();
    }

    protected abstract void initdata();
}
