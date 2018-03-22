package com.nextoneday.customview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nextoneday.customview.bean.PieData;
import com.nextoneday.customview.view.PieView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private PieView mPieview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPieview = findViewById(R.id.pieview);

        initData();


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
