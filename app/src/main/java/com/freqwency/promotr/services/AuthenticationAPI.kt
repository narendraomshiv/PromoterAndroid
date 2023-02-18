package com.freqwency.promotr.services

import com.freqwency.promotr.application.PromotrApp
import com.freqwency.promotr.beans.LoginRequest
import com.freqwency.promotr.beans.ResetPasswordRequest
import com.freqwency.promotr.beans.ResetPasswordResponse
import com.freqwency.promotr.beans.registration.RegistrationRequest
import com.freqwency.promotr.beans.registration.RegistrationResponse
import com.freqwency.promotr.beans.verifyOTP.RequestOTPRequest
import com.freqwency.promotr.beans.verifyOTP.VerifyOTPRequest
import com.freqwency.promotr.beans.verifyOTP.VerifyOTPResponse
import com.freqwency.promotr.network.APIClient
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

object AuthenticationAPI {

    val client: APISlidersEndpointsInterface
        get() = APIClient().retrofitClient.create(APISlidersEndpointsInterface::class.java)

    interface APISlidersEndpointsInterface {
        @POST("auth/register")
        fun register(@Query("locale") locale: String = PromotrApp.encryptedPrefs.appLanguage, @Body body: RegistrationRequest): Call<RegistrationResponse>

        @POST("auth/otp-verifications")
        fun verifyOTP(@Query("locale") locale: String = PromotrApp.encryptedPrefs.appLanguage, @Body body: VerifyOTPRequest): Call<VerifyOTPResponse>

        @POST("auth/otps")
        fun getOTP(@Query("locale") locale: String = PromotrApp.encryptedPrefs.appLanguage, @Body body: RequestOTPRequest): Call<VerifyOTPResponse>

        @POST("auth/reset-password")
        fun resetPassword(@Query("locale") locale: String = PromotrApp.encryptedPrefs.appLanguage, @Body body: ResetPasswordRequest): Call<ResetPasswordResponse>

        @POST("auth/login")
        fun login(@Query("locale") locale: String = PromotrApp.encryptedPrefs.appLanguage, @Body body: LoginRequest): Call<RegistrationResponse>

    }
}