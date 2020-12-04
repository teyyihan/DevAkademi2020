package com.teyyihan.devakademi.ui.app.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teyyihan.devakademi.R
import com.teyyihan.devakademi.databinding.BottomSheetQueryBinding

class QueryBottomSheet: BottomSheetDialogFragment() {

    private val TAG = "teooo QueryBottomSheet"

    private lateinit var binding: BottomSheetQueryBinding
    var mListener: BottomSheetListener? = null

    interface BottomSheetListener{
        fun queryButtonClicked(sellerType: String?, title: String?, minPrice: Int?, maxPrice: Int?)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetQueryBinding.inflate(inflater,container,false)

        context?.let {
            ArrayAdapter.createFromResource(it, R.array.seller_type_spinner, android.R.layout.simple_spinner_item).also { adapter ->

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.sellerTypeSpinner.adapter = adapter
            }
        }

        binding.button.setOnClickListener {
            val sellerType = findSellerType(binding.sellerTypeSpinner.selectedItemId)
            val titleInput = binding.titleInput.text.toString()
            val minPriceInput = try {
                binding.minPriceInput.text.toString().toInt()
            }catch (e: Exception){
                Log.d(TAG, "onCreateView: burası mı 1")
                null
            }
            val maxPriceInput = try {
                binding.maxPriceInput.text.toString().toInt()
            }catch (e: Exception){
                Log.d(TAG, "onCreateView: burası mı2")
                null
            }

            if(titleInput.isBlank() || titleInput.isEmpty() || minPriceInput == null || maxPriceInput == null ){
                Toast.makeText(context, "Please fill the gaps", Toast.LENGTH_SHORT).show()
            }else{
                mListener?.queryButtonClicked(sellerType,titleInput,minPriceInput,maxPriceInput)
                // after clicking search button, close bottom sheet
                dismiss()
            }
        }

        return binding.root
    }

    private fun findSellerType(selectedItemId: Long): String {
        return when(selectedItemId){
            0L -> "CORPORATE"
            else -> "INDIVIDUAL"
        }
    }

    // Attach main activity to search button listener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as BottomSheetListener
    }

}