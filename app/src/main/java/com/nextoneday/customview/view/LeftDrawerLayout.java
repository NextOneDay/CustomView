package com.nextoneday.customview.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 默认的是content在上面，memu在下面
 * 这是hongyang 大神的侧滑菜单
 */
public class LeftDrawerLayout extends ViewGroup
{
    public final String TAG="LeftDrawerLayout";
    private static final int MIN_DRAWER_MARGIN = 64; // dp
    /**
     * Minimum velocity that will be detected as a fling
     */
    private static final int MIN_FLING_VELOCITY = 400; // dips per second

    /**
     * drawer离父容器右边的最小外边距
     */
    private int mMinDrawerMargin;

    private View mLeftMenuView;
    private View mContentView;

    private ViewDragHelper mHelper;
    /**
     * drawer显示出来的占自身的百分比
     */
    private float mLeftMenuOnScrren;


    public LeftDrawerLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //setup drawer's minMargin
        final float density = getResources().getDisplayMetrics().density;
        final float minVel = MIN_FLING_VELOCITY * density;
        mMinDrawerMargin = (int) (MIN_DRAWER_MARGIN * density + 0.5f);

        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback()
        {
            /**
             * 控制边界，只能在-width到 0 之间，超过了就没有意义了
             * @param child
             * @param left
             * @param dx
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx)
            {
                int newLeft = Math.max(-child.getWidth(), Math.min(left, 0));
                return newLeft;
            }

            /**
             *  当child 为menu 的时候才能进行拖动
             *  content不能拖动
             * @param child
             * @param pointerId
             * @return
             */
            @Override
            public boolean tryCaptureView(View child, int pointerId)
            {
                Log.d(TAG,"tryCaptureView");
                return child == mLeftMenuView;
            }

            /**
             * 这个是在边界拖动的时候触发，
             * @param edgeFlags
             * @param pointerId
             */
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId)
            {
                Log.d(TAG,"onEdgeDragStarted");
                // 这个方法进行手动的设置view，这个方法可以绕过trycaptureView，
                mHelper.captureChildView(mLeftMenuView, pointerId);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel)
            {
                Log.d(TAG,"onViewReleased");
                final int childWidth = releasedChild.getWidth();
                float offset = (childWidth + releasedChild.getLeft()) * 1.0f / childWidth;
                //当大于一半 的时候就设置left为0（显示） ，小于一半就设置-width（隐藏）
                mHelper.settleCapturedViewAt(xvel > 0 || xvel == 0 && offset > 0.5f ? 0 : -childWidth, releasedChild.getTop());
                invalidate();
            }

            /**
             * view 位置发生变化
             * @param changedView
             * @param left
             * @param top
             * @param dx
             * @param dy
             */
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy)
            {
                //算出view已经移到屏幕多少位置，然后进行设置是否显示
                final int childWidth = changedView.getWidth();
                float offset = (float) (childWidth + left) / childWidth;
                mLeftMenuOnScrren = offset;
                //offset can callback here
                changedView.setVisibility(offset == 0 ? View.INVISIBLE : View.VISIBLE);
                invalidate();
            }

            /**
             * 当这个方法返回值大于0 的时候，才能被onInterceptTouchEvent 捕获，
             * @param child
             * @return
             */
            @Override
            public int getViewHorizontalDragRange(View child)
            {
                return mLeftMenuView == child ? child.getWidth() : 0;
            }
        });
        //设置edge_left track
        mHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        //设置minVelocity
        mHelper.setMinVelocity(minVel);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);

        View leftMenuView = getChildAt(1);
        MarginLayoutParams lp = (MarginLayoutParams)
                leftMenuView.getLayoutParams();

        final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,
                mMinDrawerMargin + lp.leftMargin + lp.rightMargin,
                lp.width);
        final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,
                lp.topMargin + lp.bottomMargin,
                lp.height);
        leftMenuView.measure(drawerWidthSpec, drawerHeightSpec);


        View contentView = getChildAt(0);
        lp = (MarginLayoutParams) contentView.getLayoutParams();
        final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
        final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
        contentView.measure(contentWidthSpec, contentHeightSpec);

        mLeftMenuView = leftMenuView;
        mContentView = contentView;


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        View menuView = mLeftMenuView;
        View contentView = mContentView;

        MarginLayoutParams lp = (MarginLayoutParams) contentView.getLayoutParams();
        contentView.layout(lp.leftMargin, lp.topMargin,
                lp.leftMargin + contentView.getMeasuredWidth(),
                lp.topMargin + contentView.getMeasuredHeight());

        lp = (MarginLayoutParams) menuView.getLayoutParams();

        final int menuWidth = menuView.getMeasuredWidth();
        int childLeft = -menuWidth + (int) (menuWidth * mLeftMenuOnScrren);
        menuView.layout(childLeft, lp.topMargin, childLeft + menuWidth,
                lp.topMargin + menuView.getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        boolean shouldInterceptTouchEvent = mHelper.shouldInterceptTouchEvent(ev);
        return shouldInterceptTouchEvent;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mHelper.processTouchEvent(event);
        return true;
    }


    @Override
    public void computeScroll()
    {
        if (mHelper.continueSettling(true))
        {
            invalidate();
        }
    }

    public void closeDrawer()
    {
        View menuView = mLeftMenuView;
        mLeftMenuOnScrren = 0.f;
        mHelper.smoothSlideViewTo(menuView, -menuView.getWidth(), menuView.getTop());
    }

    public void openDrawer()
    {
        View menuView = mLeftMenuView;
        mLeftMenuOnScrren = 1.0f;
        mHelper.smoothSlideViewTo(menuView, 0, menuView.getTop());
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams()
    {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
    {
        return new MarginLayoutParams(p);
    }

}