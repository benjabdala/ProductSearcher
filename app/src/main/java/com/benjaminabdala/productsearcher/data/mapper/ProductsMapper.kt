package com.benjaminabdala.productsearcher.data.mapper

import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.data.service.response.ProductResponse

class ProductsMapper {

    private fun transformToProduct(productResponse: ProductResponse): Product =
        with(productResponse) {
            Product(
                title,
                price.toString(),
                permalink,
                thumbnail,
                address.city_name,
                shipping.free_shipping
            )
        }

    fun transformToListOfProducts(listOfProductResponse: List<ProductResponse>): List<Product> =
        listOfProductResponse.map { productResponseItem ->
            transformToProduct(productResponseItem)
        }
}
