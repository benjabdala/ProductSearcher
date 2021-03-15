package com.benjaminabdala.productsearcher.data

import com.benjaminabdala.productsearcher.data.response.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductSearcherAPI {
    @GET("/sites/MLA/search")
    fun getSearchProductsRequest(@Query(QUERY) productInput: String): Call<MainResponse>

    companion object {
        private const val QUERY = "q"
    }
}
