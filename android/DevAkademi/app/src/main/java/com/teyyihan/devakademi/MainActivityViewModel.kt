package com.teyyihan.devakademi

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.teyyihan.devakademi.data.AdRepository
import com.teyyihan.devakademi.model.QueryModel
import com.teyyihan.devakademi.model.search.Content

@ExperimentalPagingApi
class MainActivityViewModel @ViewModelInject constructor(
    private val adRepository: AdRepository
): ViewModel() {

    val query = MutableLiveData<QueryModel>()

    init {
        search(null,null,null,null)
    }

    private var currentQueryValue: QueryModel? = null

    var currentSearchResult: LiveData<PagingData<Content>>? = null


    @ExperimentalPagingApi
    fun search(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?) {
        val _query = QueryModel(sellerType, title, minPrice, maxPrice)

        val lastResult = currentSearchResult

        // If the query is the same, just send the old result
        if (_query == currentQueryValue && lastResult != null) {
            // Nothing
        }else{
            currentQueryValue = _query
            val newResult = adRepository.search(sellerType, title, minPrice, maxPrice)
                // Cached in viewmodel. When viewmodel dies, data will be lost
                .cachedIn(viewModelScope)

            currentSearchResult = newResult
        }

    }

}