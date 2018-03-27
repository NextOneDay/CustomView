package com.nextoneday.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/3/26.
 */

public class SlidingMenu extends RelativeLayout {
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }
}
