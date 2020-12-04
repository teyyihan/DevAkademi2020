package com.teyyihan.devakademi.data.remote.impl

import com.teyyihan.devakademi.data.remote.DevAkademiAPI
import com.teyyihan.devakademi.data.remote.SpringAPI
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.model.search.SearchResponse

class RemoteAdDataSourceImpl(
    private val springAPI: SpringAPI,
    private val devAkademiAPI: DevAkademiAPI
): RemoteAdDataSource {
    override suspend fun search(
        sellerType: String,
        title: String,
        minPrice: Int,
        maxPrice: Int,
        page: Int
    ) = springAPI.search(sellerType,title,minPrice,maxPrice,page)


    override suspend fun getAdsFromDevAkademi(page: Int): SearchResponse
        = devAkademiAPI.getAds(page)
}