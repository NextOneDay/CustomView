package com.nextoneday.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextoneday.customview.R;
import com.nextoneday.customview.util.CircleUtil;

/**
 * Created by Administrator on 2018/4/2.
 */

public class CircleMenuView extends ViewGroup {
    private int startAngle;
    private int mD; //直径
    private float lastX;
    private float lastY;

    public CircleMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //自己测量自己
        int widthMeasure = 0;
        int heightmeasure = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            //match_parent 或者 具体的dp
            int min = Math.min(size, getDefatuleSize());
            widthMeasure = min;
        } else if (mode == MeasureSpec.AT_MOST) {
            // wrap_content ,获取背景 来作为测量大小
            int suggestedMinimumWidth = getSuggestedMinimumWidth();//获取背景大小
            if (suggestedMinimumWidth == 0) {
                widthMeasure = getDefatuleSize();
            } else {
                widthMeasure = Math.min(suggestedMinimumWidth, getDefatuleSize());
            }
        }
        mD = widthMeasure;
        heightmeasure = widthMeasure;
        setMeasuredDimension(widthMeasure, heightmeasure);

        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int width ;
            int height ;
            width = MeasureSpec.makeMeasureSpec(mD/3, MeasureSpec.EXACTLY);
            height = MeasureSpec.makeMeasureSpec(mD/3, MeasureSpec.EXACTLY);
            childAt.measure(width, height);
        }

        //并且测量view的size
    }

    public int getDefatuleSize() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int result = Math.min(width, height);
        return result;


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //对子view 进布局
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();

            float temp = mD / 3.0f;
            int left = (int) (mD / 2 + Math.round(temp * Math.cos(Math.toRadians(startAngle))) - childWidth / 2);
            int right = left + childWidth;
            int top = (int) (mD / 2 + Math.round(temp * Math.sin(Math.toRadians(startAngle))) - childWidth / 2);
            int bottom = top + childWidth;
            child.layout(left, top, right, bottom);
            startAngle += 360 / getChildCount();

            child.layout(left, top, right, bottom);
        }
    }


    private void init() {


    }

    public void setData(int[] images,String []titles ){

        for (int i = 0; i < images.length; i++) {
            View view = View.inflate(getContext(), R.layout.circle_item, null);
            ImageView image = view.findViewById(R.id.iv_image);
            TextView tvText = view.findViewById(R.id.text);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(images[i]);
            tvText.setText(titles[i]);
            addView(view);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                 lastX = event.getX();
                 lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x =event.getX();
                float y = event.getY();

                float start = CircleUtil.getAngle(lastX, lastY, mD);
                float end = CircleUtil.getAngle(x, y, mD);
                float angle;
                //判断点击的点所处的象限,如果是1,4象限,角度值是正数,否则是负数
                if (CircleUtil.getQuadrant(x, y, mD) == 1 || CircleUtil.getQuadrant(x, y, mD) == 4) {
                    angle = end - start;
                } else {
                    angle = start - end;
                }
                startAngle += angle;
                //让界面重新布局和绘制
                requestLayout();
                lastX = x;
                lastY = y;

                break;
            case MotionEvent.ACTION_UP:


                break;
        }
        return true;
    }
}
