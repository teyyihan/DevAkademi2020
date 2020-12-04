package com.teyyihan.devakademi.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.teyyihan.devakademi.data.local.abstraction.LocalAdDataSource
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.model.search.Content
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

@ExperimentalPagingApi
class AdRemoteMediatorDevAkademi(
    private val remoteService: RemoteAdDataSource,
    private val localService: LocalAdDataSource
): RemoteMediator<Int, Content>() {

    private val TAG = "teooo AdRemoteMediatorDevAkad"

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Content>
    ): MediatorResult {
        /**
         *  Calculating next (append), previous (prepend) and initial(refresh) keys
         */
        val page: Int? = when (loadType) {

            LoadType.REFRESH -> {
                // initial key
                1
            }
            LoadType.PREPEND -> {
                // Prev key
                Log.d(TAG, "load: 2222222222")
                null
            }
            LoadType.APPEND -> {
                // Next key
                Log.d(TAG, "load: 33333333333")
                state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.nextKey

            }
        }



        if (page != null) {
            try {
                val apiResponse = remoteService.getAdsFromDevAkademi(page)
                val ads = apiResponse.content
                val endOfPaginationReached = ads.isEmpty()


                Log.d(TAG, "load: PAGEEE $page")

                /**
                 *  Set prev and next keys into AdModels. So that we can access them with O(1) time complexity.
                 *  Paging 3.0 Codelab stores them into another Room entity.
                 */
                ads.map {
                    it.prevKey = null
                    it.nextKey = apiResponse.pageable.pageNumber+1
                }

                Log.d(TAG, "load: BUNA BAK $ads")

                // Insert Characters
                localService.insert(ads)
                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } catch (exception: Exception) {
                Log.d(TAG, "load: WHY2 ${exception.localizedMessage}")
                return MediatorResult.Error(exception)
            }
        } else {
            Log.d(TAG, "load: WHY3 ")
            return MediatorResult.Success(false)
        }

    }
}