package com.teyyihan.devakademi.data.remote

import com.teyyihan.devakademi.model.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpringAPI {

    @GET("/resource/search")
    suspend fun search(
        @Query("sellerType") sellerType: String,
        @Query("title") title: String,
        @Query("minPrice") minPrice: Int,
        @Query("maxPrice") maxPrice: Int,
        @Query("page") page: Int
    ) : SearchResponse

}