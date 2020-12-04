package com.teyyihan.devakademi.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.model.search.Content
import java.lang.Exception

class AdNetworkPagingSpring(
    private val service: RemoteAdDataSource,
    private val sellerType: String,
    private val title: String,
    private val minPrice: Int,
    private val maxPrice: Int
) : PagingSource<Int, Content>() {

    private val TAG = "teooo AdNetworkPagingSpring"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {

        val position = params.key ?: 1                                           // Get page count
        Log.d(TAG, "load: position $position")
        return try {
            val response = service.search(sellerType,title,minPrice,maxPrice,position)
            Log.d(TAG, "load: query response ")
            val ads = response.content
            LoadResult.Page(
                    data = ads,
                    prevKey = if (position == 0) null else position - 1,             // If this is the first page, prev key must be null
                    nextKey = if (response.empty) null else position + 1
            )
        }catch (e: Exception){
            Log.d(TAG, "load: sorun ne ${e.localizedMessage}")
            LoadResult.Page(emptyList(),position-1,null)
        }

    }

}