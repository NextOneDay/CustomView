package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nextonedaygg on 2018/3/24.
 */

public class CircleLoadingView extends View {
    private static final String TAG = "CircleLoadingView";
    private int mHeight;
    private int mWidth;
    private Paint mPaint;
    private RectF mRect;
    private int progress; //进度
    private Paint mTextPaint;

    public CircleLoadingView(Context context) {
        super(context);
    }

    public CircleLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth= w;
        mHeight =h;
        Log.d(TAG,"mwidth: "+mWidth+": "+"mheight:"+mHeight);
        initData();
    }

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(5);

        mTextPaint =new Paint();

        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(40);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.CYAN);
    }

    int size = 5;
    private void initData() {

        Log.d(TAG,"l , t, r, b :"+ getLeft()+" "+ getTop()+" "+getRight()+" "+getBottom());
//        mRect = new RectF(getLeft()+10,getTop()+10,getRight()-10,getBottom()-10);

        mRect = new RectF(0+size,0+size,mWidth-size,mHeight-size);

    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画个百分百的灰色的圆


        int sweepAngle= (int) ((progress/100f)*360);
        canvas.drawArc(mRect,-90,sweepAngle,false,mPaint);


        canvas.drawText(progress+" %", mWidth/2,
                mHeight/2+size,mTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        Log.d(TAG, x+":::"+y);
        return  super.onTouchEvent(event);
    }

    public void setprogress(int i) {
        this.progress=i;
        postInvalidate();
    }
}
