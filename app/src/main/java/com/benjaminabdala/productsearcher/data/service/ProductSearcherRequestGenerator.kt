package com.benjaminabdala.productsearcher.data.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductSearcherRequestGenerator {

    private val httpClient = OkHttpClient.Builder().addInterceptor{ chain ->
        val defaultRequest = chain.request()
        val defaultHttpUrl = defaultRequest.url()
        val httpUrl = defaultHttpUrl.newBuilder()
            .build()
        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
        chain.proceed(requestBuilder.build())
    }

    private val buider = Retrofit.Builder()
        .baseUrl(PRODUCTS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = buider.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        private const val PRODUCTS_BASE_URL = "https://api.mercadolibre.com/"
    }
}
