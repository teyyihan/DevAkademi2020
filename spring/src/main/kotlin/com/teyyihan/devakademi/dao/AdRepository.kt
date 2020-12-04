package com.teyyihan.devakademi.dao

import com.teyyihan.devakademi.model.AdModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.CrudRepository

interface AdRepository: CrudRepository<AdModel, Long> {

    @Query(value = "{'title': {${'$'}regex : ?0, ${'$'}options: 'i'}}")
    fun findByTitleLike(title: String, pageable: Pageable): Page<AdModel>

    fun findBysellerID(sellerID: String, pageable: Pageable): Page<AdModel>

    @Query(value = "{'sellerType' : ?0," +
            " 'title': {${'$'}regex : ?1, ${'$'}options: 'i'}," +
            " 'price': {${'$'}gt: ?2, ${'$'}lt: ?3 } }")
    //{"age" : {"$gt" : from, "$lt" : to}}
    //{ age : { $lt : 50 }, accounts.balance : { $gt : 1000.00 }}
    fun bigSearch(sellerType: String, title: String, minPrice: Int, maxPrice: Int, pageable: Pageable): Page<AdModel>

}