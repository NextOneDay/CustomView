package com.nextoneday.customview.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/28.
 */

public class ScrollDeleteView extends ViewGroup {

    private final String TAG = "ScrollDeleteView";
    private final ViewDragHelper mHelper;
    private View mContentView;
    private View mDeleteView;

    public ScrollDeleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = ViewDragHelper.create(this, mCallback);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);
        mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
        int right = mContentView.getMeasuredWidth() + mDeleteView.getMeasuredWidth();
        mDeleteView.layout(mContentView.getMeasuredWidth(), 0, right, mDeleteView.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;

    }


    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentView;
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            Log.d(TAG, "clampViewPositionHorizontal:" + left + ":" + dx + "KK" + mDeleteView.getMeasuredWidth());
            if (left < -mDeleteView.getMeasuredWidth()) {
                left = -mDeleteView.getMeasuredWidth();
            } else if (left > 0) {
                left = 0;
            }

            return left;

        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.d(TAG, "onViewPositionChanged:" + left + ":" + dx + "：：" + mDeleteView.getLeft());
            if (changedView == mContentView) {
                mDeleteView.layout(mDeleteView.getLeft() + dx, mDeleteView.getTop(), mDeleteView.getRight() + dx, mDeleteView.getBottom());

            } else if (changedView == mDeleteView) {
                mContentView.layout(mContentView.getLeft() + dx, mContentView.getTop(), mContentView.getRight() + dx
                        , mContentView.getBottom());
            }

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int threshold = mContentView.getMeasuredWidth()
                    - mDeleteView.getMeasuredWidth() / 2;
            if (mDeleteView.getLeft() > threshold) {
                // 滚回去
                int finalLeft = mContentView.getMeasuredWidth();
                mHelper.smoothSlideViewTo(mDeleteView, finalLeft, 0);
                invalidate();
//                isOpen = false;
            } else {
                // 滚出来
                int finalLeft = mContentView.getMeasuredWidth()
                        - mDeleteView.getMeasuredWidth();
                mHelper.smoothSlideViewTo(mDeleteView, finalLeft, 0);
                invalidate();
//                isOpen = true;
            }
        }

    };

    @Override
    public void computeScroll() {

        if (mHelper.continueSettling(true)) {
//            invalidate();  // 这里用这个可能会出现问题，所以使用下面这个
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
