package com.benjaminabdala.productsearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    var animationFinishedLiveData = MutableLiveData<Boolean>()

    fun runSplashAnimation() = viewModelScope.launch {
        delay(DELAY_TIME)
        animationFinishedLiveData.postValue(true)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}
