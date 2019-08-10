package com.example.wanandroid.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.ArticleBean;
import com.example.wanandroid.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : 首页文章列表对应Adapter
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context mContext;
    /**
     * 基本文章列表
     */
    private List<ArticleBean.DatasBean> basicList;
    /**
     * 置顶文章列表
     */
    private List<ArticleBean.DatasBean> topList;
    private OnCollectClickListener mListener;


    public ArticleAdapter(Context context) {
        mContext = context;
        basicList = new ArrayList<>();
        topList = new ArrayList<>();
    }

    /**
     * 设置收藏按钮点击事件
     *
     * @param listener 点击事件
     */
    public void setOnCollectClickListener(OnCollectClickListener listener) {
        if (listener != null) {
            this.mListener = listener;
        }
    }

    /**
     * 设置收藏状态
     *
     * @param isCollect 是否收藏
     * @param position  位置
     * @param view      图标
     */
    public void setCollect(boolean isCollect, int position, View view) {
        ArticleBean.DatasBean bean;
        if (topList.size() == 0) {
            bean = basicList.get(position);
        } else if (position < topList.size()) {
            bean = topList.get(position);
        } else {
            bean = basicList.get(position - topList.size());
        }
        bean.setCollect(isCollect);
        notifyItemChanged(position, view);
    }


    /**
     * 设置数据集
     *
     * @param basicList 基本文章数据
     * @param topList   置顶文章数据
     */
    public void setDatas(List<ArticleBean.DatasBean> basicList, List<ArticleBean.DatasBean> topList) {
        if (basicList != null) {
            int oldSize = this.basicList.size();
            int newSize = basicList.size();

            this.basicList.clear();
            this.basicList.addAll(basicList);

            // 新数据少于旧数据，移除多余数据。多于旧数据的话，则添加多于数据
            if (oldSize > newSize) {
                notifyItemRangeRemoved(this.topList.size() + newSize, oldSize - newSize);
            } else if (oldSize < newSize) {
                notifyItemRangeInserted(this.topList.size() + oldSize, newSize - oldSize);
            }
            // 其余的进行change变化
            notifyItemRangeChanged(this.topList.size(), newSize);
        }
        if (topList != null) {
            int oldSize = this.topList.size();
            int newSize = topList.size();

            this.topList.clear();
            this.topList.addAll(topList);

            if (oldSize > newSize) {
                notifyItemRangeRemoved(newSize, oldSize - newSize);
            } else if (oldSize < newSize) {
                notifyItemRangeInserted(oldSize, newSize - oldSize);
            }
            notifyItemRangeChanged(0, newSize);
        }
    }

    /**
     * 添加文章数据
     *
     * @param basicList 基础文章数据
     */
    public void addDatas(List<ArticleBean.DatasBean> basicList) {
        if (basicList != null) {
            this.basicList.addAll(basicList);
            notifyItemRangeChanged(this.topList.size(), basicList.size());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * 隐藏收藏按钮
     *
     * @param holder holder
     * @param i      position
     */
    protected void setCollectGone(ViewHolder holder, int i) {
        holder.mHomeItemCollect.setVisibility(View.GONE);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ArticleBean.DatasBean bean;
        if (topList.size() == 0) {
            bean = basicList.get(i);
        } else if (i < topList.size()) {
            bean = topList.get(i);
        } else {
            bean = basicList.get(i - topList.size());
        }
        // 作者
        viewHolder.mHomeItemAuthor.setText(fromHtml(bean.getAuthor()));
        // 章节名称
        viewHolder.mHomeItemCharacter.setText(fromHtml(bean.getChapterName()));
        // 收藏
        viewHolder.mHomeItemCollect.setImageResource(bean.isCollect() ? R.mipmap.ic_collect : R.mipmap.ic_uncollect);
        // 描述
        if (TextUtils.isEmpty(bean.getDesc())) {
            viewHolder.mHomeItemDesc.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemDesc.setVisibility(View.VISIBLE);
            viewHolder.mHomeItemDesc.setText(Html.fromHtml(bean.getDesc()));
        }
        // 图片
        if (TextUtils.isEmpty(bean.getEnvelopePic())) {
            viewHolder.mHomeItemImage.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemImage.setVisibility(View.VISIBLE);
            ScreenUtils.loadImg(mContext, viewHolder.mHomeItemImage, bean.getEnvelopePic());
        }
        // 最新
        if (bean.isFresh()) {
            viewHolder.mHomeItemNew.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mHomeItemNew.setVisibility(View.GONE);
        }
        // 日期
        viewHolder.mHomeItemTime.setText(bean.getNiceDate());
        // 父章节
        viewHolder.mHomeItemSuperCharacter.setText(bean.getSuperChapterName());
        // 分类
        if (bean.getTags() == null || bean.getTags().size() == 0) {
            viewHolder.mHomeItemType.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemType.setVisibility(View.VISIBLE);
            viewHolder.mHomeItemType.setText(bean.getTags().get(0).getName());
        }
        // 标题
        viewHolder.mHomeItemTitle.setText(fromHtml(bean.getTitle()));
        // 父章节或者本身章节为空的时候，隐藏分割点
        if (TextUtils.isEmpty(bean.getChapterName()) || TextUtils.isEmpty(bean.getSuperChapterName())) {
            viewHolder.mHomeDot.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeDot.setVisibility(View.VISIBLE);
        }

        // 设置置顶文章
        if (bean.getType() == 1) {
            viewHolder.mHomeItemTop.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mHomeItemTop.setVisibility(View.GONE);
        }

        // 设置收藏点击事件
        if (mListener != null) {
            viewHolder.mHomeItemCollect.setOnClickListener(v -> {
                bean.setCollect(!bean.isCollect());
                notifyItemChanged(i, viewHolder.mHomeItemCollect);
                mListener.onCollectClick(i, viewHolder.mHomeItemCollect, bean.getId(), bean.isCollect());
            });
        }
    }

    /**
     * 解析html字符，并且替换em标签，将其标记为红色
     *
     * @param html 字符串
     * @return
     */
    private Spanned fromHtml(String html) {
        String result = html
                .replace("<em", "<font color=red ")
                .replace("</em>", "</font>");
        return Html.fromHtml(result);
    }

    @Override
    public int getItemCount() {
        return basicList.size() + topList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_item_author)
        TextView mHomeItemAuthor;
        @BindView(R.id.home_item_time)
        TextView mHomeItemTime;
        @BindView(R.id.home_item_image)
        ImageView mHomeItemImage;
        @BindView(R.id.home_item_title)
        TextView mHomeItemTitle;
        @BindView(R.id.home_item_desc)
        TextView mHomeItemDesc;
        @BindView(R.id.home_item_top)
        TextView mHomeItemTop;
        @BindView(R.id.home_item_type)
        TextView mHomeItemType;
        @BindView(R.id.home_item_super_character)
        TextView mHomeItemSuperCharacter;
        @BindView(R.id.home_item_character)
        TextView mHomeItemCharacter;
        @BindView(R.id.home_item_collect)
        ImageView mHomeItemCollect;
        @BindView(R.id.home_item_new)
        TextView mHomeItemNew;
        @BindView(R.id.home_item_dot)
        View mHomeDot;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCollectClickListener {
        /**
         * 点击收藏触发的事件
         *
         * @param position  点击的item的位置
         * @param view      点击的对象
         * @param articleId 文章的ID
         * @param isCollect 是否收藏
         */
        void onCollectClick(int position, View view, int articleId, boolean isCollect);
    }

}
