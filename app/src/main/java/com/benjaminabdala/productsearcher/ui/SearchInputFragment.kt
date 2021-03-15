package com.benjaminabdala.productsearcher.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.benjaminabdala.productsearcher.R
import com.benjaminabdala.productsearcher.databinding.FragmentSearchInputBinding
import com.benjaminabdala.productsearcher.util.Constants.EMPTY_STRING

class SearchInputFragment : Fragment() {

    private lateinit var binding: FragmentSearchInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchInputBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        binding.searchInputEtProduct.text.clear()
    }

    private fun setOnClickListener() {
        with(binding) {
            searchInputBtnSearch.setOnClickListener {
                if (searchInputEtProduct.text.isNotBlank()) {
                    (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                        searchInputBtnSearch.windowToken,
                        0
                    )
                    findNavController().navigate(
                        R.id.navigate_to_products_found_fragment,
                        Bundle().apply {
                            putString(PRODUCT_INPUT, searchInputEtProduct.text.toString())
                        })
                } else {
                    searchInputEtProduct.requestFocus()
                    Toast.makeText(
                        context,
                        getString(R.string.error_empty_search_input_field),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val PRODUCT_INPUT = "productInput"
    }
}
