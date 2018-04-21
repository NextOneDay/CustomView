package com.nextoneday.customview.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextoneday.customview.R;
import com.nextoneday.customview.activity.MainActivity;


/**
 * Created by nextonedaygg on 2018/4/21.
 */

public class TestFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "TestFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG,"onAttach....");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate....");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView....");
        View view = inflater.inflate(R.layout.fragment_test,container,false);
        view.findViewById(R.id.test).setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated...");
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated....");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState....");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart....");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause....");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop....");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"onDestroyView....");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy....");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach...");

    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
    }
}
