package com.freqwency.promotr.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.freqwency.promotr.R
import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.ResetPasswordRequest
import com.freqwency.promotr.beans.ResetPasswordResponse
import com.freqwency.promotr.beans.verifyOTP.VerifyOTPResponse
import com.freqwency.promotr.databinding.ActivityResetPasswordBinding
import com.freqwency.promotr.databinding.ActivityVerifyMobileBinding
import com.freqwency.promotr.services.AuthenticationAPI
import com.freqwency.promotr.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalFunctions.addUIPadding(binding.root)
        binding.llBackBtn.setOnClickListener {
            this.finish()
        }

        if (intent.hasExtra("email")) {
            binding.etEmail.setText(intent.getStringExtra("email"))
        }

        binding.btnVerify.setOnClickListener {
            if (binding.etEmail.text.isNullOrEmpty()) {
                binding.etEmail.background =  getDrawable(R.drawable.edittext_error)
            } else {
                binding.etEmail.background =  getDrawable(R.drawable.edittext)
                resetPassword()
            }
        }
    }

    private fun resetPassword() {
        val resetPassCall = AuthenticationAPI.client.resetPassword(
            PromotrApp.encryptedPrefs.appLanguage,
            ResetPasswordRequest(email = binding.etEmail.text.toString()
            )
        )
        resetPassCall.enqueue(object : Callback<ResetPasswordResponse> {
            override fun onResponse(
                call: Call<ResetPasswordResponse>,
                response: Response<ResetPasswordResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    startActivity(Intent(this@ResetPasswordActivity, LoginActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())
                startActivity(Intent(this@ResetPasswordActivity, MainActivity::class.java))
                finish()
            }
        })
    }
}