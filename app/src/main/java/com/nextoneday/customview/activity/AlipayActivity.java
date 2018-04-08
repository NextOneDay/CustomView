package com.nextoneday.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nextoneday.customview.R;
import com.nextoneday.customview.adapter.DeleteAdapter;

import java.util.ArrayList;

/**
 * Created by nextonedaygg on 2018/4/9.
 */

public class AlipayActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);

        mRecyclerView = findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDelete = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDelete.add("这是要显示的内容");
        }
        mRecyclerView.setAdapter(new DeleteAdapter(mDelete));
    }
}
