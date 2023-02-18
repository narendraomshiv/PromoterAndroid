package com.freqwency.promotr.view.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.core.content.ContextCompat
import com.freqwency.promotr.R
import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.verifyOTP.RequestOTPRequest
import com.freqwency.promotr.beans.verifyOTP.VerifyOTPRequest
import com.freqwency.promotr.beans.verifyOTP.VerifyOTPResponse
import com.freqwency.promotr.databinding.ActivityVerifyMobileBinding
import com.freqwency.promotr.services.AuthenticationAPI
import com.freqwency.promotr.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerifyMobileActivity : BaseActivity() {

    lateinit var binding: ActivityVerifyMobileBinding
    var mobileNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyMobileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalFunctions.addUIPadding(binding.root)
        if (intent.hasExtra("mobileNumber")) {
            mobileNumber = intent.getStringExtra("mobileNumber").toString()
        }

        if (intent.hasExtra("resendOTP")) {
            getOTP()
        }

        binding.llBackBtn.setOnClickListener {
            this.finish()
        }
        binding.btnVerify.setOnClickListener {
            if (binding.etOtp.text.isNullOrEmpty()) {
                binding.etOtp.background = getDrawable(R.drawable.edittext_error)
            } else {
                binding.etOtp.background = getDrawable(R.drawable.edittext)
                verifyOTP(binding.etOtp.text.toString())
            }
        }

        binding.tvResendOTP.setOnClickListener {
            getOTP()
        }
        binding.tvResendOTP.isEnabled = false
        startCounter()

    }

    private fun startCounter() {
        val countDownTimer = object : CountDownTimer(300 * 1000, 1000) {
            // override object functions here, do it quicker by setting cursor on object, then type alt + enter ; implement members
            override fun onTick(p0: Long) {
                binding.tvCounter.text = "0:${(p0 / 1000)}"
            }

            override fun onFinish() {
                binding.tvResendOTP.isEnabled = true
            }
        }
        countDownTimer.start()
    }

    private fun getOTP() {
        val resendOTPCall = AuthenticationAPI.client.getOTP(
            PromotrApp.encryptedPrefs.appLanguage,
            RequestOTPRequest(mobileNumber = mobileNumber)
        )
        resendOTPCall.enqueue(object : Callback<VerifyOTPResponse> {
            override fun onResponse(
                call: Call<VerifyOTPResponse>,
                response: Response<VerifyOTPResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                   if (response.body()?.errors == true) {
                       // todo show error
                   }
                }
            }

            override fun onFailure(call: Call<VerifyOTPResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())
                // todo show error
            }
        })
    }

    private fun verifyOTP(otp: String) {
        val verifyOTPCall = AuthenticationAPI.client.verifyOTP(
            PromotrApp.encryptedPrefs.appLanguage,
            VerifyOTPRequest(verifyOTP = otp, mobileNumber = mobileNumber)
        )
        verifyOTPCall.enqueue(object : Callback<VerifyOTPResponse> {
            override fun onResponse(
                call: Call<VerifyOTPResponse>,
                response: Response<VerifyOTPResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    PromotrApp.encryptedPrefs.isFirstTime = false
                    startActivity(Intent(this@VerifyMobileActivity, MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<VerifyOTPResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())
            }
        })
    }
}