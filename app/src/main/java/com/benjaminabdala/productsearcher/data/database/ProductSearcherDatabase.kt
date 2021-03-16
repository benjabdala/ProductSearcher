package com.benjaminabdala.productsearcher.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benjaminabdala.productsearcher.data.database.entity.ProductDatabase
import com.benjaminabdala.productsearcher.data.entity.Product
import com.benjaminabdala.productsearcher.data.mapper.ProductsDatabaseMapper
import com.benjaminabdala.productsearcher.util.Result
import java.lang.Exception

@Database(entities = [ProductDatabase::class], version = 1)
abstract class ProductSearcherDatabase: RoomDatabase() {

    abstract fun productSearcherDao(): ProductSearcherDao
    private val productsMapper = ProductsDatabaseMapper()

    fun getProductsFromDatabase(): Result<List<Product>> {
        productSearcherDao().getProducts().let { listOfProductDatabase ->
            return if (listOfProductDatabase.isNotEmpty()) {
                Result.Success(productsMapper.transformToListOfProducts(listOfProductDatabase))
            } else {
                Result.Failure(Exception(PRODUCTS_ERROR))
            }
        }
    }

    fun addProduct(product: Product) {
        productSearcherDao().insertProduct(productsMapper.transformToProductDatabase(product))
    }

    companion object {
        private const val PRODUCTS_ERROR = "Products not found..."
    }
}
