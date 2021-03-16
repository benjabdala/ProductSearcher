package com.benjaminabdala.productsearcher.ui.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.benjaminabdala.productsearcher.R
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.databinding.FragmentSearchInputBinding
import com.benjaminabdala.productsearcher.ui.adapter.OnProductCardClicked
import com.benjaminabdala.productsearcher.ui.adapter.ProductsFoundRecyclerViewAdapter
import com.benjaminabdala.productsearcher.util.Constants.PERMALINK
import com.benjaminabdala.productsearcher.viewmodel.HistoryOfProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchInputFragment : Fragment(), OnProductCardClicked {

    private lateinit var binding: FragmentSearchInputBinding
    private val historyOfProductsViewModel by viewModel<HistoryOfProductsViewModel>()

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
        historyOfProductsViewModel.historyOfProductsLiveData.observe(::getLifecycle, ::showHistoryOfProductsClicked)
        historyOfProductsViewModel.getHistoryOfProductsFromDatabase()
    }

    override fun onResume() {
        super.onResume()
        binding.searchInputEtProduct.text.clear()
    }

    private fun showHistoryOfProductsClicked(historyOfProducts: List<Product>?) {
        historyOfProducts?.let { listOfProducts ->
            val historyAdapter = ProductsFoundRecyclerViewAdapter(this)
            historyAdapter.submitProductsFound(listOfProducts)
            binding.searchInputRvHistory.root.apply {
                isVisible = true
                layoutManager = LinearLayoutManager(this.context)
                adapter = historyAdapter
            }
        } ?: showEmptyHistory()
    }

    private fun showEmptyHistory() {
        binding.searchInputTvEmptyHistory.isVisible = true
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

    override fun onProductCardClicked(productClicked: Product) {
        findNavController().navigate(R.id.navigate_to_product_details_fragment, Bundle().apply {
            putString(PERMALINK, productClicked.permalink)
        })
    }
}
