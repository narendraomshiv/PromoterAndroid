package com.freqwency.promotr.services

import com.freqwency.promotr.beans.GetSlidersResponse
import com.freqwency.promotr.network.APIClient
import retrofit2.Call
import retrofit2.http.GET

object SlidersAPI {

    val client: APISlidersEndpointsInterface
        get() = APIClient().retrofitClient.create(APISlidersEndpointsInterface::class.java)

    interface APISlidersEndpointsInterface {
        @GET("public/app-sliders")
        fun getSliders(): Call<GetSlidersResponse>

    }
}