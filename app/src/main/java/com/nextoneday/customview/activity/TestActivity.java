package com.nextoneday.customview.activity;



import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nextoneday.customview.R;
import com.nextoneday.customview.fragment.TestFragment;

/**
 * Created by nextonedaygg on 2018/3/26.
 */

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "onCreate...");
        addFragment();
    }

    private void addFragment() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        TestFragment test = new TestFragment();
        transaction.replace(R.id.fl_content, test);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart...");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause...");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop....");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");

    }

    /**
     * 当默认的时候，activity横竖屏切换的时候，就走生命周期方法，然后重建一个新的activity，
     * 会onsaveInstanceState 和 onRestoreSaveInstanceState 方法。
     * 当清单文件设置了configuration 为orientation|keyboardHidden|screenSize的时候，不会进行生命周期的变化
     * 而是回调onConfigurationChanged方法
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged-"+newConfig.toString());

        if (this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {

        }else if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){



        }
    }
}
