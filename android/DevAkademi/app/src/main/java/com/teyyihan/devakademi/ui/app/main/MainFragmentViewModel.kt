package com.teyyihan.devakademi.ui.app.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.teyyihan.devakademi.data.AdRepository
import com.teyyihan.devakademi.model.search.Content

class MainFragmentViewModel @ViewModelInject constructor(
    private val adRepository: AdRepository
): ViewModel() {


    @ExperimentalPagingApi
    fun search(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?): LiveData<PagingData<Content>>
        = adRepository.search(sellerType, title, minPrice, maxPrice)

}