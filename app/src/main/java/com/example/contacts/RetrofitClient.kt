package com.example.contacts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class to handle the Retrofit client creation
object RetrofitClient {
    private const val BASE_URL = "https://randomuser.me/api/"

    fun create(): RandomContactApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomContactApi::class.java)
    }
}
