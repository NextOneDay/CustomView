package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27.
 *
 *
 */

public class EyesView  extends View{

    public EyesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
                1. 先画一个圆
                2. 再画线框
                3.画一个中间框
                4.使用贝塞尔画勾玉？ 或者使用图像叠加？

                5。使用旋转动画进行插值器，进行加速旋转
                6.在选转过程中将勾玉进行连线绘制在一起，
                7.勾玉连在一起后，进行渐变放大，瞬间缩小显示

         */
    }
}
