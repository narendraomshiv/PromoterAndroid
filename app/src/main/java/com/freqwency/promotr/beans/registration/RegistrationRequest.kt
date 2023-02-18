package com.freqwency.promotr.beans.registration

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("mobile_number") var mobileNumber: String,
    @SerializedName("country") var country: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("email") val email: String = "",
    @SerializedName("gender") var gender: String = "",
    @SerializedName("date_of_birth") val dateOfBirth: String = "",
    @SerializedName("password") var password: String,
    @SerializedName("password_confirmation") var passwordConfirmation: String,
    @SerializedName("device_name") val deviceName: String = "",
    @SerializedName("has_accepted_terms") val hasAcceptedTerms: Boolean
)