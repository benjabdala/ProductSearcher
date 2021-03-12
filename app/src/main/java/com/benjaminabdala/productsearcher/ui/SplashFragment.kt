package com.benjaminabdala.productsearcher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.benjaminabdala.productsearcher.databinding.FragmentSplashBinding
import com.benjaminabdala.productsearcher.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        splashViewModel.runSplashAnimation()
    }

    private fun setUpObserver() {
        splashViewModel.animationFinishedLiveData.observe(::getLifecycle) { animationFinished ->
            navigateToSearchInputScreen(animationFinished)
        }
    }

    private fun navigateToSearchInputScreen(animationFinished: Boolean) {
        if (animationFinished) {
            Toast.makeText(
                this.context,
                "HERE SHOULD NAVIGATE TO SEARCHINPUTSCREEN",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
