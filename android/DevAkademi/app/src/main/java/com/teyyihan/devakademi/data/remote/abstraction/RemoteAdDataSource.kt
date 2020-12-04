package com.teyyihan.devakademi.data.remote.abstraction


import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.model.search.SearchResponse

interface RemoteAdDataSource {

    suspend fun search(sellerType: String, title: String, minPrice: Int, maxPrice: Int, page: Int): SearchResponse

    suspend fun getAdsFromDevAkademi(page: Int): SearchResponse

}