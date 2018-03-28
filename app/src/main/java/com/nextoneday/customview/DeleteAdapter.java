package com.nextoneday.customview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class DeleteAdapter extends RecyclerView.Adapter<DeleteAdapter.DeleteViewHolder> {


    private final List<String> mDeleteList;

    public DeleteAdapter(List<String> delete) {
        this.mDeleteList = delete;
    }

    @Override
    public DeleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        DeleteAdapter.DeleteViewHolder holder= new DeleteViewHolder(inflate);
        return  holder;
    }

    @Override
    public void onBindViewHolder(DeleteViewHolder holder, int position) {
        String text = mDeleteList.get(position);
        holder.textContent.setText(text);
    }

    @Override
    public int getItemCount() {
        return mDeleteList == null ? 0 : mDeleteList.size();
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {
        TextView textContent;
        TextView textDelete;

        public DeleteViewHolder(View itemView) {
            super(itemView);
            textContent=itemView.findViewById(R.id.tv_content);
            textDelete =itemView.findViewById(R.id.tv_delete);
        }
    }
}
