package com.teyyihan.devakademi.db

import android.content.Context
import androidx.room.*
import com.teyyihan.devakademi.model.search.Content
import com.teyyihan.devakademi.util.CategoryTypeConverter
import com.teyyihan.devakademi.util.Consts

@Database(entities = [Content::class],version = 1, exportSchema = false)
@TypeConverters(CategoryTypeConverter::class)
abstract class MainDatabase: RoomDatabase(){

    abstract fun adDao(): AdDao

    /**
     *  Will be called from ApplicationModule DI
     */
    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MainDatabase::class.java, Consts.DATABASE_NAME)
                .build()
    }

}