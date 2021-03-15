package com.benjaminabdala.productsearcher.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.benjaminabdala.productsearcher.R
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.databinding.FragmentProductsFoundBinding
import com.benjaminabdala.productsearcher.ui.adapter.ProductsFoundRecyclerViewAdapter
import com.benjaminabdala.productsearcher.util.Data
import com.benjaminabdala.productsearcher.util.Event
import com.benjaminabdala.productsearcher.util.Status
import com.benjaminabdala.productsearcher.viewmodel.ProductsFoundViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFoundFragment : Fragment() {

    private lateinit var binding: FragmentProductsFoundBinding
    private val productsFoundViewModel by viewModel<ProductsFoundViewModel>()
    private val args: ProductsFoundFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProductsFoundBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsFoundViewModel.productsFoundLiveData.observe(::getLifecycle, ::updateUI)
        productsFoundViewModel.searchProducts(args.productInput)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.productsFoundError.errorLayoutButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateUI(event: Event<Data<List<Product>>>) {
        val eventData = event.getContent()
        when (eventData.status) {
            Status.LOADING -> {
                setLoaderVisible(true)
            }
            Status.SUCCESS -> {
                eventData.data?.let { productsFound ->
                    showProductsFound(productsFound)
                } ?: showError()

            }
            Status.ERROR -> {
                showError()
            }
        }
    }

    private fun setLoaderVisible(visible: Boolean) {
        binding.productsFoundLoader.root.isVisible = visible
    }

    private fun showProductsFound(productsFound: List<Product>) {
        setLoaderVisible(false)
        binding.productsFoundContent.isVisible = true
        binding.productsFoundTvTitle.text =
            getString(R.string.products_found_title_text, args.productInput)
        val productsFoundAdapter = ProductsFoundRecyclerViewAdapter()
        productsFoundAdapter.submitProductsFound(productsFound)
        binding.productsFoundRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = productsFoundAdapter
        }
    }

    private fun showError() {
        setLoaderVisible(false)
        binding.productsFoundError.root.isVisible = true
    }
}
