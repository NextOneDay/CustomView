package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.nextoneday.customview.R;

/**
 * Created by nextonedaygg on 2018/3/25.
 * 滑动解锁
 * 1.绘制背景条
 * 2.绘制滑块
 * 3.
 */

public class LockView extends View {

    private static final String TAG = "LockView";
    private RectF mRect;
    private RectF mBackgroupRect;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private float mDownX;
    private float mDownY;
    private Bitmap mBitmap;
    private int max;

    private Scroller mScroller;
    private OnUnlockListener onUnlockListener;

    public LockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);
        mScroller= new Scroller(context);
    }

    /**
     * 需要修改测量，因为当设置wrapcontent 的时候，范围大小不可控
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size,mBitmap.getHeight());

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

    private void initData() {


        mRect = new RectF(0, 0, mWidth / 4, mHeight);
        max = getWidth() - mBitmap.getWidth();
    }

    /**
     * 使用shape 来作为背景
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

//        mPaint.setColor(Color.WHITE);
//        canvas.drawRoundRect(mRect, mHeight / 2, mHeight / 2, mPaint);
//        mPaint.setShadowLayer(mHeight / 2, 10, 10, Color.GRAY);

//        mPaint.setColor(Color.GREEN);
//        canvas.drawLine(mWidth / 8, mHeight * 0.25f, mWidth / 8, mHeight * 0.75f, mPaint);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                Log.d(TAG,mDownX+":down");
                if (mDownX > mBitmap.getWidth()) {
                    return false;
                }

                //偏移量为左正右负， 当前位置-移动的位置
                int scrolloffset1 =-(int)(mDownX - mBitmap.getWidth() / 2);
                if (scrolloffset1 > 0) {
                    scrolloffset1 = 0;
                }

                scrollTo(scrolloffset1, 0);

                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                Log.d(TAG,moveX+"::move");
                int scrolloffset2 = -(int) (moveX - mBitmap.getWidth() / 2);

                if (scrolloffset2 < -max) {
                    scrolloffset2 =- max;
                } else if (scrolloffset2 > 0) {
                    scrolloffset2 = 0;
                }

                scrollTo(scrolloffset2, 0);

                break;
            case MotionEvent.ACTION_UP:
              Log.d(TAG, "event.getX():" + event.getX());
                int upX = (int) (event.getX() - mBitmap.getWidth() / 2);
                if(upX<max){
                    int startX= getScrollX() ;
                    int startY =0;
                    int dx  =0-startX;
                    int dy =0;
                    //设置缓慢滑动
                    mScroller.startScroll(startX,startY,dx,dy,1000);
                    invalidate();
                }else {
                    if(onUnlockListener!=null){
                        onUnlockListener.onUnlock();
                    }
                }

                break;

            default:

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),0);
            invalidate();
        }
    }

    public void resetView() {
        scrollTo(0,0);
        invalidate();
    }

    public interface  OnUnlockListener{
       void  onUnlock();
    }
    public void setOnUnlockListener(OnUnlockListener l){
        this.onUnlockListener =l;
    }
}
