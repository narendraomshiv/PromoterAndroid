package com.freqwency.promotr.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Log.INFO
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import com.freqwency.promotr.R
import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.GetSlidersResponse
import com.freqwency.promotr.databinding.ActivitySplashBinding
import com.freqwency.promotr.services.SlidersAPI
import com.freqwency.promotr.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Level.INFO

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    var slidersList: ArrayList<GetSlidersResponse.SliderBean>? = null
    lateinit var splashAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PromotrApp.encryptedPrefs.appLanguage = "en"
        if (PromotrApp.encryptedPrefs.deviceID.isEmpty()) {
            PromotrApp.encryptedPrefs.deviceID = GlobalFunctions.getDeviceID()
        }

        val splashBinding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        splashAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        splashBinding.ivLogo.animation = splashAnimation

        splashAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                Log.d("+++++", PromotrApp.encryptedPrefs.isFirstTime.toString())
                if (PromotrApp.encryptedPrefs.isFirstTime) {
                    // get Sliders
                    print("+++++ call+++")
                    getSliders()
                    splashAnimation.repeatMode = 1
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }, 1000)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
    }

    fun getSliders() {
        Log.d("+++++", "API CALL")

        val getSlidersCall = SlidersAPI.client.getSliders()
        getSlidersCall.enqueue(object : Callback<GetSlidersResponse> {
            override fun onResponse(
                call: Call<GetSlidersResponse>,
                response: Response<GetSlidersResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    slidersList = response.body()?.data?.appSliders
                    val intent = Intent(this@SplashActivity, IntroActivity::class.java)
                    intent.putExtra("sliders", slidersList)
                    startActivity(intent)
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<GetSlidersResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())
                slidersList = null
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        })
    }
}