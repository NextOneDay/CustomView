package com.nextoneday.customview;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nextoneday.customview.fragment.HistogramFragment;
import com.nextoneday.customview.fragment.LoadingFragment;
import com.nextoneday.customview.fragment.LockFragment;
import com.nextoneday.customview.fragment.PieFragment;
import com.nextoneday.customview.fragment.SwitchFragment;
import com.nextoneday.customview.view.PieView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private PieView mPieview;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mAl;
    public String[] titles = {"饼图","下拉选择框","进度条","切换开关","滑动解锁"};

    private TabLayout mTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();

    }

    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tablayout);


    }

    private void initDatas() {

        mAl = new ArrayList<>();

        mAl.add(PieFragment.newinstance());
        mAl.add(HistogramFragment.newinstance());
        mAl.add(LoadingFragment.newInstance());
        mAl.add(SwitchFragment.newInstance());
        mAl.add(LockFragment.newInstance());


        mTablayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mAl.get(position);
            }

            @Override
            public int getCount() {
                return mAl.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
              return  titles[position];
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.setCurrentItem(mAl.size()-1);
    }

}
