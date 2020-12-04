package com.teyyihan.devakademi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ads")
data class AdModel(
        @Id
        val id: Long,
        val sellerID: String,
        val sellerType: String,
        val title: String,
        val description: String,
        val categories: CategoryModel,
        val price: Long,
        val date: Long,
        val expiryDate: Long,
        val live: Boolean,
        val status: String,
        val adminID: Int
)