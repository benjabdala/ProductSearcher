package com.benjaminabdala.productsearcher

import android.app.Application
import com.benjaminabdala.productsearcher.di.serviceModule
import com.benjaminabdala.productsearcher.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProductSearcherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProductSearcherApplication)
            modules(viewModelModule, serviceModule)
        }
    }
}
