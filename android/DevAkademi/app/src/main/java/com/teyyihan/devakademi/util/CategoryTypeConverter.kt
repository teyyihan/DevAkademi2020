package com.teyyihan.devakademi.util

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.teyyihan.devakademi.model.search.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.security.PrivateKey
import java.security.PublicKey

@TypeConverters
class CategoryTypeConverter {


    @TypeConverter
    fun mainCat(str: String): Categories? = Json.decodeFromString(str)
    @TypeConverter
    fun mainCat1(pk: Categories): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat0(str: String): Category0? = Json.decodeFromString(str)
    @TypeConverter
    fun cat00(pk: Category0): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat1(str: String): Category1? = Json.decodeFromString(str)
    @TypeConverter
    fun cat11(pk: Category1): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat2(str: String): Category2? = Json.decodeFromString(str)
    @TypeConverter
    fun cat22(pk: Category2): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat3(str: String): Category3? = Json.decodeFromString(str)
    @TypeConverter
    fun cat33(pk: Category3): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat4(str: String): Category4? = Json.decodeFromString(str)
    @TypeConverter
    fun cat44(pk: Category4): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat5(str: String): Category5? = Json.decodeFromString(str)
    @TypeConverter
    fun cat55(pk: Category5): String? = Json.encodeToString(pk)


    @TypeConverter
    fun cat6(str: String): Category6? = Json.decodeFromString(str)
    @TypeConverter
    fun cat66(pk: Category6): String? = Json.encodeToString(pk)

}