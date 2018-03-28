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
 * Created by Administrator on 2018/3/26.
 */

public class SlidingMenu extends ViewGroup {

    private static final String TAG = "SlidingMenu";

    private final ViewDragHelper mHelper;
    private float mDownX;
    private float mDownY;
    private View mMenu;
    private View mContent;
    private boolean isOpen;

    // 通过设置一个值，来提表示拖动多少,比如屏幕的百分比
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper = ViewDragHelper.create(this, mCallback);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        //通过google 提供的测量child 的方法进行设置，内部也是通过循环遍历child ，
        // 再通过获取child LayoutParams获取child在布局中设置的参数，再通过调用child自己的measure方法进行测量

        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 对两个界面进行设置好需要放置的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mMenu = getChildAt(0);
        mMenu.layout(-mMenu.getMeasuredWidth(), 0, 0, mMenu.getMeasuredHeight());
        mContent = getChildAt(1);
        mContent.layout(0, 0, mContent.getMeasuredWidth(), mContent.getMeasuredHeight());

    }

    /**
     * 消费事件，把事件交给ViewDragHelper 处理
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        mHelper.processTouchEvent(event);
        return true;

    }

    /**
     * 拦截事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");

//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = ev.getX();
//                mDownY = ev.getY();
//                // actiondown 被消费了，ViewDragHelper 就接收不到了，需要手动传入
//                mHelper.processTouchEvent(ev);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = ev.getX();
//                float moveY = ev.getY();
//                float dx = Math.abs(moveX - mDownX);
//                float dy = Math.abs(moveY - mDownY);
//
//                if (dx > dy) {
//                    //水平滑动 拦截
//                    // TODO: 2018/3/27 需要判断是不是viewpager这种情况，
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
        return mHelper.shouldInterceptTouchEvent(ev);
//        return false;
    }

    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        /**
         * 是否允许拖动child  true 允许，false 不允许
         * @param child
         * @param pointerId
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView");

            return true;
        }


        /**
         * 用来水平拖动child的，不管拖动左边还是右边都不能越界
         * @param child
         * @param left
         * @param dx
         * @return
         */

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            Log.d(TAG, "clampViewPositionHorizontal" + child.getId() + "::" + left + "::" + dx);

            if (child == mContent) {
                //拖动右边的时候限制他的left 不能超过左边view的宽度
                if (left < 0) {
                    left = 0;
                } else if (left > (mMenu.getMeasuredWidth())) {
                    left = mMenu.getMeasuredWidth();
                }

            } else if (child == mMenu) {
                // 拖动左边的时候限制他的left ，不能超过自己0，也就是不能整个view移除屏幕
                if (left > 0) {
                    left = 0;
                }
            }

            return left;
        }

        /**
         * 当child 的位置发生变化的时候进行回调
         * 当拖动右边，把左边的进行拖出
         * 当拖动左边，右边跟着动
         * @param changedView
         * @param left changedview 的新left
         * @param top 同上
         * @param dx changedView 在x轴的偏移量
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.d(TAG, "onViewPositionChanged");

            if (changedView == mContent) {
                mMenu.layout(mMenu.getLeft() + dx, top, mMenu.getRight() + dx, mMenu.getBottom());

            } else if (changedView == mMenu) {
                mContent.layout(mContent.getLeft() + dx, top, mContent.getRight() + dx, mContent.getBottom());
            }
        }

        /**
         *+
         * 当不主动拖view的时候的回调
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.d(TAG, "onViewReleased");

            //当拖动大于临界时， 打开侧滑菜单，
//            当拖动小于临界值时，关闭侧滑菜单
            int thres = mMenu.getMeasuredWidth() / 2;
            if (mContent.getLeft() > thres) {
//                打开
                openMenu();
            } else {
//                关闭
                closeMenu();
            }
        }


    };

    /**
     * 打开，平滑的滑动
     */
    private void openMenu() {
        int finalleft = mMenu.getMeasuredWidth();
        mHelper.smoothSlideViewTo(mContent, finalleft, 0);
        isOpen = true;
        invalidate();
    }

    private void closeMenu() {
        mHelper.smoothSlideViewTo(mContent, 0, 0);
        isOpen = false;
        invalidate();
    }

    public void setToggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    /**
     * 计算滑动偏移量平滑移动
     */
    @Override
    public void computeScroll() {

        if (mHelper.continueSettling(true)) {
//            invalidate();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
