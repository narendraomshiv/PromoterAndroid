package com.freqwency.promotr.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.freqwency.promotr.R
import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.LoginRequest
import com.freqwency.promotr.beans.registration.RegistrationResponse
import com.freqwency.promotr.databinding.ActivityLoginBinding
import com.freqwency.promotr.services.AuthenticationAPI
import com.freqwency.promotr.utils.GlobalFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var activity: Activity
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        activity = this
        GlobalFunctions.addUIPadding(loginBinding.rlMainView)
        loginBinding.btnSignIn.setOnClickListener(this)
        loginBinding.tvResetPass.setOnClickListener(this)

        with(loginBinding) {
            llBackBtn.setOnClickListener {
                finish()
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_signIn -> {
                loginCall()
            }
            R.id.tv_resetPass -> {
                val intent = Intent(activity, ResetPasswordActivity::class.java)
                if (loginBinding.etEmail.text.isNotEmpty()) {
                    intent.putExtra("email", loginBinding.etEmail.text.toString())
                }
                activity.startActivity(intent)
            }
        }
    }

    private fun loginCall() {
        if (loginBinding.etEmail.text.isNullOrEmpty()) {
            loginBinding.etEmail.background = getDrawable(R.drawable.edittext_error)
        } else {
            loginBinding.etEmail.background = getDrawable(R.drawable.edittext)
        }

        if (loginBinding.etPassword.text.isNullOrEmpty()) {
            loginBinding.etPassword.background = getDrawable(R.drawable.edittext_error)
        } else {
            loginBinding.etPassword.background = getDrawable(R.drawable.edittext)
        }

        val request = LoginRequest(
            email = loginBinding.etEmail.text.toString(),
            password = loginBinding.etPassword.text.toString(),
            deviceName = PromotrApp.encryptedPrefs.deviceID
        )

        val loginCall =
            AuthenticationAPI.client.login(PromotrApp.encryptedPrefs.appLanguage, request)
        loginCall.enqueue(object : Callback<RegistrationResponse> {
            override fun onResponse(
                call: Call<RegistrationResponse>,
                response: Response<RegistrationResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    PromotrApp.encryptedPrefs.token = response.body()!!.data?.accessToken ?: ""
                    PromotrApp.encryptedPrefs.user = response.body()!!.data?.user
                    PromotrApp.encryptedPrefs.isFirstTime = false

                    if (response.body()!!.data?.user?.mobileNumberVerified == true) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        val intent = Intent(this@LoginActivity, VerifyMobileActivity::class.java)
                        intent.putExtra("resendOTP", true)
                        intent.putExtra("mobileNumber", response.body()!!.data?.user?.mobileNumber ?: "")
                        startActivity(intent)
                        finish()
                    }
                }
            }

            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                Log.d("+++++", t.message.toString())
            }
        })
    }
}