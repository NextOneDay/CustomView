package com.nextoneday.customview;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nextoneday.customview.bean.PieData;
import com.nextoneday.customview.view.PieView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private PieView mPieview;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        TabLayout tablayout =findViewById(R.id.tablayout);

    }

    private void  initDatas(){

        ArrayList<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });



    }
    private void initData() {

        ArrayList<PieData> arrayList= new ArrayList<>();
        int sum=0;
        for (int x = 0; x < 6; x++) {
            double value = Math.random() * 100;
            sum+=value;
            PieData pie = new PieData("i+1",value);
            arrayList.add(pie);

        }

        mPieview.setViewData(arrayList,sum);

    }
}
