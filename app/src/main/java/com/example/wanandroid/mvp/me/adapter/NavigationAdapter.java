package com.example.wanandroid.mvp.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.HeaderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author NavigationAdapter
 * @date 2019/8/12
 * ${DESCRIPTION}
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {


    private List<HeaderBean> data;
    private Context mContext;

    public NavigationAdapter(Context context) {
        this.mContext = context;
        data = new ArrayList<>();
    }

    public void setData(List<HeaderBean> data) {
        int oldSize = this.data.size();
        int newSize = data.size();
        this.data.clear();
        this.data.addAll(data);
        if (newSize > oldSize) {
            notifyItemRangeChanged(0, oldSize);
            notifyItemRangeInserted(oldSize, newSize - oldSize);
        } else if (newSize == oldSize) {
            notifyItemRangeChanged(0, newSize);
        } else {
            notifyItemRangeChanged(0, newSize);
            notifyItemRangeRemoved(newSize, oldSize - newSize);
        }
    }

    public HeaderBean getBean(int position) {
        if (data.size() > position) {
            return data.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_navigation, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemNavigationTitle.setText(data.get(i).getName());
        viewHolder.itemNavigationLink.setText(data.get(i).getLink());
        // 设置View的tag，用于区分是否是标准头
        if (data.get(i).isHeader()) {
            viewHolder.itemView.setTag(true);
        } else {
            viewHolder.itemView.setTag(false);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_navigation_title)
        TextView itemNavigationTitle;
        @BindView(R.id.item_navigation_link)
        TextView itemNavigationLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
