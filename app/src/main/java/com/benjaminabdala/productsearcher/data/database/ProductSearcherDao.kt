package com.benjaminabdala.productsearcher.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benjaminabdala.productsearcher.data.database.entity.ProductDatabase

@Dao
interface ProductSearcherDao {

    @Query("SELECT * FROM products_table")
        fun getProducts(): List<ProductDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductDatabase)
}
