package com.teyyihan.devakademi.ui.app.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.teyyihan.devakademi.MainActivityViewModel
import com.teyyihan.devakademi.R
import com.teyyihan.devakademi.databinding.FragmentMainBinding
import com.teyyihan.devakademi.model.search.Content
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG = "teooo MainFragment"

    @ExperimentalPagingApi
    private val viewModel by activityViewModels<MainActivityViewModel>()
    private lateinit var adapter: MainFragmentAdapter
    private lateinit var binding: FragmentMainBinding
    private lateinit var bottomSheet : QueryBottomSheet

    private var searchJob: Job? = null

//    private fun search(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?) {
//        // Make sure we cancel the previous job before creating a new one
//        searchJob?.cancel()
//        searchJob = lifecycleScope.launch {
//            viewModel.search(sellerType,title,minPrice,maxPrice).observe(viewLifecycleOwner, {
//                adapter.submitData(lifecycle,it)
//
//
//            })
//        }
//    }

    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        bottomSheet = QueryBottomSheet()

        adapter = MainFragmentAdapter()
        adapter.adClickListener = adlistener
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        binding.fab.setOnClickListener {
            bottomSheet.show(parentFragmentManager,"TTT")
        }

        viewModel.currentSearchResult?.observe(viewLifecycleOwner){
            lifecycleScope.launch {
                adapter.submitData(it)
                delay(300)
                binding.list.scrollToPosition(0)
            }

        }

        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.query.observe(viewLifecycleOwner){
            Log.d(TAG, "onCreateView: geldi mi")
            viewModel.search(it.sellerType,it.title,it.minPrice,it.maxPrice)
        }
    }


    val adlistener = object : MainFragmentAdapter.AdClickListener{
        override fun onAdClicked(adModel: Content) {
            Log.d(TAG, "onAdClicked: CLICKED")
            val action = MainFragmentDirections.actionMainFragmentToAdDetailFragment2(Json.encodeToString(adModel))
            findNavController().navigate(action)
//            findNavController().navigate(R.id.action_mainFragment_to_adDetailFragment2)
        }

    }

}