package com.nextoneday.customview.activity;



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
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");

    }
}
