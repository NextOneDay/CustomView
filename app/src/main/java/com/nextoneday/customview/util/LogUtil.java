package com.nextoneday.customview.util;


import android.util.Log;



public class LogUtil {

    /**
     * @return 当前的类名(simpleName)
     */
    private static String getClassName() {

        String result;
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());

        //如果调用位置在匿名内部类的话，就会产生类似于 MainActivity$3 这样的TAG
        //可以把$后面的部分去掉
        int i = result.indexOf("$");

        return i == -1 ? result : result.substring(0, i);
    }

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;
    private static final String TAG = "TAG";

    /**
     * 是否输出Log
     *
     * @param isDebug
     */
    public static void debug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG + getClassName(), msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG + "--" + getClassName(), msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG + "--" + getClassName(), msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG + "--" + getClassName(), msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
