package com.teyyihan.devakademi.model

import kotlinx.serialization.Serializable


@Serializable
data class CategoryModel(
        val category0: SubCategoryModel,
        val category1: SubCategoryModel,
        val category2: SubCategoryModel,
        val category3: SubCategoryModel,
        val category4: SubCategoryModel,
        val category5: SubCategoryModel,
        val category6: SubCategoryModel
)

@Serializable
data class SubCategoryModel(
        val categoryID: Int?,
        val title: String?
)