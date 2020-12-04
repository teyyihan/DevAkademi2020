package com.teyyihan.devakademi.data.local.abstraction

import com.teyyihan.devakademi.model.search.Content

interface LocalAdDataSource {

    suspend fun insert(contents: List<Content>)

}