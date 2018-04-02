package com.nextoneday.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nextoneday.customview.bean.PieData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/22.
 * <p>
 * 一个简单小饼图 , 传入数据，进行绘制饼图，以及数据文字等
 */

public class PieView extends View {

    private static final String TAG = "PieView";
    private static final float DEFAULT_START_ANGLE = 45;

    public int colors[] = {Color.RED, Color.BLACK, Color.GRAY, Color.CYAN, Color.GREEN, Color.BLUE};

    private Paint mPaint;
    private int mWidth; //宽
    private int mHight; // 高
    private float startAngle = 45;
    private ArrayList<PieData> mData; //数据集
    private Paint mLinePaint;
    private int mTouchSize = 20;
    private RectF mRect;
    private float r;
    private RectF mTouchRect; //点击后的矩形
    private ValueAnimator animator; // 点击开始动画
    private float mCurrentAngle;
    private float curtFraction;
    private int sum;
    private float mDownX;
    private float mDownY;
    private int index;

    public PieView(Context context) {
        super(context);
    }


    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAnimator();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHight = h;
        // 饼状图半径
        r = (float) (Math.min(mWidth, mHight) / 2 * 0.6);
        mRect = new RectF(-r, -r, r, r);
        mTouchRect = new RectF(-r - mTouchSize, -r - mTouchSize, r + mTouchSize, r + mTouchSize);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


        //初始化 线的画笔
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(3);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setTextSize(20);
    }

    // 获取text的高度的rect
    Rect textRect = new Rect();

    private void initAnimator() {

        animator = ValueAnimator.ofFloat(0, 1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "startAnimation");
                curtFraction = animation.getAnimatedFraction();
//                startAngle = DEFAULT_START_ANGLE;
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData.size() < 0) {
            Log.d("PieView", "数据为null");
            return;
        }
        //把画布移动到中心
        canvas.translate(mWidth / 2, mHight / 2);

        mCurrentAngle = startAngle;
        int lineLength = 30;
        for (int x = 0; x < mData.size(); x++) {
            PieData pieData = mData.get(x);
            mPaint.setColor(pieData.color);
            pieData.angle = (float) (360 * curtFraction * pieData.value / sum);
            if (x == index) {
                canvas.drawArc(mTouchRect, mCurrentAngle, pieData.angle, true, mPaint);
            } else {
                canvas.drawArc(mRect, mCurrentAngle, pieData.angle, true, mPaint);

            }

            // 画线
            double angle = Math.toRadians(mCurrentAngle + pieData.angle / 2);
            float startX = (float) (r * Math.cos(angle));
            float startY = (float) (r * Math.sin(angle));
            Log.d(TAG, angle + ":" + startX + ":" + startY);
            float stopX = (float) ((r + lineLength) * Math.cos(angle));
            float stopY = (float) ((r + lineLength) * Math.sin(angle));
            canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);

            mCurrentAngle += pieData.angle;
            // 画文字
            String text = String.format("%.1f", pieData.percentage) + "%";
            Log.d(TAG, mCurrentAngle + "::");
            mLinePaint.getTextBounds(text, 0, text.length(), textRect);
            int textheight = textRect.height();
            float textwidth = mLinePaint.measureText(text);

            if (mCurrentAngle > 70 && mCurrentAngle <= 300) {
                float point = stopX - textwidth;
                canvas.drawLine(stopX, stopY, point, stopY, mLinePaint);
                canvas.drawText(text, point - textwidth, stopY, mLinePaint);
            } else {
                canvas.drawLine(stopX, stopY, stopX + textwidth, stopY, mLinePaint);
                canvas.drawText(text, stopX + textwidth, stopY, mLinePaint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            //在down 的时候获取到点所对应位置
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();


                break;

            case MotionEvent.ACTION_UP:
                setDrawingCacheEnabled(true);
                Bitmap bitmap = getDrawingCache();
                if (bitmap == null) {
                    Log.w(TAG, "bitmap == null");
                    break;
                }
                int pixel = bitmap.getPixel((int) mDownX, (int) mDownY);
                setDrawingCacheEnabled(false);

                int i = 0;
                for (PieData pie : mData) {
                    if (pixel == pie.color) {
                        index = i;
                        invalidate();
//                        onTouchPie(i);

                        break;
                    }
                    i++;
                }

                break;
        }

        return true;

    }

    /**
     * touch transfer
     *
     * @param i index for params list
     */
//    private void onTouchPie(int i) {
//        index = i;
//        curtFractionTouch = 1f;
//        curtFractionTouch2 = 0f;
//
////        float angle = getRotationAngle(i);
//
//        ValueAnimator animatorRotation;
//        animatorRotation = ValueAnimator.ofFloat(mStartAngle, mStartAngle + angle);
//        animatorRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mStartAngle = (Float) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//
//        int time = (int) (1000 * Math.abs(angle) / 360);
//
//        animatorRotation.setDuration(time);
//        animatorTouch.setStartDelay(time);
//
//        animatorRotation.start();
//        animatorTouch.start();
//    }


    /**
     * 设置数据
     *
     * @param arrayList
     * @param sum
     */
    public void setViewData(ArrayList<PieData> arrayList, int sum) {

        this.mData = arrayList;
        //根据传入的值，给其他属性进行获取数据
        this.sum = sum;
        for (int x = 0; x < arrayList.size(); x++) {
            PieData pieData = arrayList.get(x);
            pieData.color = colors[x];
            float percent = (float) (pieData.value / sum);
            pieData.percentage = percent * 100;
            pieData.angle = percent * 360;
        }

        //刷新
        invalidate();
    }

    public void setStartAngle(float angle) {
        this.startAngle = angle;
        invalidate();
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public void startAnimation() {
        animator.start();
    }
}
