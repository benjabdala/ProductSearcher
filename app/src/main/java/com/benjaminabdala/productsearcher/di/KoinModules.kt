package com.benjaminabdala.productsearcher.di

import com.benjaminabdala.productsearcher.data.service.SearchProductService
import com.benjaminabdala.productsearcher.viewmodel.ProductsFoundViewModel
import com.benjaminabdala.productsearcher.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { ProductsFoundViewModel(get()) }
}

val serviceModule = module {
    single { SearchProductService() }
}
