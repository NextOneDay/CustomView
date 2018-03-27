package com.nextoneday.customview.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.nextoneday.customview.R;

/**
 * Created by Administrator on 2018/3/27.
 * 自定义一个dialog
 */

public class DialogView extends Dialog implements View.OnClickListener {
    public DialogView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_view);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
