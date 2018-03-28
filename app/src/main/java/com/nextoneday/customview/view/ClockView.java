package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by nextonedaygg on 2018/3/28.
 */

public class ClockView extends View {

    private static final String TAG = "ClockView";
    private Paint mPaint;
    private int mCx;
    private int mCy;// view的圆心位置、画布的中心
    private int size = 20; //边缘留空白
    private int mRadius; //半径
    private Paint mTextPaint;
    private float textsize = 25;
    int longLine = 20; //长线
    int shortLine = 10; //短线
    int marginWidth = 20; //刻度与边缘留白
    int numberPadding = 15; //字间隔
    private RectF mMin;
    private RectF mSen;
    private RectF mHour;
    private Paint mRountPaint;


    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpaint();
        initRect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCx = w / 2;
        mCy = h / 2;
        mRadius = w / 2 - size;
    }

    private void initpaint() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);


        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(textsize);

        mRountPaint= new Paint(Paint.ANTI_ALIAS_FLAG);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        0.将画布移动到中心
        canvas.translate(mCx, mCy);
        // 1.画个圆
        canvas.drawCircle(0, 0, mRadius, mPaint);

        //2.画刻度
        drawTimerLine(canvas);

        //3.画数字
        drawNumber(canvas);

        //4.画指针
        drawRoundRect(canvas);
        postInvalidateDelayed(1000);

    }

    int minWidth = 10; //分的宽带
    int minLong = 160; //分的长度
    int senWidth = 8; //秒的宽带
    int senLong = 180; //秒的长度
    int hourWidth = 12; //分的宽带
    int hourLong = 100; //分的长度
    int guding=20; // 这个是指针尾部的固定长

    //圆角矩形
    private void initRect() {
        int minLeft = -minWidth/2;
        int minTop = -minLong;
        int minRight = minWidth/2;
        int minBotton = guding;
        Log.d(TAG, "min :" + minLeft + "::" + minTop + ":::" + minRight + ":::" + minBotton);
        mMin = new RectF(minLeft, minTop, minRight, minBotton);


        int senLeft = -senWidth/2;
        int senTop = -senLong;
        int senRight = senWidth/2;
        int senBotton = guding;
        Log.d(TAG, "min :" + senLeft + "::" + senTop + ":::" + senRight + ":::" + senBotton);
        mSen = new RectF(senLeft,senTop,senRight,senBotton);


        int hourLeft = -hourWidth/2;
        int hourTop = -hourLong;
        int hourRight = hourWidth/2;
        int hourBotton = guding;
        Log.d(TAG, "min :" + hourLeft + "::" + hourTop + ":::" + hourRight + ":::" + hourBotton);
        mHour = new RectF(hourLeft,hourTop,hourRight,hourBotton);
    }

    //画圆角矩形
    int rountRect= 6; //圆角矩形的圆心
    private void drawRoundRect(Canvas canvas) {
        Calendar calendar= Calendar.getInstance();

        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int hour = calendar.get(Calendar.HOUR);
        int angleHour = (hour % 12) * 360 / 12; //时针转过的角度
        int angleMinute = minute * 360 / 60; //分针转过的角度
        int angleSecond = second * 360 / 60; //秒针转过的角度
        Log.d(TAG,"time:"+hour+":"+minute+":"+second);
        Log.d(TAG,"angle:"+angleHour+":"+angleMinute+":"+angleSecond);


        mRountPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(angleMinute);
        canvas.drawRoundRect(mMin, rountRect, rountRect, mRountPaint);
        canvas.restore();




        canvas.save();
        canvas.rotate(angleHour);
        canvas.drawRoundRect(mHour, rountRect, rountRect, mRountPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(angleSecond);
        mRountPaint.setColor(Color.RED);
        canvas.drawRoundRect(mSen, rountRect, rountRect, mRountPaint);
        canvas.restore();

        canvas.drawCircle(0, 0, 10, mRountPaint);
    }


    private void drawNumber(Canvas canvas) {
        float x = 0;
        float y = -mRadius + size + longLine + marginWidth + numberPadding;
        for (int i = 1; i <= 12; i++) {
            canvas.rotate(30, 0, 0);
            float px = 0;
            float py = -mRadius + size + marginWidth + longLine + (numberPadding / 2);
            canvas.rotate(-i * 30, px, py);
            canvas.drawText(i + "", x, y, mTextPaint);
            canvas.rotate(i * 30, px, py);
        }

    }


    private void drawTimerLine(Canvas canvas) {
        mTextPaint.setColor(Color.BLACK);
        int startX = 0;
        int startY = -mRadius + marginWidth;
        int stopX = 0;
        int stopY = 0;
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth(2);
                stopY = startY + longLine;
            } else {
                mPaint.setStrokeWidth(1);
                stopY = startY + shortLine;
            }
            canvas.drawLine(startX, startY, stopX, stopY, mTextPaint);
            canvas.rotate(6);
        }
    }
}
