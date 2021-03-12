package com.benjaminabdala.productsearcher.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.benjaminabdala.productsearcher.databinding.FragmentSearchInputBinding

class SearchInputFragment : Fragment() {

    private lateinit var binding: FragmentSearchInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchInputBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.searchInputBtnSearch.setOnClickListener {
            Toast.makeText(
                this.context,
                "SHOULD SHOW A LOADER AND FETCH THE PRODUCTS FROM THE API AND SHOW IT",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
