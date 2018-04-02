package com.nextoneday.customview.fragment;

import android.view.View;
import android.widget.Button;

import com.nextoneday.customview.R;
import com.nextoneday.customview.bean.PieData;
import com.nextoneday.customview.view.PieChart;
import com.nextoneday.customview.view.PieView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/23.
 */

public class PieFragment extends ViewFragment implements View.OnClickListener {


    private PieChart mPieChart;
    private ArrayList<PieData> mArrayList;

    @Override
    protected void initView(View view) {
        mPieView = view.findViewById(R.id.pieview);
        mPieChart = view.findViewById(R.id.piechart);
        Button mStart = view.findViewById(R.id.start);
        mStart.setOnClickListener(this);

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_pie;
    }



    private PieView mPieView;

    protected void initdata() {
        mArrayList = new ArrayList<>();
        int sum = 0;
        for (int x = 0; x < 6; x++) {
            double value = Math.random() * 100;
            sum += value;
            PieData pie = new PieData("i+1", value);
            mArrayList.add(pie);

        }

        mPieView.setViewData(mArrayList, sum);
    }


    public static PieFragment newinstance() {
        return new PieFragment();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                //设置饼图
                mPieChart.setPie(mArrayList);

                //设置饼图中心文字
                mPieChart.setCenterText("PieChartTest");

                //画饼图
                mPieChart.startDrawPie();
                mPieView.startAnimation();
                break;

        }



    }
}
