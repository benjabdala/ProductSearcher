package com.benjaminabdala.productsearcher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.benjaminabdala.productsearcher.R
import com.benjaminabdala.productsearcher.databinding.FragmentSplashBinding
import com.benjaminabdala.productsearcher.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = binding.root

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
            findNavController().navigate(R.id.navigate_to_search_input_fragment)
        }
    }
}
