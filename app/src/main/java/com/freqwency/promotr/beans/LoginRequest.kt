package com.freqwency.promotr.beans

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email"       ) var email      : String? = null,
    @SerializedName("password"    ) var password   : String? = null,
    @SerializedName("device_name" ) var deviceName : String? = null
)