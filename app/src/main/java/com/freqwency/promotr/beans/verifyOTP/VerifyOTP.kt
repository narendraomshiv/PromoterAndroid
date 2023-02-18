package com.freqwency.promotr.beans.verifyOTP

import com.google.gson.annotations.SerializedName

data class VerifyOTPRequest(
    @SerializedName("otp_code" ) var verifyOTP: String,
    @SerializedName("mobile_number" )var mobileNumber: String

)

data class VerifyOTPResponse(
    @SerializedName("errors" ) var errors : Boolean,
    @SerializedName("data" ) var data : VerifyResponse
)

data class VerifyResponse(
    @SerializedName("status" ) var status : String
)

data class RequestOTPRequest(
    @SerializedName("mobile_number" )var mobileNumber: String
)