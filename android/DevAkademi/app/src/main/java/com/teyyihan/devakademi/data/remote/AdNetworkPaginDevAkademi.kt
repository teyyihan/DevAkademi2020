package com.teyyihan.devakademi.data.remote

import android.util.Log
import androidx.paging.PagingSource
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.model.search.Content
import java.lang.Exception
import kotlin.math.log

class AdNetworkPaginDevAkademi(
    private val service: RemoteAdDataSource
) : PagingSource<Int, Content>() {

    private val TAG = "teooo AdNetworkPaginDevAkadem"
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {

        val position = params.key ?: 1                                           // Get page count

        Log.d(TAG, "load: page: $position")
        
        return try {

            val response = service.getAdsFromDevAkademi(position)
            Log.d(TAG, "load: responses $response")
            val ads = response.content
            LoadResult.Page(
                data = ads,
                prevKey = if (position == 0) null else position - 1,             // If this is the first page, prev key must be null
                nextKey = if (ads.isEmpty()) null else position + 1
            )
        }catch (e: Exception){
            Log.d(TAG, "load: what happened ${e.localizedMessage}")
            LoadResult.Page(emptyList(),position-1,null)
        }

    }

}