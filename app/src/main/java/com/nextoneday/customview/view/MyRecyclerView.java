package com.nextoneday.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyRecyclerView extends RecyclerView {
    private float mDownX;
    private float mDownY;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();
                //算一下左右滑动 ,负向右，正向左
                float dx = Math.abs(mDownX - moveX);
                float dy = Math.abs(mDownY - moveY);
                Log.d("huadong",dx+":::");

                if (dx>dy && dx <300) {
                    Log.d("huadong","请求");
                    //水平滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
        }
        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = ev.getX();
//                mDownY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = ev.getX();
//                float moveY = ev.getY();
//                //算一下左右滑动 ,负向右，正向左
//                float dx = mDownX - moveX;
//                float dy = mDownY - moveY;
//                if (Math.abs(dx) > Math.abs(dy)) {
//                    //水平滑动
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//        }
        return super.onTouchEvent(ev);
    }
}