package com.benjaminabdala.productsearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benjaminabdala.productsearcher.data.database.ProductSearcherDatabase
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.data.service.SearchProductService
import com.benjaminabdala.productsearcher.util.Data
import com.benjaminabdala.productsearcher.util.Event
import com.benjaminabdala.productsearcher.util.Result
import com.benjaminabdala.productsearcher.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsFoundViewModel(
    private val searchProductService: SearchProductService,
    private val productSearcherDatabase: ProductSearcherDatabase
) : ViewModel() {

    var productsFoundLiveData = MutableLiveData<Event<Data<List<Product>>>>()

    fun searchProducts(productInput: String) = viewModelScope.launch {
        productsFoundLiveData.postValue(Event(Data(status = Status.LOADING)))

        withContext(Dispatchers.IO) { searchProductService.searchProducts(productInput) }.let { result ->
            when (result) {
                is Result.Success -> {
                    productsFoundLiveData.postValue(
                        Event(Data(status = Status.SUCCESS, data = result.data))
                    )
                }
                is Result.Failure -> {
                    productsFoundLiveData.postValue(
                        Event(Data(status = Status.ERROR, error = result.exception))
                    )
                }
            }
        }
    }

    fun addProductToDatabase(productClicked: Product) = viewModelScope.launch {
        withContext(Dispatchers.Default) { productSearcherDatabase.addProduct(productClicked) }
    }
}
