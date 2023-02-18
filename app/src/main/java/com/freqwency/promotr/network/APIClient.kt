package com.freqwency.promotr.network

import com.freqwency.promotr.BuildConfig
import com.freqwency.promotr.application.PromotrApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class APIClient {
    private var globalOKHttpClientBuilder: OkHttpClient.Builder? = null

    private fun getClient(): Retrofit {
        globalOKHttpClientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = if (BuildConfig.IS_DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }
        globalOKHttpClientBuilder!!.addInterceptor(interceptor)
//        try {
//            // Create a trust manager that does not validate certificate chains
//            val trustAllCerts = arrayOf<TrustManager>(TrustManager())
//
//            // Install the all-trusting trust manager
//            val sslContext = SSLContext.getInstance("SSL")
//            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
//
//            // Create an ssl socket factory with our all-trusting manager
//            val sslSocketFactory = sslContext.socketFactory
//
//            globalOKHttpClientBuilder1!!.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//            val hostnameVerifier = HostnameVerifier { _, _ -> true }
//            globalOKHttpClientBuilder1!!.hostnameVerifier(hostnameVerifier)
//        } catch (e: Exception) {
//            GlobalFunctions.printException(e)
//        }

        globalOKHttpClientBuilder!!.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", PromotrApp.encryptedPrefs.token)
                .build()

            chain.proceed(request)
        }

        val client = globalOKHttpClientBuilder!!.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    val retrofitClient: Retrofit
        get() {
            return APIClient().getClient()
        }

    fun <T> parseError(response: Response<T>, type: Type): T? {
        val converter = retrofitClient.responseBodyConverter<T>(type, arrayOfNulls(0))
        val error: T?
        try {
            error = converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            return null
        }

        return error
    }

}