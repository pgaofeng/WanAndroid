package com.gaofeng.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gaofeng.wanandroid.R
import com.gaofeng.wanandroid.util.ImageUtils

/**
 *
 * @author 高峰
 * @date 2020/12/2 16:11
 * @desc 首页Banner图
 */
class HomeBanner @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    def: Int = 0
) : FrameLayout(context, attributeSet, def) {

    private val list by lazy { mutableListOf<String>() }

    private val adapter: PagerAdapter by lazy {
        object : PagerAdapter() {
            override fun getCount() = list.size
            override fun isViewFromObject(view: View, `object`: Any) = view == `object`
            override fun getItemPosition(`object`: Any) = POSITION_NONE
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val image = ImageView(context).apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }
                container.addView(image)
                ImageUtils.loadImage(context, image, list[position])
                return image
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }
        }
    }

    fun setUrl(urls: Iterable<String>) {
        list.clear()
        list.addAll(urls)
        adapter.notifyDataSetChanged()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val view = LayoutInflater.from(context).inflate(R.layout.widget_banner, this, false)
        view.findViewById<ViewPager>(R.id.viewPager).adapter = adapter
        addView(view)
    }
}