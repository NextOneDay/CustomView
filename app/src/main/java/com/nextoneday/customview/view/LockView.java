package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by nextonedaygg on 2018/3/25.
 * 滑动解锁
 * 1.绘制背景条
 * 2.绘制滑块
 * 3.
 *
 */

public class LockView extends View {

    private RectF mRect;
    private RectF mBackgroupRect;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 需要修改测量，因为当设置wrapcontent 的时候，范围大小不可控
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
        mWidth =w;
        mHeight =h;
        initData();
    }

    int size =5;
    private void initData() {

        mBackgroupRect = new RectF(size,size,mWidth -size,mHeight-size);
        mRect = new RectF(0,0,mWidth/4,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(mBackgroupRect,mHeight/2,mHeight/2,mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setShadowLayer(mHeight/2,1,1,Color.GRAY);
        canvas.drawRoundRect(mRect,mHeight/2,mHeight/2,mPaint);

    }
}
