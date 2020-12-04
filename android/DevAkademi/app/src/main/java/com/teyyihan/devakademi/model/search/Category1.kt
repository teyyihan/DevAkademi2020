package com.teyyihan.devakademi.model.search

import kotlinx.serialization.Serializable

@Serializable
data class Category1(
    val categoryID: Int,
    val title: String?
)