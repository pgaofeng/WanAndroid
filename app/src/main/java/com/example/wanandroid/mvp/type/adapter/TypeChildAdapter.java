package com.example.wanandroid.mvp.type.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class TypeChildAdapter extends RecyclerView.Adapter<TypeChildAdapter.ViewHolder> {
    private Context mContext;
    private List<TypeBean.ChildrenBean> mDatas;

    public TypeChildAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    public void setDatas(List<TypeBean.ChildrenBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_child, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mDatas.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.type_item_children_title);
        }
    }
}
