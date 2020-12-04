package com.teyyihan.devakademi.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.teyyihan.devakademi.data.local.abstraction.LocalAdDataSource
import com.teyyihan.devakademi.data.remote.AdNetworkPaginDevAkademi
import com.teyyihan.devakademi.data.remote.AdNetworkPagingSpring
import com.teyyihan.devakademi.data.remote.AdRemoteMediatorDevAkademi
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.db.MainDatabase
import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.util.Consts
import javax.inject.Inject

class AdRepository @Inject constructor(
    private val remoteAdDataSource: RemoteAdDataSource,
    private val mainDatabase: MainDatabase,
    private val localAdDataSource: LocalAdDataSource
){

    private val TAG = "teooo AdRepository"

//    fun search(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?): LiveData<PagingData<Content>> {
//        if(sellerType==null || title==null || minPrice==null || maxPrice==null){
//            Log.d(TAG, "search: null search")
//            return Pager(
//                config = PagingConfig(pageSize = Consts.PAGE_SIZE),
//                pagingSourceFactory = { AdNetworkPaginDevAkademi(remoteAdDataSource) }
//            ).liveData
//        }
//
//        Log.d(TAG, "search: query search")
//        return Pager(
//            config = PagingConfig(pageSize = Consts.PAGE_SIZE),
//            pagingSourceFactory = { AdNetworkPagingSpring(remoteAdDataSource, sellerType,title,minPrice, maxPrice) }
//        ).liveData
//    }

    @ExperimentalPagingApi
    fun search(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?): LiveData<PagingData<Content>> {
        if(sellerType==null || title==null || minPrice==null || maxPrice==null){
            Log.d(TAG, "search: null search")
            return Pager(
                config = PagingConfig(pageSize = Consts.PAGE_SIZE),
                remoteMediator = AdRemoteMediatorDevAkademi(
                    remoteAdDataSource,
                    localAdDataSource
                ),
                pagingSourceFactory = { mainDatabase.adDao().getPaging() }
            ).liveData
        }

        Log.d(TAG, "search: query search")
        return Pager(
            config = PagingConfig(pageSize = Consts.PAGE_SIZE),
            pagingSourceFactory = { AdNetworkPagingSpring(remoteAdDataSource, sellerType,title,minPrice, maxPrice) }
        ).liveData
    }

}