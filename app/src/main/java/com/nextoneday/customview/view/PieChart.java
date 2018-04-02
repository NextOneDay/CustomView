package com.nextoneday.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.nextoneday.customview.R;
import com.nextoneday.customview.bean.PieData;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends View {

    private final String TAG = getClass().getSimpleName();
    private final float DEFAULT_START_ANGLE = 45;

    /*
     * mPaint1: for sector
     * mPaint2: for center circle and center text
     * mPaint3: for center circle shadow
     */
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;

    //get padding size
    private int paddingTop;
    private int paddingBottom;
    private int paddingStart;
    private int paddingEnd;

    //coordinate for center of the view
    private int centerX;
    private int centerY;

    private float mStartAngle;
    private float mMaxValue;
    private float mTextSize;
    private float mShaderSize;

    //fraction for animator
    private float curtFraction = 1f;
    private float curtFractionTouch = 1f;
    private float curtFractionTouch2 = 0f;

    private int mCurrentColor;

    //index for which sector touched
    private int index;

    //list for pie params
    private List<Integer> pieColorList;
    private List<Float> pieValueList;
    private List<String> pieStringList;

    //list for the middle angle of each sector
    private List<Float> angleList;

    //sector radius
    private int mRadius;

    //center circle radius
    private int circleRadius;

    //coordinate for touch
    private float touchX;
    private float touchY;

    //animator for init pie chart
    private ValueAnimator animator;

    //animator for touch
    private ValueAnimator animatorTouch;

    //center text
    private String text;

    public PieChart(Context context) {
        super(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PieChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PieChartView);

        mRadius = a.getDimensionPixelSize(R.styleable.PieChartView_pie_radius,
                getResources().getDimensionPixelSize(R.dimen.pie_default_radius));
        circleRadius = a.getDimensionPixelSize(R.styleable.PieChartView_centerCircle_radius,
                getResources().getDimensionPixelSize(R.dimen.centerCircle_radius));
        mTextSize = a.getDimension(R.styleable.PieChartView_textSize,
                getResources().getDimension(R.dimen.text_size));
        mShaderSize = a.getDimension(R.styleable.PieChartView_shaderSize,
                getResources().getDimension(R.dimen.shader_size));

        a.recycle();

        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        paddingStart = getPaddingStart();
        paddingEnd = getPaddingEnd();

        initPaint();
        initAnimator();
    }

    /**
     * init Paint and params for pie chart
     */
    private void initPaint() {
        pieColorList = new ArrayList<>();
        pieValueList = new ArrayList<>();
        pieStringList = new ArrayList<>();
        angleList = new ArrayList<>();


        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setAntiAlias(true);
        mPaint2.setTextSize(mTextSize);
        mPaint2.setTextAlign(Paint.Align.CENTER);

        mPaint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * init animators
     */
    private void initAnimator() {

        animator = ValueAnimator.ofFloat(0, 1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curtFraction = animation.getAnimatedFraction();
                mStartAngle = DEFAULT_START_ANGLE;
                invalidate();
            }
        });

        animatorTouch = ValueAnimator.ofFloat(1f, 1.07f);
        animatorTouch.setDuration(400);
        animatorTouch.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curtFractionTouch = (Float) animation.getAnimatedValue();
                curtFractionTouch2 = 0.02f * animation.getAnimatedFraction();

                invalidate();
            }
        });

    }

    /**
     * @param i index for params list
     * @return angle to rotation
     */
    private float getRotationAngle(int i) {
        float angleR;
        float angleT = angleList.get(i);
        if (angleT <= 270f && angleT >= 90f) {
            angleR = 90f - angleT;
        } else if (angleT > 270f && angleT <= 360f) {
            angleR = 360f - angleT + 90f;
        } else if (angleT >= 0 && angleT < 90) {
            angleR = 90 - angleT;
        } else {
            angleR = 0;
            Log.w(TAG, "Angle < 0 || Angle > 360");
        }

        for (int id = 0; id < angleList.size(); id++) {
            float temp = angleList.get(id) + angleR;
            if (temp > 360f) {
                temp -= 360f;
            } else if (temp < 0) {
                temp += 360f;
            }
            angleList.set(id, temp);
        }
        return angleR;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = mRadius * 2 + paddingStart + paddingEnd;
        int mHeight = mRadius * 2 + paddingTop + paddingBottom;

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT &&
                getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = paddingStart + (w - paddingStart - paddingEnd) / 2;
        centerY = paddingTop + (h - paddingTop - paddingBottom) / 2;

        mPaint3.setShader(new RadialGradient(centerX, centerY,
                circleRadius + mShaderSize,
                0xFF000000, 0x00FFFFFF, Shader.TileMode.CLAMP));
    }

    /**
     * start animator to draw pie chart
     */
    public void startDrawPie() {
        animator.start();
    }

    /**
     * @param amount value of each sector
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPie(Canvas canvas, float amount) {

        mPaint1.setColor(mCurrentColor);

        float mAngle = 360 * curtFraction * amount / mMaxValue;

        canvas.drawArc(centerX - mRadius, centerY - mRadius, centerX + mRadius,
                centerY + mRadius, mStartAngle, mAngle, true, mPaint1);
        mStartAngle += mAngle;

    }

    /**
     * draw small sector
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPieTouch(Canvas canvas, float amount) {

        mPaint1.setColor(mCurrentColor);

        float mAngle = 360 * curtFraction * amount / mMaxValue;

        float mRadiusTemp = mRadius * curtFractionTouch;
        canvas.drawArc(centerX - mRadiusTemp, centerY - mRadiusTemp, centerX + mRadiusTemp,
                centerY + mRadiusTemp, mStartAngle + mAngle * curtFractionTouch2,
                mAngle - mAngle * curtFractionTouch2 * 2, true, mPaint1);

        mStartAngle += mAngle;

    }

    /**
     * @param text center text
     */
    public void setCenterText(String text){
        this.text = text;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode()) {
//            mMaxValue = 30;
//            mStartAngle = DEFAULT_START_ANGLE;
//
//            mCurrentColor = Color.parseColor("#F48FB1");
//            drawPie(canvas, 10);
//            mCurrentColor = Color.parseColor("#81D4FA");
//            drawPie(canvas, 10);
//            mCurrentColor = Color.parseColor("#FFAB91");
//            drawPie(canvas, 10);
//
//            canvas.drawCircle(centerX, centerY,
//                    circleRadius + mShaderSize, mPaint3);
//
//            mPaint2.setColor(Color.WHITE);
//            canvas.drawCircle(centerX, centerY, circleRadius, mPaint2);
//
//            text = "InEditMode";
//
//            mPaint2.setColor(Color.BLACK);
//            canvas.drawText(text, centerX, centerY + mTextSize / 2, mPaint2);

        } else {

            for (int i = 0; i < pieValueList.size(); i++) {
                mCurrentColor = pieColorList.get(i);

                if (i == index) {
                    drawPieTouch(canvas, pieValueList.get(i));
                } else {
                    drawPie(canvas, pieValueList.get(i));
                }


            }

            canvas.drawCircle(centerX, centerY,
                    circleRadius + mShaderSize, mPaint3);

            mPaint2.setColor(Color.WHITE);
            canvas.drawCircle(centerX, centerY, circleRadius, mPaint2);
            if (text != null) {
                mPaint2.setColor(Color.BLACK);
                canvas.drawText(text, centerX, centerY + mTextSize / 2, mPaint2);
            }
        }

    }

    /**
     * touch transfer
     * @param i index for params list
     */
    private void onTouchPie(int i) {
        index = i;
        curtFractionTouch = 1f;
        curtFractionTouch2 = 0f;

        float angle = getRotationAngle(i);

        ValueAnimator animatorRotation;
        animatorRotation = ValueAnimator.ofFloat(mStartAngle, mStartAngle + angle);
        animatorRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartAngle = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });

        int time = (int) (1000 * Math.abs(angle) / 360);

        animatorRotation.setDuration(time);
        animatorTouch.setStartDelay(time);

        animatorRotation.start();
        animatorTouch.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;
            case MotionEvent.ACTION_UP:

                setDrawingCacheEnabled(true);
                Bitmap bitmap = getDrawingCache();
                if (bitmap == null) {
                    Log.w(TAG, "bitmap == null");
                    break;
                }
                int pixel = bitmap.getPixel((int) touchX, (int) touchY);
                setDrawingCacheEnabled(false);

                int i = 0;
                for (int color : pieColorList) {
                    if (pixel == color) {

                        onTouchPie(i);

                        break;
                    }
                    i++;
                }
                break;
        }
        return true;
    }

    /**
     * set pie chart params
     * @param pieList pie params list
     */
    public void setPie(List<PieData> pieList) {
        mMaxValue = 0;
        curtFractionTouch = 1f;
        curtFractionTouch2 = 0f;

        pieColorList = new ArrayList<>();
        pieStringList = new ArrayList<>();
        pieValueList = new ArrayList<>();
        angleList = new ArrayList<>();

        for (PieData pie : pieList) {
            pieColorList.add(pie.color);
            pieValueList.add((float) pie.value);
            mMaxValue += pie.value;
            pieStringList.add(pie.name);
        }

        float angleTemp;
        float startAngleTemp = DEFAULT_START_ANGLE;

        for (float v : pieValueList) {
            angleTemp = 360 * v / mMaxValue;

            angleList.add(startAngleTemp + angleTemp / 2);

            startAngleTemp += angleTemp;
        }

    }
}