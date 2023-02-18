package com.freqwency.promotr.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import com.freqwency.promotr.R
import com.freqwency.promotr.adapter.ViewPagerAdapter
import com.freqwency.promotr.beans.GetSlidersResponse
import com.freqwency.promotr.databinding.ActivityIntroBinding
import com.freqwency.promotr.utils.GlobalFunctions

class IntroActivity : BaseActivity(), View.OnClickListener {

    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val introBinding: ActivityIntroBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(introBinding.root)

        activity = this

        GlobalFunctions.addUIPadding(introBinding.llBackground)
        GlobalFunctions.addBottomMargin(introBinding.llBottomView)
        val sliders = intent.getSerializableExtra("sliders") as ArrayList<GetSlidersResponse.SliderBean>
       // val slidersss = R.drawable.slider1 as ArrayList<String>

        val viewPagerAdapter = ViewPagerAdapter(activity,
            sliders, R.layout.pager_item, R.id.tv_slider, R.id.tv_subtitle)
        introBinding.viewpager.adapter = viewPagerAdapter
        introBinding.indicator.setViewPager(introBinding.viewpager)

        introBinding.btnSignIn.setOnClickListener(this)
        introBinding.btnSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_signIn -> {
                val intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
            }

            R.id.btn_signUp -> {
                val intent = Intent(activity, RegisterActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }
}