package com.example.safepocket.api

import com.example.safepocket.utlis.Constants.Companion.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        private var INSTANCE: Retrofit? = null
        fun getInstance(): Retrofit? {
            if (INSTANCE == null)
                INSTANCE = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return INSTANCE
        }
    }
}