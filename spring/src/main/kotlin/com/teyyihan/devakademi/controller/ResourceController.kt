package com.teyyihan.devakademi.controller

import com.teyyihan.devakademi.dao.AdRepository
import com.teyyihan.devakademi.util.Consts
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/resource")
class ResourceController {

    @Autowired
    private lateinit var adRepository: AdRepository


    @GetMapping("/specific")
    fun getAd(@RequestParam("adId") adID: Int) = mono {
        return@mono adRepository.findById(adID.toLong())
    }


    @GetMapping("/sellerId")
    fun getBySellerID(
            @RequestParam("id") id: String,
            @RequestParam("page") page: Int
    ) = mono {
        return@mono adRepository.findBysellerID(id, PageRequest.of(page, Consts.PAGE_SIZE))
    }

    @GetMapping("/search")
    fun bigSearch(
            @RequestParam("sellerType") sellerType: String,
            @RequestParam("title") title: String,
            @RequestParam("minPrice") minPrice: Int,
            @RequestParam("maxPrice") maxPrice: Int,
            @RequestParam("page") page: Int
    ) = mono {
        println("sellerType: $sellerType  title: $title  minPrice: $minPrice  maxPrice: $maxPrice  page: $page")
        return@mono adRepository.bigSearch(sellerType, title, minPrice, maxPrice,PageRequest.of(page,Consts.PAGE_SIZE))
    }

//    @GetMapping("/searchTitle")
//    fun searchForTitle(
//            @RequestParam("text") text: String,
//            @RequestParam("page") page: Int
//    ) = mono {
//        return@mono adRepository.findByTitleLike(text, PageRequest.of(page, Consts.PAGE_SIZE))
//    }


}