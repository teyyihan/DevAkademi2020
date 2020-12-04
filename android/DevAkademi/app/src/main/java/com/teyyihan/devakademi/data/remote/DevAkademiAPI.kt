package com.teyyihan.devakademi.data.remote


import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.model.search.SearchResponse
import com.teyyihan.devakademi.util.Consts
import retrofit2.http.GET
import retrofit2.http.Query

interface DevAkademiAPI {

    @GET("/api/classified/list")
    suspend fun getAds(
        @Query("page") page: Int,
        @Query("size") size: Int = Consts.PAGE_SIZE
    ): SearchResponse
}