package com.teyyihan.devakademi.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teyyihan.devakademi.model.search.Content

@Dao
interface AdDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contents: List<Content>)

    @Query("SELECT * FROM ads")
    fun getPaging(): PagingSource<Int,Content>

}