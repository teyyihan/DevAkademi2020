package com.teyyihan.devakademi.model.search

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.teyyihan.devakademi.model.search.Categories
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ads")
data class Content(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val _id: Int,
    val adminID: Int,
    val categories: Categories,
    val date: Int,
    val description: String,
    val expiryDate: Int,
    val live: Boolean,
    val price: Int,
    val sellerID: String,
    val sellerType: String,
    val status: String,
    val title: String,
    var prevKey: Int?,
    var nextKey: Int?
)