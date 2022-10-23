package com.mustafa.artbookhilttesting.api

import com.mustafa.artbookhilttesting.model.ImageModel
import com.mustafa.artbookhilttesting.util.Constans
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageAPI {

    @GET("api/")
    suspend fun getData(
        @Query("q",encoded = true) searchQuery:String,
        @Query("key") key:String = Constans.API_KEY,
    ):ImageModel

}