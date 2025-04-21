package com.example.contacts

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomContactApi {
    @GET("https://randomuser.me/api/")

    suspend fun getRandomContact(
        @Query("results") results: Int = 10,    // Fetch 10 users
        @Query("inc") include: String = "name, email, picture, dob, id"  // Specific fields to include
    ): Response<ContactResponse>

}