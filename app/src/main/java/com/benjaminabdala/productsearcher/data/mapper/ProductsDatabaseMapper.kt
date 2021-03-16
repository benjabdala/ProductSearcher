package com.benjaminabdala.productsearcher.data.mapper

import com.benjaminabdala.productsearcher.data.database.entity.ProductDatabase
import com.benjaminabdala.productsearcher.data.entity.Product

class ProductsDatabaseMapper {

    fun transformToProduct(productDatabase: ProductDatabase): Product =
        with(productDatabase) {
            Product(
                title,
                price,
                permalink,
                imageUrl,
                addressCityName,
                freeShipping
            )
        }

    fun transformToListOfProducts(listOfProductDatabase: List<ProductDatabase>): List<Product> =
        listOfProductDatabase.map { productDatabaseItem ->
            transformToProduct(productDatabaseItem)
        }

    fun transformToProductDatabase(product: Product): ProductDatabase =
        with(product) {
            ProductDatabase(
                title,
                price,
                permalink,
                imageUrl,
                addressCityName,
                freeShipping
            )
        }
}
