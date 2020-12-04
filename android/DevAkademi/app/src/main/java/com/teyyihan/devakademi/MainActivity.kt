package com.teyyihan.devakademi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.paging.ExperimentalPagingApi
import com.teyyihan.devakademi.model.QueryModel
import com.teyyihan.devakademi.ui.app.main.QueryBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),  QueryBottomSheet.BottomSheetListener {

    @ExperimentalPagingApi
    private val viewModel by viewModels<MainActivityViewModel>()
    private val TAG = "teooo MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ExperimentalPagingApi
    override fun queryButtonClicked(
        sellerType: String?,
        title: String?,
        minPrice: Int?,
        maxPrice: Int?
    ) {
        Log.d(TAG, "queryButtonClicked: tetikleniyo mu")
        viewModel.query.postValue(QueryModel(sellerType, title, minPrice, maxPrice))
    }
}