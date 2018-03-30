package com.nextoneday.customview.view;

import android.content.Context;
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

    public int colors[] = {Color.RED, Color.BLACK, Color.GRAY, Color.CYAN, Color.GREEN, Color.BLUE};

    private Paint mPaint;
    private int mWidth; //宽
    private int mHight; // 高
    private float startAngle = 0;
    private ArrayList<PieData> mData; //数据集
    private Paint mLinePaint;

    public PieView(Context context) {
        super(context);
    }


    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData.size() < 0) {
            Log.d("PieView", "数据为null");
            return;
        }
        //把画布移动到中心
        canvas.translate(mWidth / 2, mHight / 2);
        float r = (float) (Math.min(mWidth, mHight) / 2 * 0.8);  // 饼状图半径

        RectF rect = new RectF(-r, -r, r, r);

        float currentAngle = startAngle;
        int lineLength = 30;
        for (int x = 0; x < mData.size(); x++) {
            PieData pieData = mData.get(x);
            mPaint.setColor(pieData.color);
            canvas.drawArc(rect, currentAngle, pieData.angle, true, mPaint);

            // 画线
            double angle = Math.toRadians(currentAngle + pieData.angle / 2);
            float startX = (float) (r * Math.cos(angle));
            float startY = (float) (r * Math.sin(angle));
            Log.d(TAG, angle + ":" + startX + ":" + startY);
            float stopX = (float) ((r + lineLength) * Math.cos(angle));
            float stopY = (float) ((r + lineLength) * Math.sin(angle));
            canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);

            currentAngle += pieData.angle;
            // 画文字
            String text = String.format("%.1f", pieData.percentage) + "%";
            Log.d(TAG, currentAngle+"::");
            mLinePaint.getTextBounds(text,0,text.length(),textRect);
            int textheight = textRect.height();
            float textwidth = mLinePaint.measureText(text);

            if (currentAngle >90 && currentAngle<=270){
                float point =stopX-textwidth;
                canvas.drawLine(stopX,stopY,point,stopY,mLinePaint);
                canvas.drawText(text,point-textwidth,stopY,mLinePaint);

                canvas.drawText(text,stopX-textwidth,stopY+textheight,mLinePaint);
            }else {
                canvas.drawText(text, stopX, stopY+textheight, mLinePaint);
            }
        }

    }

    /**
     * 设置数据
     *
     * @param arrayList
     * @param sum
     */
    public void setViewData(ArrayList<PieData> arrayList, int sum) {

        this.mData = arrayList;
        //根据传入的值，给其他属性进行获取数据
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
}
