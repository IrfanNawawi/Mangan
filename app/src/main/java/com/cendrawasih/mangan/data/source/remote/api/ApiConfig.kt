package com.cendrawasih.mangan.data.source.remote.api

import androidx.viewbinding.BuildConfig
import com.cendrawasih.mangan.BuildConfig.BASE_URL_THEMEALDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_THEMEALDB)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}