package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27.
 */

public class EyeView extends View {

    private int mWidth;
    private int mHeight;

    private int size = 30; // 边缘留白宽度
    private int radius;  // 大圆半径

    private Paint mPaint;
    private Paint mBackgroup;

    private int minEye;  // 中圆半径
    private int mEyeRadius; // 眼仁半径
    private int mPoint = 20; // 勾玉的半径
    private RectF mPointRect;
    private Paint mPointPaint; //勾玉
    private Path mPath;

    public EyeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);

        mBackgroup = new Paint();
        mBackgroup.setColor(Color.RED);
        mBackgroup.setStyle(Paint.Style.FILL);
        mBackgroup.setAntiAlias(true);

        mPointPaint = new Paint();
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        radius = mWidth / 2 - size;
        mEyeRadius = radius / 4;
        minEye = (int) (radius * 0.7);

        mPointRect = new RectF(-mPoint, minEye - mPoint, mPoint, minEye + mPoint);

        CornerPathEffect effect = new CornerPathEffect(10);
        mPointPaint.setPathEffect(effect);
        mPath = new Path();
        mPath.addArc(mPointRect, -240, 300);
        mPath.lineTo(-mPoint / 1.3f, minEye + (mPoint * 1.5f));
    }

    float degrees = 10;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        /*
                1. 先画一个圆
                2. 再画线框
                3.画一个中间框
                4.使用贝塞尔画勾玉？ 或者使用图像叠加？
                        1)使用path 先画个半圆，再画个点，连起来，
                        2)再用 CornerPathEffect 让直线角变得平滑

                5。使用旋转动画进行插值器，进行加速旋转
                6.在选转过程中将勾玉进行连线绘制在一起，
                7.勾玉连在一起后，进行渐变放大，瞬间缩小显示

         */
        canvas.rotate(degrees);
        drawCircel(canvas);

        drawMagatama(canvas);

        postInvalidateDelayed(50);

        degrees += 10;
    }


    private void drawMagatama(Canvas canvas) {
        canvas.save();

        for (int i = 1; i <= 3; i++) {
            //画勾玉

//            canvas.drawCircle(0,minEye,mPoint,mPointPaint);

            mPath.close();
            canvas.drawPath(mPath, mPointPaint);
            canvas.rotate(120);
        }
        canvas.restore();
    }

    private void drawCircel(Canvas canvas) {

//        1. 先画一个圆
//        2. 再画线框
//        3.画一个中间框

        //画个框
        canvas.drawCircle(0, 0, radius + 2, mPaint);

        //画红色背景
        canvas.drawCircle(0, 0, radius, mBackgroup);
        //画中间圆
        canvas.drawCircle(0, 0, minEye, mPaint);

        // 画眼仁
//        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, mEyeRadius, mPointPaint);
    }
}
