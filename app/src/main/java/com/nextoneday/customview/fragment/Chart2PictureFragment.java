package com.nextoneday.customview.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nextoneday.customview.R;
import com.nextoneday.customview.test.Chart2Picture;

/**
 * Created by nextonedaygg on 2018/12/5.
 */

public class Chart2PictureFragment extends ViewFragment {

    private TextView mText;
    private StringBuilder mBuilder;

    @Override
    protected void initView(View view) {

        mText = view.findViewById(R.id.text);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_chart_picture;
    }

    @Override
    protected void initdata() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                createChartPicture(getContext().getFilesDir() + "/abc.jpg");

            }
        }).start();


    }


    public void createChartPicture(String path) {
        mBuilder = new StringBuilder();
        String base = "!@#$%^&*[]{};:?/|";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {

                int pixel = bitmap.getPixel(j, i);
                final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                final int index = Math.round(gray * (base.length() + 1) / 255);
                mBuilder.append(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
            }
            mBuilder.append("\n");
        }

        mText.post(new Runnable() {
            @Override
            public void run() {

                mText.setText(mBuilder.toString());
            }
        });

    }

    public static Chart2PictureFragment newInstance() {

        Bundle args = new Bundle();

        Chart2PictureFragment fragment = new Chart2PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
