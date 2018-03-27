package com.nextoneday.customview;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.nextoneday.customview.fragment.HistogramFragment;
import com.nextoneday.customview.fragment.LoadingFragment;
import com.nextoneday.customview.fragment.LockFragment;
import com.nextoneday.customview.fragment.PieFragment;
import com.nextoneday.customview.fragment.SwitchFragment;
import com.nextoneday.customview.view.PieView;
import com.nextoneday.customview.view.SlidingMenu;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private PieView mPieview;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mAl;
    public String[] titles = {"饼图", "下拉框", "进度条", "切换开关", "滑动解锁"};

    private TabLayout mTablayout;
    private SlidingMenu mSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                mSlidingMenu.setToggle();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void initView() {
        mSlidingMenu = findViewById(R.id.slidingMenu);
        mViewPager = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tablayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void initDatas() {

        showDialog();
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
                return titles[position];
            }
        });

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mViewPager.setCurrentItem(mAl.size()-1);
    }

    //显示一个dialog
    private void showDialog() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @SuppressLint("WrongConstant")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Toast.makeText(MainActivity.this,
                                year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日", 10).show();
                    }
                },
                2015, 8, 21);
        //Date和Time只用show()  不用create()
        datePickerDialog.show();

    }


}
