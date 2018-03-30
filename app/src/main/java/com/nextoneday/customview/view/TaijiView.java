package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/30.
 *
 * 太极视图，通过传入间隔时间和角度，能够进行自动旋转
 */

public class TaijiView extends View {
    private int mWidth;
    private int mHeight;
    private int radius;

    private int size = 50; // 图形与控件边缘留白
    private RectF mRect;
    private Paint mBlackPaint;
    private Paint mWhitePaint;
    private int degress=5; // 旋转角度
    private int time =50;

    public TaijiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化两个黑白的画笔
     */
    private void init() {

        mBlackPaint = new Paint();
        mBlackPaint.setAntiAlias(true);
        mBlackPaint.setColor(Color.BLACK);

        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;

        radius = (w / 2) - size;
        mRect = new RectF(-radius, -radius, radius, radius);
    }

    int current = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(Color.GRAY);

        canvas.rotate(current);
        drawCircle(canvas);
        postInvalidateDelayed(time);

        current += degress;
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawArc(mRect, -90, 180, true, mWhitePaint);
        canvas.drawArc(mRect, 90, 180, true, mBlackPaint);


        //绘制小圆 ，小圆的半径为大圆半径的一半
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, mBlackPaint);
        canvas.drawCircle(0, smallRadius, smallRadius, mWhitePaint);


        //绘制鱼眼 ,与小圆是同心圆
        int fisheyeRadius = smallRadius / 4;
        canvas.drawCircle(0, smallRadius, fisheyeRadius, mBlackPaint);
        canvas.drawCircle(0, -smallRadius, fisheyeRadius, mWhitePaint);
    }


    //    提供外接访问接口，设置旋转角度
    public void setRotate(int degress, int time) {
        this.degress = degress;
        this.time = time;
        invalidate();
    }


}
