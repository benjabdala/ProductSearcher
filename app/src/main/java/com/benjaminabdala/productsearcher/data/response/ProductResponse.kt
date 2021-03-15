package com.benjaminabdala.productsearcher.data.response

class ProductResponse (
    val title: String,
    val price: Double,
    val thumbnail: String,
    val permalink: String,
    val address: ProductAddressResponse,
    val shipping: ProductShippingResponse,
)
