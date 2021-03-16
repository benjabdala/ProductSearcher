package com.benjaminabdala.productsearcher.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
class ProductDatabase (
    @PrimaryKey val title: String,
    val price: String,
    val permalink: String,
    val imageUrl: String,
    val addressCityName: String,
    val freeShipping: Boolean
)
