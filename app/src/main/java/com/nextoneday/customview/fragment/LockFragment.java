package com.nextoneday.customview.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.nextoneday.customview.R;
import com.nextoneday.customview.view.LockView;

/**
 * Created by nextonedaygg on 2018/3/25.
 */

public class LockFragment extends ViewFragment implements LockView.OnUnlockListener {

    private LockView mLockView;

    @Override
    protected void initView(View view) {

        mLockView = view.findViewById(R.id.unlock);
        mLockView.setOnUnlockListener(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_lock;
    }

    @Override
    protected void initdata() {

    }

    public static Fragment newInstance() {

        return  new LockFragment();
    }

    @Override
    public void onUnlock() {
        getActivity().finish();
    }
}
