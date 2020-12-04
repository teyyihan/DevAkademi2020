package com.teyyihan.devakademi.model.search

import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.model.search.Pageable
import com.teyyihan.devakademi.model.search.SortX

data class SearchResponse(
    val content: List<Content>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: SortX,
    val totalElements: Int,
    val totalPages: Int
)