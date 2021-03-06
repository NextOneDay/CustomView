package com.nextoneday.customview.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nextoneday.customview.R;
import com.nextoneday.customview.animation.DrawableAnimationActivity;
import com.nextoneday.customview.animation.PropertyAnimationActivity;
import com.nextoneday.customview.animation.ViewAnimationActivity;
import com.nextoneday.customview.fragment.BannerFragment;
import com.nextoneday.customview.fragment.Chart2PictureFragment;
import com.nextoneday.customview.fragment.CircleMenuFragment;
import com.nextoneday.customview.fragment.ClockFragment;
import com.nextoneday.customview.fragment.EyesFragment;
import com.nextoneday.customview.fragment.HistogramFragment;
import com.nextoneday.customview.fragment.LoadingFragment;
import com.nextoneday.customview.fragment.LockFragment;
import com.nextoneday.customview.fragment.LottieFragment;
import com.nextoneday.customview.fragment.PieFragment;
import com.nextoneday.customview.fragment.SwitchFragment;
import com.nextoneday.customview.fragment.TaijiFragment;
import com.nextoneday.customview.view.DialogView;
import com.nextoneday.customview.view.PieView;
import com.nextoneday.customview.view.SlidingMenu;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private PieView mPieview;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mAl;
    public String[] titles = {"lottie","饼图", "下拉框", "进度条", "切换开关", "轮播图", "滑动解锁", "时钟", "太极", "写轮眼", "圆形菜单" };

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
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //动画menu点击事件

        TextView drawable = (TextView) findViewById(R.id.drawable_animation);
        TextView view = (TextView) findViewById(R.id.view_animation);
        TextView property = (TextView) findViewById(R.id.property_animation);
        drawable.setOnClickListener(this);
        view.setOnClickListener(this);
        property.setOnClickListener(this);
        TextView alipay = (TextView) findViewById(R.id.alipay);
        alipay.setOnClickListener(this);

    }

    private void initDatas() {

//        showDialog();
        mAl = new ArrayList<>();


        mAl.add(LottieFragment.newInstance());
        mAl.add(PieFragment.newinstance());
        mAl.add(HistogramFragment.newinstance());
        mAl.add(LoadingFragment.newInstance());
        mAl.add(SwitchFragment.newInstance());
        mAl.add(BannerFragment.newInstance());
        mAl.add(LockFragment.newInstance());
        mAl.add(ClockFragment.newInstance());
        mAl.add(TaijiFragment.newInstance());
        mAl.add(EyesFragment.newInstance());
        mAl.add(CircleMenuFragment.newInstance());
//        mAl.add(Chart2PictureFragment.newInstance());



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
        mViewPager.addOnPageChangeListener(this);

//        mViewPager.setCurrentItem(mAl.size()-1);
    }

    //显示一个dialog
    private void showDialog() {

//        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @SuppressLint("WrongConstant")
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                          int dayOfMonth) {
//                        Toast.makeText(MainActivity.this,
//                                year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日", 10).show();
//                    }
//                },
//                2015, 8, 21);
//        //Date和Time只用show()  不用create()
//        datePickerDialog.show();

        DialogView dialogView = new DialogView(this, R.style.update_dialog);

        dialogView.show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //当页面切换到第一页的时候运行menu滑动，其他页面的时候不允许滑动
        if (position == 0) {
            mSlidingMenu.setTouchMode(true);
        } else {
            mSlidingMenu.setTouchMode(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawable_animation:

                Intent drawalbe = new Intent(this, DrawableAnimationActivity.class);
                startActivity(drawalbe);
                break;
            case R.id.view_animation:
                Intent view = new Intent(this, ViewAnimationActivity.class);
                startActivity(view);
                break;
            case R.id.property_animation:
                Intent property = new Intent(this, PropertyAnimationActivity.class);
                startActivity(property);
                break;
            case R.id.alipay:
                Intent intent = new Intent(this, AlipayActivity.class);
                startActivity(intent);

        }
    }
}
