package com.teyyihan.devakademi.data.local.impl

import com.teyyihan.devakademi.data.local.abstraction.LocalAdDataSource
import com.teyyihan.devakademi.db.AdDao
import com.teyyihan.devakademi.db.MainDatabase
import com.teyyihan.devakademi.model.search.Content

class LocalAdDataSourceImpl(
    private val mainDatabase: MainDatabase
): LocalAdDataSource {
    override suspend fun insert(contents: List<Content>): Unit
        = mainDatabase.adDao().insert(contents)
}