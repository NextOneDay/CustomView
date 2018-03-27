package com.nextoneday.customview.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextoneday.customview.R;

import java.util.ArrayList;

/**
 * Created by nextonedaygg on 2018/3/27.
 */

public class BannerFragment extends ViewFragment {

    private ViewPager mViewPager;
    private TextView mTvTitle;
    private LinearLayout mLlPoint;

    @Override
    protected void initView(View view) {

        mViewPager = view.findViewById(R.id.viewpager);
        mTvTitle = view.findViewById(R.id.tv_title);
        mLlPoint = view.findViewById(R.id.ll_point);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initdata() {

        ArrayList<>

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

        });
    }

    public static Fragment newInstance() {
        return  new BannerFragment();
    }
}
