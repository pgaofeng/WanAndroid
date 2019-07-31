package com.example.wanandroid.mvp.type.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.TypeBean;
import com.example.wanandroid.util.ScreenUtils;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    private static final String TAG = "TypeAdapter";

    private List<TypeBean> mDatas;
    private Context mContext;

    public TypeAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
    }

    public void setDatas(List<TypeBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i(TAG, "onBindViewHolder position: "+i);
        viewHolder.mTypeItemTitle.setText(mDatas.get(i).getName());
        viewHolder.mTypeItemChildren.removeAllViews();
        for (TypeBean.ChildrenBean bean : mDatas.get(i).getChildren()) {
            TextView textView = new TextView(mContext);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int margin = ScreenUtils.dp2px(mContext, 10);
            int padding = ScreenUtils.dp2px(mContext, 6);
            params.setMargins(margin, margin, margin, 0);

            textView.setLayoutParams(params);
            textView.setBackgroundResource(R.drawable.bg_selectable_item);
            textView.setText(bean.getName());
            textView.setClickable(true);
            textView.setPadding(padding, padding, padding, padding);
            viewHolder.mTypeItemChildren.addView(textView);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.type_item_title)
        TextView mTypeItemTitle;
        @BindView(R.id.type_item_children)
        FlexboxLayout mTypeItemChildren;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
