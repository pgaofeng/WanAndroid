package com.example.wanandroid.mvp.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wanandroid.mvp.home.adapter.ArticleAdapter;

/**
 * @author CollectAdapter
 * @date 2019/8/10
 * ${DESCRIPTION}
 */
public class CollectAdapter extends ArticleAdapter {
    public CollectAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        setCollectGone(viewHolder, i);
    }
}
