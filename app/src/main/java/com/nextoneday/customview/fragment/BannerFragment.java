package com.nextoneday.customview.fragment;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextoneday.customview.DeleteAdapter;
import com.nextoneday.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by nextonedaygg on 2018/3/27.
 */

public class BannerFragment extends ViewFragment implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private TextView mTvTitle;
    private LinearLayout mLlPoint;
    private ArrayList<View> mData;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem;
    private MyHandler mMyHandler;
    private RecyclerView mRecycler;
    private List<String> mDelete;

    @Override
    protected void initView(View view) {

        mViewPager = view.findViewById(R.id.viewpager);
        mTvTitle = view.findViewById(R.id.tv_title);
        mLlPoint = view.findViewById(R.id.ll_point);
        mMyHandler = new MyHandler();
        mRecycler = view.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_banner;
    }

    int[] imageids = {R.drawable.ic_cover_1, R.drawable.ic_cover_2, R.drawable.ic_cover_3, R.drawable.ic_cover_4, R.drawable.ic_cover_5};
    String[] title = {"xx1", "xx2", "xx3", "xx4", "xx5"};

    @Override
    protected void initdata() {

        viewdata();
        mTvTitle.setText(title[0]);

        mViewPager.addOnPageChangeListener(this);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mData == null ? 0 : mData.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View image = mData.get(position);
                container.addView(image);
                return image;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mData.get(position));
            }

        });
        initRecyclerData();
    }

    private void initRecyclerData() {
        mDelete = new ArrayList();
        for (int i = 0; i < 10; i++) {
            mDelete.add("这是要显示的内容");
        }
        DeleteAdapter deleteAdapter = new DeleteAdapter(mDelete);
        mRecycler.setAdapter(deleteAdapter);
    }


    private void viewdata() {
        mData = new ArrayList<>();
        for (int i = 0; i < imageids.length; i++) {

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageids[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mData.add(imageView);

            View point = new View(getActivity());
            int size = getResources().getDimensionPixelOffset(R.dimen.indicator_size);
            int margin = getResources().getDimensionPixelOffset(R.dimen.indicator_margin);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.topMargin = margin;
            if (i != 4) {
                params.rightMargin = margin;
            }
            if (i == 0) {
                point.setBackgroundResource(R.drawable.shape_point_select);
            } else {
                point.setBackgroundResource(R.drawable.shape_point);

            }
            point.setLayoutParams(params);
            mLlPoint.addView(point);
        }
    }

    //自动轮播
    @Override
    public void onStart() {
        super.onStart();
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(
//                new ViewPageTask(),
//                2,
//                2,
//                TimeUnit.SECONDS);
    }

    /**
     * 图片轮播任务
     *
     * @author liuyazhuang
     */
    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageids.length;
            mMyHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */

    private class MyHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(currentItem);
        }
    }

    ;

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
//        if (scheduledExecutorService != null) {
//            scheduledExecutorService.shutdown();
//            scheduledExecutorService = null;
//        }
    }

    public static Fragment newInstance() {
        return new BannerFragment();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    int oldposition = 0;

    @Override
    public void onPageSelected(int position) {

        mTvTitle.setText(title[position]);
        mLlPoint.getChildAt(position).setBackgroundResource(R.drawable.shape_point_select);
        mLlPoint.getChildAt(oldposition).setBackgroundResource(R.drawable.shape_point);
        oldposition = position;
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
