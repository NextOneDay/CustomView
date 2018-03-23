package com.nextoneday.customview.fragment;

import android.view.View;

import com.nextoneday.customview.R;
import com.nextoneday.customview.bean.PieData;
import com.nextoneday.customview.view.PieView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/23.
 */

public class PieFragment extends ViewFragment {



    @Override
    protected void initView(View view) {
        mPieView = view.findViewById(R.id.pieview);

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_pie;
    }



    private PieView mPieView;

    protected void initdata() {
        ArrayList<PieData> arrayList = new ArrayList<>();
        int sum = 0;
        for (int x = 0; x < 6; x++) {
            double value = Math.random() * 100;
            sum += value;
            PieData pie = new PieData("i+1", value);
            arrayList.add(pie);

        }

        mPieView.setViewData(arrayList, sum);
    }


    public static PieFragment newinstance() {
        return new PieFragment();
    }
}
