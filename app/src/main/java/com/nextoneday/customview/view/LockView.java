package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nextonedaygg on 2018/3/25.
 * 滑动解锁
 * 1.绘制背景条
 * 2.绘制滑块
 * 3.
 */

public class LockView extends View {

    private RectF mRect;
    private RectF mBackgroupRect;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private float mDownX;
    private float mDownY;

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 需要修改测量，因为当设置wrapcontent 的时候，范围大小不可控
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0x55888888);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initData();
    }

    int size = 5;
    int blockWidth=mWidth/4;
    private void initData() {

        mBackgroupRect = new RectF(size, size, mWidth - size, mHeight - size);

        mRect = new RectF(0, 0, mWidth/4, mHeight);
    }

    /**
     * 使用shape 来作为背景
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(mBackgroupRect, mHeight / 2, mHeight / 2, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(mRect, mHeight / 2, mHeight / 2, mPaint);
        mPaint.setShadowLayer(mHeight / 2, 10, 10, Color.GRAY);

        mPaint.setColor(Color.GREEN);
        canvas.drawLine(mWidth / 8, mHeight * 0.25f, mWidth / 8, mHeight * 0.75f, mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();

                //偏移量为左正右负， 当前位置-移动的位置
               int  scrolloffset= (int) -(Math.abs(mDownX- blockWidth/2));
               scrollTo(scrolloffset,0);

                break;
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:

                break;
        }
        return true;
    }
}
