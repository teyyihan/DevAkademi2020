package com.teyyihan.devakademi.ui.app.ad_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.teyyihan.devakademi.databinding.FragmentAdDetailBinding
import com.teyyihan.devakademi.model.search.Categories
import com.teyyihan.devakademi.model.search.Content
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class AdDetailFragment : Fragment() {

    private lateinit var binding: FragmentAdDetailBinding
    private val args: AdDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdDetailBinding.inflate(inflater, container, false)

        val ad: Content? = args.ad?.let { Json.decodeFromString(it) }

        ad?.let {
            bindAd(it)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun bindAd(ad: Content) {
        binding.category.text = categoryText(ad.categories)
        binding.description.text = ad.description
        binding.price.text = ad.price.toString()+" br"
        binding.title.text = ad.title
    }

    private fun categoryText(categories: Categories): String? {
        var catText = ""
        categories.category0.title?.let {
            catText += it
        }
        categories.category1.title?.let {
            catText += ">$it"
        }
        categories.category2.title?.let {
            catText += ">$it"
        }
        categories.category3.title?.let {
            catText += ">$it"
        }
        categories.category4.title?.let {
            catText += ">$it"
        }
        categories.category5.title?.let {
            catText += ">$it"
        }
        categories.category6.title?.let {
            catText += ">$it"
        }
        return catText
    }


}