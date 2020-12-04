package com.teyyihan.devakademi.model.search

import com.teyyihan.devakademi.model.search.Sort

data class Pageable(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: Sort,
    val unpaged: Boolean
)