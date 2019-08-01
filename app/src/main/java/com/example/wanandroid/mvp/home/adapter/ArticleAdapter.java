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
    private List<ArticleBean.DatasBean> datas;

    public ArticleAdapter(Context context) {
        mContext = context;
        datas = new ArrayList<>();
    }

    /**
     * 设置数据
     * @param datas 数据
     */
    public void setNewDatas(List<ArticleBean.DatasBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 获取adapter数据集
     * @return 数据
     */
    public List<ArticleBean.DatasBean> getDatas(){
        return this.datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // 作者
        viewHolder.mHomeItemAuthor.setText(fromHtml(datas.get(i).getAuthor()));
        // 章节名称
        viewHolder.mHomeItemCharacter.setText(fromHtml(datas.get(i).getChapterName()));
        // 收藏
        viewHolder.mHomeItemCollect.setImageResource(datas.get(i).isCollect() ? R.mipmap.ic_collect : R.mipmap.ic_collect);
        // 描述
        if (TextUtils.isEmpty(datas.get(i).getDesc())) {
            viewHolder.mHomeItemDesc.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemDesc.setText(Html.fromHtml(datas.get(i).getDesc()));
        }
        // 图片
        if (TextUtils.isEmpty(datas.get(i).getEnvelopePic())) {
            viewHolder.mHomeItemImage.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemImage.setImageResource(R.mipmap.ic_launcher);
        }
        // 最新
        if (!datas.get(i).isFresh()) {
            viewHolder.mHomeItemNew.setVisibility(View.GONE);
        }
        // 日期
        viewHolder.mHomeItemTime.setText(datas.get(i).getNiceDate());
        // 父章节
        viewHolder.mHomeItemSuperCharacter.setText(datas.get(i).getSuperChapterName());
        // 分类
        if (datas.get(i).getTags() == null || datas.get(i).getTags().size() == 0) {
            viewHolder.mHomeItemType.setVisibility(View.GONE);
        } else {
            viewHolder.mHomeItemType.setText(datas.get(i).getTags().get(0).getName());
        }
        // 标题
        viewHolder.mHomeItemTitle.setText(Html.fromHtml(datas.get(i).getTitle()));

        // 设置置顶文章
        if (datas.get(i).getType()==1){
            viewHolder.mHomeItemTop.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 解析html字符
     * @param html 字符串
     * @return
     */
    private Spanned fromHtml(String html){
        return Html.fromHtml(html);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
