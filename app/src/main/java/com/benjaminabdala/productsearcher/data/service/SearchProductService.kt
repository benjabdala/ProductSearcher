package com.benjaminabdala.productsearcher.data.service

import com.benjaminabdala.productsearcher.data.ProductSearcherAPI
import com.benjaminabdala.productsearcher.data.ProductSearcherRequestGenerator
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.data.mapper.ProductsMapper
import com.benjaminabdala.productsearcher.util.Constants.ERROR
import com.benjaminabdala.productsearcher.util.Result
import java.lang.Exception

class SearchProductService {

    private val api = ProductSearcherRequestGenerator()
    private val productsMapper = ProductsMapper()

    fun searchProducts(productInput: String): Result<List<Product>> {
        try {
            val callResponse = api.createService(ProductSearcherAPI::class.java)
                .getSearchProductsRequest(productInput)
            val response = callResponse.clone().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    productsMapper.transformToListOfProducts(it.results)
                }?.let {
                    return Result.Success(it)
                }
            }
        } catch (e: Exception) {
            return Result.Failure(Exception(ERROR))
        }
        return Result.Failure(Exception(ERROR))
    }
}
