package com.nextoneday.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextoneday.customview.R;
import com.nextoneday.customview.adapter.DeleteAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nextonedaygg on 2018/4/9.
 */

public class AlipayActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.toolbar_head1)
    View toolbar1;
    @BindView(R.id.toolbar_head2)
    View toolbar2;
    @BindView(R.id.img_zhangdan)
    ImageView mImgZhangdan;
    @BindView(R.id.img_zhangdan_txt)
    TextView mImgZhangdanTxt;
    @BindView(R.id.jiahao)
    ImageView mJiahao;
    @BindView(R.id.tongxunlu)
    ImageView mTongxunlu;
    @BindView(R.id.iv_zhangdan)
    ImageView mIvZhangdan;
    @BindView(R.id.iv_saoyisao)
    ImageView mIvSaoyisao;
    @BindView(R.id.appbarlayout)
    AppBarLayout mAppbarlayout;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.iv_camera)
    ImageView mIvCamera;

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDelete = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDelete.add("这是要显示的内容");
        }
        mRecyclerView.setAdapter(new DeleteAdapter(mDelete));

        mAppbarlayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //偏移量
        if (verticalOffset == 0) {
            //完全展开
            toolbar1.setVisibility(View.VISIBLE);
            toolbar2.setVisibility(View.GONE);
            setToolbar1Alpha(255);
        } else if (Math.abs(verticalOffset) == mAppbarlayout.getTotalScrollRange()) {
//            折叠
            toolbar2.setVisibility(View.VISIBLE);
            toolbar1.setVisibility(View.GONE);
            setToolbar2Alpha(255);
        }else {
            //折叠和展开之间的状态
            if(toolbar1.getVisibility()==View.VISIBLE){
                int alpha = 255 - 155 - Math.abs(verticalOffset);
                setToolbar1Alpha(alpha);
            }else if(toolbar2.getVisibility()==View.VISIBLE){
                int alpha = (int) (255*(Math.abs(verticalOffset)/100f));
                setToolbar2Alpha(alpha);
            }
        }
    }

    private void setToolbar2Alpha(int i) {
        mIvZhangdan.setAlpha(i);
        mIvSaoyisao.setAlpha(i);
        mIvSearch.setAlpha(i);
        mIvCamera.setAlpha(i);
    }

    private void setToolbar1Alpha(int i) {

        mImgZhangdan.setAlpha(i);
        mImgZhangdanTxt.setAlpha(i);
        mTongxunlu.setAlpha(i);
        mJiahao.setAlpha(i);
    }

}
