package com.nextoneday.customview.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nextoneday.customview.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/23.
 *
 *这是一个 组合控件， 通过下拉才能选择条目，设置进输入框上
 * 还应该提供一个接口回调，然后外部进行选择监听
 * 删除监听，
 */

public class SpinnerView extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView mIvArr;
    private EditText mEditText;
    private ArrayList<String> mdata;
    private PopupWindow mWindow;

    public SpinnerView(Context context) {
        super(context);
        init();
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /**
     * 初始化
     */
    private void init() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this);
        mEditText = view.findViewById(R.id.ed_text);
        mIvArr = view.findViewById(R.id.iv_arr);

        initData();

        initEvent();
    }

    private void initData() {
        mdata = new ArrayList<>();
        for (int x = 10000; x < 10040; x++) {
            mdata.add(x + "");
        }
    }
    public void setData(ArrayList<String> al){
        this.mdata=mdata;
    }



    private void initEvent() {
        mIvArr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        showWindow();
    }

    /**
     * 弹出一个窗口
     */
    private void showWindow() {

        if (mWindow == null) {
            mWindow = new PopupWindow();
            mWindow.setWidth(mEditText.getWidth());
            mWindow.setHeight(LayoutParams.WRAP_CONTENT);
            ListView contentView = new ListView(getContext());
            contentView.setVerticalScrollBarEnabled(false);
            contentView.setAdapter(mAdapter);
            mWindow.setContentView(contentView);
            contentView.setOnItemClickListener(this);

            mWindow.setFocusable(true);
            mWindow.setBackgroundDrawable(new ColorDrawable());
            mWindow.setOutsideTouchable(true);
        }

        mWindow.showAsDropDown(mEditText);

    }

    private BaseAdapter mAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return mdata == null ? 0 : mdata.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final String text = mdata.get(position);
            holder.tvText.setText(text);
            holder.ivDelete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mdata.remove(text);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = mdata.get(position);
        mEditText.setText(s);
        mEditText.setSelection(s.length());
        mWindow.dismiss();
    }

    private class ViewHolder {
        ImageView ivLogo;
        TextView tvText;
        ImageView ivDelete;

        ViewHolder(View rootview) {
            ivLogo = rootview.findViewById(R.id.iv_logo);
            tvText = rootview.findViewById(R.id.tv_text);
            ivDelete = rootview.findViewById(R.id.iv_delete);
        }
    }
}
