package com.benjaminabdala.productsearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benjaminabdala.productsearcher.data.database.ProductSearcherDatabase
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryOfProductsViewModel(private val productSearcherDatabase: ProductSearcherDatabase) :
    ViewModel() {

    val historyOfProductsLiveData = MutableLiveData<List<Product>?>()

    fun getHistoryOfProductsFromDatabase() = viewModelScope.launch {
        withContext(Dispatchers.IO) { productSearcherDatabase.getProductsFromDatabase() }.let { result ->
            when (result) {
                is Result.Success -> {
                    historyOfProductsLiveData.postValue(result.data)
                }
                is Result.Failure -> {
                    historyOfProductsLiveData.postValue(null)
                }
            }
        }
    }
}
