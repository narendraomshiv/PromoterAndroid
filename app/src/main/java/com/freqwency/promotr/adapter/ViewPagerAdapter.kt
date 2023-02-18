package com.freqwency.promotr.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.freqwency.promotr.R
import com.freqwency.promotr.beans.GetSlidersResponse


class ViewPagerAdapter(
    private val activity: Activity,
    private var sliders: ArrayList<GetSlidersResponse.SliderBean>,
    private var inflatedLayout: Int,
    txtView: Int,
    subTitleTextView: Int
) : PagerAdapter() {

    private var mLayoutInflater: LayoutInflater =
        activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var textView: TextView = activity.findViewById(txtView)
    private var subTitleText: TextView = activity.findViewById(subTitleTextView)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(inflatedLayout, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val url = sliders[position].image_url
        Glide
            .with(activity)
            //.load(R.drawable.slider1)
            .load(url)
//            .placeholder(R.drawable.loading_spinner)
            .into(imageView)

        textView.text = sliders[position].title
        subTitleText.text = sliders[position].subtitle

        Log.e(
            "Timing", "  Size:  " + sliders.get(position).title
        )

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

    override fun getCount(): Int {
        return sliders.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`

    }
}
