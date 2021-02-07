package com.example.apidog.model.dao

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDogClient {
    companion object{
        private const val URL_BASE = "https://dog.ceo/api/"
        fun getApiDogClient(): IApiDog {
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(IApiDog::class.java)
        }
    }
}