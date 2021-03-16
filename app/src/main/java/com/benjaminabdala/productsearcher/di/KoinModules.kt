package com.benjaminabdala.productsearcher.di

import androidx.room.Room
import com.benjaminabdala.productsearcher.data.database.ProductSearcherDatabase
import com.benjaminabdala.productsearcher.data.service.SearchProductService
import com.benjaminabdala.productsearcher.viewmodel.HistoryOfProductsViewModel
import com.benjaminabdala.productsearcher.viewmodel.ProductsFoundViewModel
import com.benjaminabdala.productsearcher.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { ProductsFoundViewModel(get(), get()) }
    viewModel { HistoryOfProductsViewModel(get()) }
}

val serviceModule = module {
    single { SearchProductService() }
}

val databaseModule = module {
    single { Room.databaseBuilder(get(), ProductSearcherDatabase::class.java, DATABASE_NAME).build() }
    single { get<ProductSearcherDatabase>().productSearcherDao() }
}

private const val DATABASE_NAME = "ProductSearcherDatabase"