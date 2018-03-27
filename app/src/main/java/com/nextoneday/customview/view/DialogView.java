package com.nextoneday.customview.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2018/3/27.
 * 自定义一个dialog
 */

public class DialogView extends Dialog {
    public DialogView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
    }
}
