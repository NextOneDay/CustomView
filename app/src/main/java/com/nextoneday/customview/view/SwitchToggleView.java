package com.nextoneday.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by nextonedaygg on 2018/3/24.
 *
 * 一个简陋的仿选择开关
 */

public class SwitchToggleView extends View implements View.OnClickListener {


    private static final String TAG = "SwitchToggleView";
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private RectF mRect;
    private ValueAnimator valueAnimator;

    public SwitchToggleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.d(TAG, mWidth + "::" + mHeight);
        initData();
    }


    int size = 5;// 所绘制的矩形框比布局中的小一点

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        setOnClickListener(this);
    }

    private void initData() {
        mRect = new RectF(0 + size, 0 + size, mWidth - size, mHeight - size);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //应该使用状态来区分，这里为了简单就直接写ifelse了
        if (toggle) {
            //先绘制白色的椭圆背景
            mPaint.setColor(Color.WHITE); //背景底色
            mPaint.setStyle(Paint.Style.FILL);
            drawRoundRect(canvas, mRect, mHeight, mPaint);

            // 绘制一个灰色椭圆边框
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0XffDDDDDD);
            drawRoundRect(canvas, mRect, mHeight / 2, mPaint);


            // 绘制一个小圆点，关闭界面的
            drawCircle(canvas, mWidth * 0.75f, mHeight / 2, 10, mPaint);

//        绘制开启界面的背景色，绿色


//        绘制一个灰色的遮挡

            //绘制开启界面的白色小竖线

            //绘制一个圆形的开关

            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            drawCircle(canvas, mWidth / 4, mHeight / 2, (mHeight / 2) - size, mPaint);
            mPaint.setColor(0XffDDDDDD);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
            drawCircle(canvas, mWidth / 4, mHeight / 2, (mHeight / 2) - 3, mPaint);
        } else {
            //先绘制白色的椭圆背景
            mPaint.setColor(Color.GREEN); //背景底色
            mPaint.setStyle(Paint.Style.FILL);
            drawRoundRect(canvas, mRect, mHeight, mPaint);

//            // 绘制一个灰色椭圆边框
//            mPaint.setStyle(Paint.Style.STROKE);
//            mPaint.setColor(0XffDDDDDD);
//            drawRoundRect(canvas, mRect, mHeight / 2, mPaint);

//
//            // 绘制一个小圆点，关闭界面的
//            drawCircle(canvas, mWidth * 0.75f, mHeight / 2, 10, mPaint);

//        绘制开启界面的背景色，绿色


//        绘制一个灰色的遮挡

            //绘制开启界面的白色小竖线

            mPaint.setStrokeWidth(4);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(mWidth*0.25f,mHeight*0.25f,mWidth*0.25f,mHeight*0.75f,mPaint);

            //绘制一个圆形的开关

            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            drawCircle(canvas, mWidth *0.75f, mHeight / 2, (mHeight / 2) - size, mPaint);
            mPaint.setColor(0XffDDDDDD);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
            drawCircle(canvas, mWidth *0.75f, mHeight / 2, (mHeight / 2) - 3, mPaint);
        }
    }


    /**
     * 画个圆
     *
     * @param canvas
     * @param cx
     * @param cy
     * @param radius
     * @param paint
     */
    private void drawCircle(Canvas canvas, float cx, float cy, float radius, Paint paint) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    /**
     * 画圆角矩形
     *
     * @param canvas
     * @param rect
     * @param paint
     */
    private void drawRoundRect(Canvas canvas, RectF rect, float radius, Paint paint) {
        canvas.drawRoundRect(rect, radius, radius, paint);
    }

    boolean toggle = true;

    @Override
    public void onClick(View v) {

        this.toggle = !toggle;
        postInvalidate();
    }
}
