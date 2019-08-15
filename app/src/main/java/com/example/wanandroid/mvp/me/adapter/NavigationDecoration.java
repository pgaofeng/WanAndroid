package com.example.wanandroid.mvp.me.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.wanandroid.R;
import com.example.wanandroid.bean.HeaderBean;
import com.example.wanandroid.util.ScreenUtils;

/**
 * @author gaofengpeng
 * @date 2019/8/13
 * @description :
 */
public class NavigationDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    /**
     * 悬浮头颜色
     */
    @ColorInt
    private int floatingColor;
    /**
     * 分割线颜色
     */
    @ColorInt
    private int divideColor;
    /**
     * text颜色
     */
    @ColorInt
    private int textColor;
    /**
     * 分割线宽
     */
    private int divideLine = 0;
    private int marginStart = 0;
    /**
     * header宽
     */
    private int head = 0;
    /**
     * 字体中心点距baseLine距离
     */
    private float fix = 0;
    private Paint mPaint;

    public NavigationDecoration(Context context) {
        this.mContext = context;
        divideLine = ScreenUtils.dp2px(mContext, 1);
        marginStart = ScreenUtils.dp2px(mContext, 30);
        head = ScreenUtils.dp2px(mContext, 40);
        mPaint = new Paint();
        mPaint.setTextSize(ScreenUtils.sp2px(mContext, 15));
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        fix = (metrics.bottom + metrics.top) / 2;

        divideColor = mContext.getResources().getColor(R.color.home_divide_gray);
        textColor = mContext.getResources().getColor(R.color.home_author_gray);
        floatingColor = mContext.getResources().getColor(R.color.flex_item_gray);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        NavigationAdapter adapter = (NavigationAdapter) parent.getAdapter();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            assert adapter != null;
            HeaderBean bean = adapter.getBean(parent.getChildAdapterPosition(view));
            Rect rect;
            if (bean.isHeader()) {
                // 绘制上方Header
                rect = new Rect(view.getLeft(), view.getTop() - head, view.getRight(), view.getTop());
                mPaint.setColor(floatingColor);
                c.drawRect(rect, mPaint);
                mPaint.setColor(textColor);
                c.drawText(bean.getHeaderName(), rect.left + marginStart, (rect.bottom + rect.top) / 2F - fix, mPaint);
            }
            // 绘制下方分割线
            if (i + 1 == parent.getChildCount() || adapter.getBean(parent.getChildAdapterPosition(parent.getChildAt(i + 1))).isHeader()) {
                // 若是最后一个或者下一个为Header元素的话，则不绘制底部分割线
                continue;
            }
            mPaint.setColor(divideColor);
            rect = new Rect(view.getLeft() + marginStart, view.getBottom(), view.getRight(), view.getBottom() + divideLine);
            c.drawRect(rect, mPaint);

        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        NavigationAdapter adapter = (NavigationAdapter) parent.getAdapter();
        assert adapter != null;
        View first = parent.getChildAt(0);
        View second = parent.getChildAt(1);

        if (first != null && second != null) {

            HeaderBean firstBean = adapter.getBean(parent.getChildAdapterPosition(first));
            HeaderBean secondBean = adapter.getBean(parent.getChildAdapterPosition(second));

            int left = first.getLeft();
            int right = second.getRight();
            int top = 0;
            int bottom = head;
            // 第一个Header被移出屏幕
            if (secondBean.isHeader() && first.getBottom() < head) {
                bottom = first.getBottom();
                top = bottom - head;
            }
            Rect rect = new Rect(left, top, right, bottom);
            mPaint.setColor(floatingColor);
            c.drawRect(rect, mPaint);
            mPaint.setColor(textColor);
            c.drawText(firstBean.getHeaderName(), rect.left + marginStart, (rect.bottom + rect.top) / 2F - fix, mPaint);
        }


    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        NavigationAdapter adapter = (NavigationAdapter) parent.getAdapter();

        if ((boolean) view.getTag()) {
            outRect.top = head;
        }
        // 最后一个item不添加分割线，下一个是Header的也不添加分割线
        int position = parent.getChildAdapterPosition(view);
        assert adapter != null;
        if (position + 1 < adapter.getItemCount() && !adapter.getBean(position + 1).isHeader()) {
            outRect.bottom = divideLine;
        }

    }
}
