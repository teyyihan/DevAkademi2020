package com.teyyihan.devakademi.ui.app.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.devakademi.databinding.ListItemAdBinding
import com.teyyihan.devakademi.model.search.Content

class MainFragmentAdapter: PagingDataAdapter<Content,MainFragmentViewHolder>(COMPARATOR) {

    lateinit var adClickListener: AdClickListener

    /**
     *  Interface - Listener mechanism to pass value and view to MainFragment
     *  and navigate via Navigation Component there.
     */
    interface AdClickListener {
        fun onAdClicked(adModel: Content)
    }

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<Content>(){
            override fun areItemsTheSame(oldItem: Content, newItem: Content) = true
            override fun areContentsTheSame(oldItem: Content, newItem: Content) = true
        }
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        val ad = getItem(position)
        ad?.let {
            holder.bind(it)
        }

        holder.root.setOnClickListener {
            ad?.let {
                adClickListener.onAdClicked(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        return MainFragmentViewHolder.create(parent)
    }
}


class MainFragmentViewHolder(private val binding: ListItemAdBinding): RecyclerView.ViewHolder(binding.root) {

    val root = binding.root

    fun bind(ad: Content) {
        binding.title.text = ad.title
        binding.body.text = ad.description
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): MainFragmentViewHolder {
            val view = ListItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MainFragmentViewHolder(view)
        }
    }
}